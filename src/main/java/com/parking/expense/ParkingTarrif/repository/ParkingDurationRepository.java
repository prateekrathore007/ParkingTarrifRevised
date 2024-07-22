package com.parking.expense.ParkingTarrif.repository;

import com.parking.expense.ParkingTarrif.entity.ParkingDuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingDurationRepository extends JpaRepository<ParkingDuration, Long> {

    Optional<ParkingDuration> findByLicensePlateAndEndTimeIsNull(String licensePlate);
}
