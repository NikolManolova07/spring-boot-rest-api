package org.fmiplovdiv.weblanguages.homework.travelagency.dto;

import org.fmiplovdiv.weblanguages.homework.travelagency.model.Holiday;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidayRepository extends ListCrudRepository<Holiday, Long> {

}