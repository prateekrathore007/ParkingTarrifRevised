package com.parking.expense.ParkingTarrif.controller;



import com.parking.expense.ParkingTarrif.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Scheduler for loading CSV data every 2 hours.
 */
@Component
public class ProductScheduler {

    @Autowired
    private ProductService productService;

    /**
     * Scheduled job to load data from a default CSV file.
     */
    @Scheduled(cron = "0 0 */2 * * ?")
    public void loadDataScheduled() {
        File defaultFile = new File("default-products.csv");
        if (defaultFile.exists()) {
            productService.loadDataFromCsv(defaultFile);
        }
    }
}