package org.fmiplovdiv.weblanguages.homework.travelagency.dto;

import org.fmiplovdiv.weblanguages.homework.travelagency.model.Reservation;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends ListCrudRepository<Reservation, Long> {

}