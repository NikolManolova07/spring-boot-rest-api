package org.fmiplovdiv.weblanguages.homework.travelagency.controllers;

import java.util.List;

import org.fmiplovdiv.weblanguages.homework.travelagency.dto.CreateHolidayDTO;
import org.fmiplovdiv.weblanguages.homework.travelagency.dto.ResponseHolidayDTO;
import org.fmiplovdiv.weblanguages.homework.travelagency.dto.UpdateHolidayDTO;
import org.fmiplovdiv.weblanguages.homework.travelagency.service.HolidayService;
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
@RequestMapping("/holidays")
public class HolidayController {
	
	@Autowired
	private HolidayService service;
	
	@PostMapping
	public ResponseEntity<ResponseHolidayDTO> createHoliday(@RequestBody CreateHolidayDTO toCreate) {
		ResponseHolidayDTO response = service.createHoliday(toCreate);
        return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{holidayId}")
	public ResponseEntity<Boolean> deleteHoliday(@PathVariable("holidayId") Long id) {
		Boolean response = service.deleteHoliday(id);
        return ResponseEntity.ok(response);
	}
	
	@GetMapping
	public ResponseEntity<List<ResponseHolidayDTO>> getAllHolidays(
			@RequestParam(name = "location", required = false) String location,
            @RequestParam(name = "startDate", required = false) String startDate,
            @RequestParam(name = "duration", required = false) Integer duration) {
		List<ResponseHolidayDTO> response = service.getAllHolidays(location, startDate, duration);
        return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{holidayId}")
	public ResponseEntity<ResponseHolidayDTO> getHolidayById(@PathVariable("holidayId") Long id) {
		ResponseHolidayDTO response = service.getHolidayById(id);
        return ResponseEntity.ok(response);
	}
	
	@PutMapping
	public ResponseEntity<ResponseHolidayDTO> updateHoliday(@RequestBody UpdateHolidayDTO toUpdate) {
		ResponseHolidayDTO response = service.updateHoliday(toUpdate);
        return ResponseEntity.ok(response);
	}
}
