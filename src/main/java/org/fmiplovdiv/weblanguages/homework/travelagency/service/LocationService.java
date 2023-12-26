package org.fmiplovdiv.weblanguages.homework.travelagency.service;

import java.util.ArrayList;
import java.util.List;

import org.fmiplovdiv.weblanguages.homework.travelagency.dto.CreateLocationDTO;
import org.fmiplovdiv.weblanguages.homework.travelagency.dto.LocationRepository;
import org.fmiplovdiv.weblanguages.homework.travelagency.dto.ResponseLocationDTO;
import org.fmiplovdiv.weblanguages.homework.travelagency.dto.UpdateLocationDTO;
import org.fmiplovdiv.weblanguages.homework.travelagency.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
	
	@Autowired
    private LocationRepository locationRepository;

    public ResponseLocationDTO createLocation(CreateLocationDTO createLocationDTO) {
        Location location = new Location();
        
        location.setStreet(createLocationDTO.getStreet());
        location.setNumber(createLocationDTO.getNumber());
        location.setCity(createLocationDTO.getCity());
        location.setCountry(createLocationDTO.getCountry());
        location.setImageUrl(createLocationDTO.getImageUrl());
        
        location = locationRepository.save(location);
        
        return new ResponseLocationDTO(location.getId(), location.getStreet(), location.getNumber(),
        		location.getCity(), location.getCountry(), location.getImageUrl());
    }

    public boolean deleteLocation(Long id) {
        locationRepository.deleteById(id);
        return true;
    }

    public List<ResponseLocationDTO> getAllLocations() {
        List<Location> locations = locationRepository.findAll();
        
        if (locations.size() == 0) {
			return null;
		}
        
        List<ResponseLocationDTO> responseLocationDTOList = new ArrayList<>();
        
        for (Location location : locations) {
            responseLocationDTOList.add(new ResponseLocationDTO(location.getId(), location.getStreet(), location.getNumber(),
            		location.getCity(), location.getCountry(), location.getImageUrl()));
        }

        return responseLocationDTOList;
    }

    public ResponseLocationDTO getLocationById(Long id) {
        Location location = locationRepository.findById(id).orElse(null);
        
		if (location == null) {
			return null;
		}
		
        return new ResponseLocationDTO(location.getId(), location.getStreet(), location.getNumber(),
        		location.getCity(), location.getCountry(), location.getImageUrl());
    }

    public ResponseLocationDTO updateLocation(UpdateLocationDTO updateLocationDTO) {
        Location currentLocation = locationRepository.findById(updateLocationDTO.getId()).orElse(null);
        
        if (currentLocation != null) {
        	currentLocation.setStreet(updateLocationDTO.getStreet());
        	currentLocation.setNumber(updateLocationDTO.getNumber());
        	currentLocation.setCity(updateLocationDTO.getCity());
        	currentLocation.setCountry(updateLocationDTO.getCountry());
        	currentLocation.setImageUrl(updateLocationDTO.getImageUrl());
        	
        	currentLocation = locationRepository.save(currentLocation);
            
            return new ResponseLocationDTO(currentLocation.getId(), currentLocation.getStreet(), currentLocation.getNumber(),
            		currentLocation.getCity(), currentLocation.getCountry(), currentLocation.getImageUrl());
        } else {
            return null;
        }
    }
}
