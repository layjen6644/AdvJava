/*
Name: Layla Jenkins
Date: 10/31/2026
Assignment: SDC330 - Lecture Project

This class implements the InventoryOperations interface. It will handle the application's
operations for managing the inventory. It adds, updates, deletes, searches, and views supplies.
This class demonstrates composition as it has a DatabaseManager object.
*/

import java.sql.Connection;
import java.util.List;

public class InventoryManager implements InventoryOperations {
    // Composition example: InventoryManager contains a DatabaseManager object
    private DatabaseManager databaseManager;
    private Connection conn;

    // Constructor
    public InventoryManager(Connection conn) {
        this.conn = conn;
        // Creating DatabaseManager object and using it's constructor
        this.databaseManager = new DatabaseManager();
        // Creates table if it doesn't exist
        this.databaseManager.createTable(conn);
    }

    // Using interface methods
    @Override 
    public void addSupply(Supply supply) {
        // Error handling. 
        // Checks to see if supply already exists before adding it
        // Prevents duplicate supplies
        // Gets a list of all supplies
        List<Supply> existingSupplies = getViewSupplies();
        // Since SQL assigns the supplyId, I am checking for the same name AND category
        for (Supply existingSupply : existingSupplies) {
            if (existingSupply.getName().equalsIgnoreCase(supply.getName()) 
                    && existingSupply.getCategory().equalsIgnoreCase(supply.getCategory())) {
                System.out.println("\nSupply already exists.\n");
                return;
            }
        }
        // Adds to supply if one wasn't found
        databaseManager.addSupply(conn, supply);
        System.out.println("\n- Supply added successfully. -\n");
    }

    @Override 
    public List<Supply> getViewSupplies() {
        return databaseManager.getAllSupplies(conn);
    }

    @Override 
    public Supply findSupply(int supplyId) {
        // DatabaseManager already returns a null if a match wasn't found
        return databaseManager.findSupply(conn, supplyId);
    }

    @Override
    public void updateSupply(Supply updatedSupply) {
        Supply supply = findSupply(updatedSupply.getSupplyId());
       // Checks to see if a supply is found, then allows user to update it
       if (supply != null) {
        databaseManager.updateSupply(conn, updatedSupply);
        System.out.println("\n- Supply updated successfully. -\n");
       } else {
        // Displays if supply wasn't found
        System.out.println("\n-- Supply not found. --\n");
       }
    }

    @Override 
    public void deleteSupply(int supplyId) {
        Supply supply = findSupply(supplyId);
        // Checks to see if supply was found, then deletes it
        if (supply != null) {
            databaseManager.deleteSupply(conn, supplyId);
            System.out.println("\nSupply deleted successfully.\n");
        } else {
            // Displays if supply not found
            System.out.println("\n-- Supply not found. --\n");
        }
    }

    @Override 
    public List<Supply> getLowStock() {
        return databaseManager.getLowStock(conn);
    }
}