package org.fmiplovdiv.weblanguages.homework.travelagency.dto;

import org.fmiplovdiv.weblanguages.homework.travelagency.model.Location;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends ListCrudRepository<Location, Long> {

}