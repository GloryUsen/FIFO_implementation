package org.Store;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Read purchase orders from CSV file
        List<PurchaseOrder> purchaseOrders = readPurchaseOrdersFromCSV("/Users/decagon/Desktop/WEEK3/src/main/java/org/Store/resources/ProductList.csv");

        // Create a store
        Store store = new Store();

        // Add purchase orders to the store
        for (PurchaseOrder purchaseOrder : purchaseOrders) {
            store.addPurchaseOrder(purchaseOrder);
        }

        // Process purchase orders based on customer FIFO
        CustomerFIFOSales fifoSales = new CustomerFIFOSales();
        fifoSales.addPurchaseOrders(purchaseOrders);

        System.out.println("Processing purchase orders based on customer FIFO:");
        while (!fifoSales.isEmpty()) {
            PurchaseOrder purchaseOrder = fifoSales.processNextPurchaseOrder();
            Customer customer = purchaseOrder.getCustomer();
            System.out.println("Customer: " + customer.getName());
            System.out.println("Items:");
            for (Item item : purchaseOrder.getItems()) {
                System.out.println(item.getName() + " - $" + item.getPrice());
            }
            System.out.println();
        }

        // Process purchase orders based on item quantity
        ItemQuantitySales quantitySales = new ItemQuantitySales();
        quantitySales.addPurchaseOrders(purchaseOrders);

        System.out.println("Processing purchase orders based on item quantity:");
        while (!quantitySales.isEmpty()) {
            PurchaseOrder purchaseOrder = quantitySales.processNextPurchaseOrder();
            Customer customer = purchaseOrder.getCustomer();
            System.out.println("Customer: " + customer.getName());
            System.out.println("Items:");
            for (Item item : purchaseOrder.getItems()) {
                System.out.println(item.getName() + " - $" + item.getPrice());
            }
            System.out.println();
        }
    }

    private static List<PurchaseOrder> readPurchaseOrdersFromCSV(String filename) {
        List<PurchaseOrder> purchaseOrders = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Skip the header line
                }

                String[] data = line.split(",");

                String customerName = data[0].trim();
                String itemName = data[1].trim();
//                double price = Double.parseDouble(data[2].trim());

                // Check if the customer already exists in the purchase orders
                Customer customer = null;
                for (PurchaseOrder purchaseOrder : purchaseOrders) {
                    if (purchaseOrder.getCustomer().getName().equals(customerName)) {
                        customer = purchaseOrder.getCustomer();
                        break;
                    }
                }

                // If the customer doesn't exist, create a new one
                if (customer == null) {
                    customer = new Customer(customerName);
                }

                // Create the item and purchase order
                Item item = new Item(itemName);
                PurchaseOrder purchaseOrder = new PurchaseOrder(customer);
                purchaseOrder.addItem(item);

                // Add the purchase order to the list
                purchaseOrders.add(purchaseOrder);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return purchaseOrders;
    }
}
