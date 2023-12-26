package org.fmiplovdiv.weblanguages.homework.travelagency.service;

import java.util.List;
import java.util.stream.Collectors;

import org.fmiplovdiv.weblanguages.homework.travelagency.dto.CreateReservationDTO;
import org.fmiplovdiv.weblanguages.homework.travelagency.dto.HolidayRepository;
import org.fmiplovdiv.weblanguages.homework.travelagency.dto.ReservationRepository;
import org.fmiplovdiv.weblanguages.homework.travelagency.dto.ResponseHolidayDTO;
import org.fmiplovdiv.weblanguages.homework.travelagency.dto.ResponseLocationDTO;
import org.fmiplovdiv.weblanguages.homework.travelagency.dto.ResponseReservationDTO;
import org.fmiplovdiv.weblanguages.homework.travelagency.dto.UpdateReservationDTO;
import org.fmiplovdiv.weblanguages.homework.travelagency.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

	@Autowired
    private ReservationRepository reservationRepository;
	
	@Autowired
    private HolidayRepository holidayRepository;

    public ResponseReservationDTO createReservation(CreateReservationDTO createReservationDTO) {
    	if (!holidayRepository.existsById(createReservationDTO.getHoliday())) {
			return null;
		}
    	
    	Reservation reservation = new Reservation();
    	reservation.setContactName(createReservationDTO.getContactName());
        reservation.setPhoneNumber(createReservationDTO.getPhoneNumber());
        reservation.setHoliday(holidayRepository.findById(createReservationDTO.getHoliday()).get());
        
        reservation = reservationRepository.save(reservation);
        
        return new ResponseReservationDTO(
        		reservation.getId(), reservation.getContactName(), reservation.getPhoneNumber(),
        		new ResponseHolidayDTO(
        				reservation.getHoliday().getId(),
        				new ResponseLocationDTO(
        						reservation.getHoliday().getLocation().getId(),
        						reservation.getHoliday().getLocation().getStreet(),
        						reservation.getHoliday().getLocation().getNumber(),
        						reservation.getHoliday().getLocation().getCity(),
        						reservation.getHoliday().getLocation().getCountry(),
        						reservation.getHoliday().getLocation().getImageUrl()
        						),
        				reservation.getHoliday().getTitle(), reservation.getHoliday().getStartDate(),
        				reservation.getHoliday().getDuration(), reservation.getHoliday().getPrice(), reservation.getHoliday().getFreeSlots()));
    }

    public boolean deleteReservation(Long id) {
    	reservationRepository.deleteById(id);
        return true;
    }

    public List<ResponseReservationDTO> getAllReservations(String phoneNumber) {
    	List<Reservation> reservations = reservationRepository.findAll();
        
        if (reservations.size() == 0) {
			return null;
		}
        
        reservations = filterReservationsByPhoneNumber(reservations, phoneNumber);
        
        return reservations.stream().map(r -> new ResponseReservationDTO(
        		r.getId(), r.getContactName(), r.getPhoneNumber(),
        		new ResponseHolidayDTO(
        				r.getHoliday().getId(),
        				new ResponseLocationDTO(
        						r.getHoliday().getLocation().getId(),
        						r.getHoliday().getLocation().getStreet(),
        						r.getHoliday().getLocation().getNumber(),
        						r.getHoliday().getLocation().getCity(),
        						r.getHoliday().getLocation().getCountry(),
        						r.getHoliday().getLocation().getImageUrl()
        						),
        				r.getHoliday().getTitle(),
        				r.getHoliday().getStartDate(),
        				r.getHoliday().getDuration(),
        				r.getHoliday().getPrice(),
        				r.getHoliday().getFreeSlots()
        				)
        		)).toList();
    }

    public ResponseReservationDTO getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        
        if (reservation == null) {
        	return null;
        }
        
        return new ResponseReservationDTO(
        		reservation.getId(), reservation.getContactName(), reservation.getPhoneNumber(),
        		new ResponseHolidayDTO(
        				reservation.getHoliday().getId(),
        				new ResponseLocationDTO(
        						reservation.getHoliday().getLocation().getId(),
        						reservation.getHoliday().getLocation().getStreet(),
        						reservation.getHoliday().getLocation().getNumber(),
        						reservation.getHoliday().getLocation().getCity(),
        						reservation.getHoliday().getLocation().getCountry(),
        						reservation.getHoliday().getLocation().getImageUrl()
        						),
        				reservation.getHoliday().getTitle(), reservation.getHoliday().getStartDate(),
        				reservation.getHoliday().getDuration(), reservation.getHoliday().getPrice(), reservation.getHoliday().getFreeSlots()));
    }

    public ResponseReservationDTO updateReservation(UpdateReservationDTO updateReservationDTO) {
        Reservation currentReservation = reservationRepository.findById(updateReservationDTO.getId()).orElse(null);
        
        if (currentReservation != null) {
        	currentReservation.setContactName(updateReservationDTO.getContactName());
        	currentReservation.setPhoneNumber(updateReservationDTO.getPhoneNumber());
        	currentReservation.setHoliday(holidayRepository.findById(updateReservationDTO.getHoliday()).get());
            
        	currentReservation = reservationRepository.save(currentReservation);
            
            return new ResponseReservationDTO(
            		currentReservation.getId(), currentReservation.getContactName(), currentReservation.getPhoneNumber(),
            		new ResponseHolidayDTO(
            				currentReservation.getHoliday().getId(),
            				new ResponseLocationDTO(
            						currentReservation.getHoliday().getLocation().getId(),
            						currentReservation.getHoliday().getLocation().getStreet(),
            						currentReservation.getHoliday().getLocation().getNumber(),
            						currentReservation.getHoliday().getLocation().getCity(),
            						currentReservation.getHoliday().getLocation().getCountry(),
            						currentReservation.getHoliday().getLocation().getImageUrl()
            						),
            				currentReservation.getHoliday().getTitle(), currentReservation.getHoliday().getStartDate(),
            				currentReservation.getHoliday().getDuration(), currentReservation.getHoliday().getPrice(), currentReservation.getHoliday().getFreeSlots()));
        } else {
            return null;
        }
    }
    
    /* Filter Reservations By Phone Number. */
    private List<Reservation> filterReservationsByPhoneNumber(List<Reservation> reservations, String phoneNumber) {
        if (phoneNumber != null) {
            reservations = reservations.stream()
                .filter(reservation -> reservation.getPhoneNumber().equals(phoneNumber))
                .collect(Collectors.toList());
        }
        return reservations;
    }
}
