package com.parking.expense.ParkingTarrif.util;


import com.parking.expense.ParkingTarrif.entity.ProductA;
import com.parking.expense.ParkingTarrif.entity.ProductB;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Utility for parsing CSV files.
 */
@Component
public class CsvUtil {

    public List<ProductA> parseCsvToProductA(File csvFile) {
        List<ProductA> products = new ArrayList<>();
        try (Scanner scanner =  new Scanner(new FileReader(csvFile))) {
            scanner.nextLine(); // Skip header
            while (scanner.hasNextLine()) {
                String[] values = scanner.nextLine().split(",");
                ProductA product = new ProductA();
                product.setId(Long.valueOf(values[0]));
                product.setQuantity(Integer.parseInt(values[1]));
                product.setName(values[2]);
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<ProductB> parseCsvToProductB(File csvFile) {
        List<ProductB> products = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileReader(csvFile))) {
            scanner.nextLine(); // Skip header
            while (scanner.hasNextLine()) {
                String[] values = scanner.nextLine().split(",");
                ProductB product = new ProductB();
                product.setId(Long.valueOf(values[0]));
                product.setQuantity(Integer.parseInt(values[1]));
                product.setName(values[2]);
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
}

