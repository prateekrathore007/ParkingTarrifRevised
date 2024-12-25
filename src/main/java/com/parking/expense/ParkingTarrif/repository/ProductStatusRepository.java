package com.parking.expense.ParkingTarrif.repository;

import com.parking.expense.ParkingTarrif.entity.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductStatusRepository extends JpaRepository<ProductStatus, Long> {
}
