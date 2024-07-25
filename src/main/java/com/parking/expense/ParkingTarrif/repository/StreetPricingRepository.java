package com.parking.expense.ParkingTarrif.repository;

import com.parking.expense.ParkingTarrif.entity.StreetPricing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StreetPricingRepository extends JpaRepository<StreetPricing, Long> {
    Optional<StreetPricing> findByStreetName(String streetName);
}
