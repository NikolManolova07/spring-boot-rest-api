package org.fmiplovdiv.weblanguages.homework.travelagency.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.fmiplovdiv.weblanguages.homework.travelagency.dto.CreateHolidayDTO;
import org.fmiplovdiv.weblanguages.homework.travelagency.dto.HolidayRepository;
import org.fmiplovdiv.weblanguages.homework.travelagency.dto.LocationRepository;
import org.fmiplovdiv.weblanguages.homework.travelagency.dto.ResponseHolidayDTO;
import org.fmiplovdiv.weblanguages.homework.travelagency.dto.ResponseLocationDTO;
import org.fmiplovdiv.weblanguages.homework.travelagency.dto.UpdateHolidayDTO;
import org.fmiplovdiv.weblanguages.homework.travelagency.model.Holiday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HolidayService {
	
    @Autowired
    private HolidayRepository holidayRepository;

    @Autowired
    private LocationRepository locationRepository;

    public ResponseHolidayDTO createHoliday(CreateHolidayDTO createHolidayDTO) {
    	if (!locationRepository.existsById(createHolidayDTO.getLocation())) {
			return null;
		}
    	
    	Holiday holiday = new Holiday();        
        holiday.setLocation(locationRepository.findById(createHolidayDTO.getLocation()).get());
        holiday.setTitle(createHolidayDTO.getTitle());
        holiday.setStartDate(createHolidayDTO.getStartDate());
        holiday.setDuration(createHolidayDTO.getDuration());
        holiday.setPrice(createHolidayDTO.getPrice());
        holiday.setFreeSlots(createHolidayDTO.getFreeSlots());
        
        holiday = holidayRepository.save(holiday);
        
        return new ResponseHolidayDTO(
        		holiday.getId(),
        		new ResponseLocationDTO(
        				holiday.getLocation().getId(), holiday.getLocation().getStreet(), holiday.getLocation().getNumber(),
                		holiday.getLocation().getCity(), holiday.getLocation().getCountry(), holiday.getLocation().getImageUrl()
                		),
        		holiday.getTitle(), holiday.getStartDate(), holiday.getDuration(), holiday.getPrice(), holiday.getFreeSlots());
    }

    public boolean deleteHoliday(Long id) {
        holidayRepository.deleteById(id);
        return true;
    }

    public List<ResponseHolidayDTO> getAllHolidays(String location, String startDate, Integer duration) {
    	List<Holiday> holidays = holidayRepository.findAll();
        
        if (holidays.size() == 0) {
			return null;
		}
        
        holidays = filterHolidaysByLocation(holidays, location);
        holidays = filterHolidaysByStartDate(holidays, startDate);
        holidays = filterHolidaysByDuration(holidays, duration);
        
        return holidays.stream().map(h -> new ResponseHolidayDTO(
        		h.getId(),
        		new ResponseLocationDTO(
        				h.getLocation().getId(), h.getLocation().getStreet(), h.getLocation().getNumber(),
        				h.getLocation().getCity(), h.getLocation().getCountry(), h.getLocation().getImageUrl()
        				),
        		h.getTitle(), h.getStartDate(), h.getDuration(), h.getPrice(), h.getFreeSlots()
        		)).toList();
    }

    public ResponseHolidayDTO getHolidayById(Long id) {
        Holiday holiday = holidayRepository.findById(id).orElse(null);
        
        if (holiday == null) {
        	return null;
        }
        
        return new ResponseHolidayDTO(
        		holiday.getId(),
        		new ResponseLocationDTO(
        				holiday.getLocation().getId(), holiday.getLocation().getStreet(), holiday.getLocation().getNumber(),
                		holiday.getLocation().getCity(), holiday.getLocation().getCountry(), holiday.getLocation().getImageUrl()
                		),
        		holiday.getTitle(), holiday.getStartDate(), holiday.getDuration(), holiday.getPrice(), holiday.getFreeSlots());
    }

    public ResponseHolidayDTO updateHoliday(UpdateHolidayDTO updateHolidayDTO) {
        Holiday currentHoliday = holidayRepository.findById(updateHolidayDTO.getId()).orElse(null);
        
        if (currentHoliday != null) {
        	currentHoliday.setLocation(locationRepository.findById(updateHolidayDTO.getLocation()).get());
        	currentHoliday.setTitle(updateHolidayDTO.getTitle());
        	currentHoliday.setStartDate(updateHolidayDTO.getStartDate());
        	currentHoliday.setDuration(updateHolidayDTO.getDuration());
        	currentHoliday.setPrice(updateHolidayDTO.getPrice());
        	currentHoliday.setFreeSlots(updateHolidayDTO.getFreeSlots());
            
        	currentHoliday = holidayRepository.save(currentHoliday);
            
            return new ResponseHolidayDTO(
            		currentHoliday.getId(),
            		new ResponseLocationDTO(
            				currentHoliday.getLocation().getId(), currentHoliday.getLocation().getStreet(), currentHoliday.getLocation().getNumber(),
            				currentHoliday.getLocation().getCity(), currentHoliday.getLocation().getCountry(), currentHoliday.getLocation().getImageUrl()
                    		),
            		currentHoliday.getTitle(), currentHoliday.getStartDate(), currentHoliday.getDuration(), currentHoliday.getPrice(), currentHoliday.getFreeSlots());
        } else {
            return null;
        }
    }
    
    /* Filter Holidays By Location, including by City or Country. */
    private List<Holiday> filterHolidaysByLocation(List<Holiday> holidays, String location) {
        if (location != null && !location.isEmpty()) {
            holidays = holidays.stream()
                .filter(holiday -> holiday.getLocation().getCity().equalsIgnoreCase(location) ||
                		holiday.getLocation().getCountry().equalsIgnoreCase(location))
                .collect(Collectors.toList());
        }
        return holidays;
    }

    /* Filter Holidays By Start Date. */
    private List<Holiday> filterHolidaysByStartDate(List<Holiday> holidays, String startDate) {
        if (startDate != null && !startDate.isEmpty()) {
            holidays = holidays.stream()
                .filter(holiday -> holiday.getStartDate().isEqual(LocalDate.parse(startDate)))
                .collect(Collectors.toList());
        }
        return holidays;
    }

    /* Filter Holidays By Duration. */
    private List<Holiday> filterHolidaysByDuration(List<Holiday> holidays, Integer duration) {
        if (duration != null) {
            holidays = holidays.stream()
                .filter(holiday -> holiday.getDuration() == duration)
                .collect(Collectors.toList());
        }
        return holidays;
    }
}
