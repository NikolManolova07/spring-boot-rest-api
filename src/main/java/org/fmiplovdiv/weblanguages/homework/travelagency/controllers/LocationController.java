package org.fmiplovdiv.weblanguages.homework.travelagency.controllers;

import java.util.List;

import org.fmiplovdiv.weblanguages.homework.travelagency.dto.CreateLocationDTO;
import org.fmiplovdiv.weblanguages.homework.travelagency.dto.ResponseLocationDTO;
import org.fmiplovdiv.weblanguages.homework.travelagency.dto.UpdateLocationDTO;
import org.fmiplovdiv.weblanguages.homework.travelagency.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locations")
public class LocationController {
	
	@Autowired
	private LocationService service;
	
	@PostMapping
	public ResponseEntity<ResponseLocationDTO> createLocation(@RequestBody CreateLocationDTO toCreate) {
		ResponseLocationDTO response = service.createLocation(toCreate);
        return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{locationId}")
	public ResponseEntity<Boolean> deleteLocation(@PathVariable("locationId") Long id) {
		Boolean response = service.deleteLocation(id);
        return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{locationId}")
	public ResponseEntity<ResponseLocationDTO> getLocationById(@PathVariable("locationId") Long id) {
		ResponseLocationDTO response = service.getLocationById(id);
        return ResponseEntity.ok(response);
	}
	
	@GetMapping
	public ResponseEntity<List<ResponseLocationDTO>> getAllLocations() {
		List<ResponseLocationDTO> response = service.getAllLocations();
        return ResponseEntity.ok(response);
	}

	@PutMapping
	public ResponseEntity<ResponseLocationDTO> updateLocation(@RequestBody UpdateLocationDTO toUpdate) {
		ResponseLocationDTO response = service.updateLocation(toUpdate);
        return ResponseEntity.ok(response);
	}
}
