package org.fmiplovdiv.weblanguages.homework.travelagency.controllers;

import java.util.List;

import org.fmiplovdiv.weblanguages.homework.travelagency.dto.CreateReservationDTO;
import org.fmiplovdiv.weblanguages.homework.travelagency.dto.ResponseReservationDTO;
import org.fmiplovdiv.weblanguages.homework.travelagency.dto.UpdateReservationDTO;
import org.fmiplovdiv.weblanguages.homework.travelagency.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
	
	@Autowired
	private ReservationService service;
	
	@PostMapping
	public ResponseEntity<ResponseReservationDTO> createReservation(@RequestBody CreateReservationDTO toCreate) {
		ResponseReservationDTO response = service.createReservation(toCreate);
        return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{reservationId}")
	public ResponseEntity<Boolean> deleteReservation(@PathVariable("reservationId") Long id) {
		Boolean response = service.deleteReservation(id);
        return ResponseEntity.ok(response);
	}
	
	@GetMapping
	public ResponseEntity<List<ResponseReservationDTO>> getAllReservation(
			@RequestParam(name = "phoneNumber", required = false) String phoneNumber) {
		List<ResponseReservationDTO> response = service.getAllReservations(phoneNumber);
        return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{reservationId}")
	public ResponseEntity<ResponseReservationDTO> getReservationById(@PathVariable("reservationId") Long id) {
		ResponseReservationDTO response = service.getReservationById(id);
        return ResponseEntity.ok(response);
	}
	
	@PutMapping
	public ResponseEntity<ResponseReservationDTO> updateLocation(@RequestBody UpdateReservationDTO toUpdate) {
		ResponseReservationDTO response = service.updateReservation(toUpdate);
        return ResponseEntity.ok(response);
	}
}
