/*
Name: Layla Jenkins
Date: 10/23/2026
Assignment: SDC330 - Lecture Project

This class implements the InventoryOperations interface. It will handle the application's
operations for managing the inventory. It adds, updates, deletes, searches, and views supplies.
This class demonstrates composition as it has a DatabaseManager object.
*/

import java.util.ArrayList;
import java.util.List;

public class InventoryManager implements InventoryOperations {
    // Property. This will be implemeneted next week
    private DatabaseManager databaseManager;
    // This is being used temporarily to demonstrate how it will work
    private ArrayList<Supply> supplies;

    // Constructor
    public InventoryManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        supplies = new ArrayList<>();
    }

    // Using interface methods
    @Override 
    public void addSupply(Supply supply) {
        // Error handling. 
        // Checks to see if supply already exists before adding it
        // Prevents duplicate supplies
        if (findSupply(supply.getSupplyId()) != null) {
            System.out.println("Supply ID already exists");
            return;
        } 
        // Adds to list of supplies (temporary until next week)
        supplies.add(supply);
        // Confirmation
        System.out.println("Supply added successfully.");
    }

    @Override 
    public List<Supply> getViewSupplies() {
        // Creates an ArrayList to return all supplies
        return new ArrayList<>(supplies);
    }

    @Override 
    public Supply findSupply(String supplyId) {
        // Loops through all supplies in list
        for (Supply supply : supplies) {
            // Error handling, to allow upper and lower case
            // Looks for matching id
            if (supply.getSupplyId().equalsIgnoreCase(supplyId)) {
                // Returns a matching supply if found
                return supply;
            }
        }
        // Returns null if supply isn't found
        return null;
    }

    @Override
    public void updateSupply(Supply updatedSupply) {
        // Loop for positions of supplies
        for (int i = 0; i < supplies.size(); i++) {
            // Checks to see if updated supplyId matches the supplyId at the position in the ArrayList
            if (supplies.get(i).getSupplyId().equalsIgnoreCase(updatedSupply.getSupplyId())) {
                // Updates existing supply at the position it was found
                supplies.set(i, updatedSupply);
                System.out.println("Supply updated successfully.");
                return;
            }
        }
        // If supplyId wasn't found
        System.out.println("Supply not found.");
    }

    @Override 
    public void deleteSupply(String supplyId) {
        // Looks for supply via findSupply() 
        Supply supply = findSupply(supplyId);
        // Checks to see if supply Id was found
        if (supply != null) {
            // Removes if found
            supplies.remove(supply);
            System.out.println("Supply deleted successfully.");
        } else {
            // If supplyId wasn't found
            System.out.println("Supply not found.");
        }
    }

    @Override 
    public List<Supply> getLowStock() {
        // Creates a new list of low stock supplies
        List<Supply> lowStockSupplies = new ArrayList<>();
        // Loops through inventory to see if they are low in stock
        for (Supply supply : supplies) {
            if (supply.isLowStock()) {
                // Adds to list if found to be low in stock
                lowStockSupplies.add(supply);
            }
        }
        // Returns low stock supply list
        return lowStockSupplies;
    }
}