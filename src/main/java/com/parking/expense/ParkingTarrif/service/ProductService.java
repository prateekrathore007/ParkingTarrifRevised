package com.parking.expense.ParkingTarrif.service;



import com.parking.expense.ParkingTarrif.entity.ProductA;
import com.parking.expense.ParkingTarrif.entity.ProductB;
import com.parking.expense.ParkingTarrif.entity.ProductStatus;
import com.parking.expense.ParkingTarrif.repository.ProductARepository;
import com.parking.expense.ParkingTarrif.repository.ProductBRepository;
import com.parking.expense.ParkingTarrif.repository.ProductStatusRepository;
import com.parking.expense.ParkingTarrif.util.CsvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * Service for managing Products.
 */
@Service
public class ProductService {

    @Autowired
    private ProductARepository productARepository;

    @Autowired
    private ProductBRepository productBRepository;

    @Autowired
    private ProductStatusRepository productStatusRepository;

    @Autowired
    private CsvUtil csvUtil;

    /**
     * Loads data into the inactive table, swaps table statuses, and persists the data.
     *
     * @param csvFile the CSV file to process
     */
    public void loadDataFromCsv(File csvFile) {
        ProductStatus status = productStatusRepository.findById(1L).orElse(new ProductStatus());

        String activeTable = status.getActiveTable();
        boolean isProductAActive = "ProductA".equalsIgnoreCase(activeTable);

        // Determine inactive table and delete its data
        if (isProductAActive) {

            productBRepository.deleteAll();
            List<ProductB> productList = csvUtil.parseCsvToProductB(csvFile);
            productBRepository.saveAll(productList);
            status.setActiveTable("ProductB");

        } else {
            productARepository.deleteAll();
            List<ProductA> productList = csvUtil.parseCsvToProductA(csvFile);
            productARepository.saveAll(productList);
            status.setActiveTable("ProductA");
        }

        productStatusRepository.save(status);
    }
}
