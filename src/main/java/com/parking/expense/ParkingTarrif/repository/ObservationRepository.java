package com.parking.expense.ParkingTarrif.repository;

import com.parking.expense.ParkingTarrif.entity.Observation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ObservationRepository extends JpaRepository<Observation, Long> {
}
