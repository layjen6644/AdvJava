/*
Name: Layla Jenkins
Date: 10/31/2026
Assignment: SDC330 - Lecture Project

Main application class.
*/

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Layla Jenkins - Week 4 Lecture Project\n");
        final String dbName = "SupplyInventory.db";
        // Scanner for user input
        Scanner input = new Scanner(System.in);
        // Connection to SQLite Database
        Connection conn = SQLiteDatabase.connect(dbName);
        // Creating object to use interface
        InventoryOperations inventoryManager = new InventoryManager(conn);

        // Welcome message -- Title of application and instructions
        System.out.println("------ Dental Supply Inventory System ------\n");
        // Declaring variable and initializing to zero
        int choice = 0;
        // Main menu, loops until user opts to exit
        while (choice != 7) {
            System.out.println("Select an option from the menu below.\n");
            System.out.println("   1. View all Supplies");
            System.out.println("   2. Search for a supply");
            System.out.println("   3. Add a supply");
            System.out.println("   4. Update a supply");
            System.out.println("   5. Delete a supply");
            System.out.println("   6. View supplies low in stock");
            System.out.println("   7. Exit\n");
            System.out.print("Enter a number: ");
            // Validating user input to ensure it's a number
            if (!input.hasNextInt()) {
                System.out.println("\n-- Invalid selection. You must enter a number 1-7. --\n");
                input.nextLine(); // Clearing input
                continue; // Goes back to start of loop
            }
            // Declaring variable for user input and clearing after reading it
            choice = input.nextInt();
            input.nextLine();

            // Switch/case based on user input
            switch (choice) {
                case 1: 
                    System.out.println();
                    System.out.println("------ Viewing All Supplies ------\n");
                    // Calling method to display supplies
                    List<Supply> supplies = inventoryManager.getViewSupplies();
                    // Error handling, if no supplies are found
                    if (supplies.isEmpty()) {
                        System.out.println("No supplies found.\n");
                    } else {
                        // Loops through list and displays it
                        for (Supply supply : supplies) {
                            System.out.println(supply);
                            System.out.println();
                        }
                    }
                    break;
                case 2:
                    System.out.println();
                    System.out.println("------ Search for a Supply ------\n");
                    System.out.print("Enter supply ID: ");
                    // Ensures a number is entered
                    if (!input.hasNextInt()) {
                        System.out.println("\n-- Invalid ID. Please enter a number. --\n");
                        input.nextLine(); // Clears input
                        break;
                    }
                    // Declaring variable for user input
                    int searchId = input.nextInt();
                    input.nextLine();
                    // Calling method to search for id based on user input
                    Supply foundSupply = inventoryManager.findSupply(searchId);
                    // if statement to display result
                    if (foundSupply == null) {
                        System.out.println("\nSupply not found.\n");
                    } else {
                        System.out.println("Supply Found\n");
                        System.out.println(foundSupply);
                    }
                    break;
                case 3: 
                    System.out.println();
                    System.out.println("------ Add a Supply ------\n");
                    System.out.print("Enter supply name: ");
                    String name = input.nextLine();
                    System.out.print("Enter supply category: ");
                    String category = input.nextLine();
                    // Ensures these aren't left blank
                    if (name.isBlank() || category.isBlank()) {
                        System.out.println("\n-- Name and Category cannot be blank. Supply not added. --\n");
                        break;
                    }
                    System.out.print("Enter supply type (disposable or reusable): ");
                    String supplyType = input.nextLine();
                    // Error handling as this needs to be either disposable or reusable, nothing else
                    if (!supplyType.equalsIgnoreCase("Disposable") && !supplyType.equalsIgnoreCase("Reusable")) {
                        System.out.println("\n-- Invalid type. Supply not added. --\n");
                        break;
                    }
                    System.out.print("Enter quantity: ");
                    // Error handling, ensures a number is entered
                    if (!input.hasNextInt()) {
                        System.out.println("\n-- Invalid quantity. Supply not added. --\n");
                        input.nextLine(); // Clears input
                        break;
                    }
                    int currentQuantity = input.nextInt();
                    input.nextLine();
                    // Error handling, ensure quantity is above 0
                    if (currentQuantity < 0) {
                        System.out.println("\n-- Quantity can't be negative. Supply not added. --\n");
                        break;
                    }
                    System.out.print("Enter minimum quantity: ");
                    // Error handling, check to see if a number
                    if (!input.hasNextInt()) {
                        System.out.println("\n-- Invalid quantity. Supply not added. --\n");
                        input.nextLine();
                        break;
                    }
                    int minimumQuantity = input.nextInt();
                    input.nextLine();
                    // Error handling
                    if (minimumQuantity < 0) {
                        System.out.println("\n-- Quantity can't be negative. Supply not added. --\n");
                        break;
                    }
                    // Creating supply type object
                    // Polymorphism
                    Supply newSupply;
                    if (supplyType.equalsIgnoreCase("Disposable")) {
                        newSupply = new DisposableSupply(name, category, currentQuantity, minimumQuantity);
                    } else {
                        newSupply = new ReusableSupply(name, category, currentQuantity, minimumQuantity);
                    }
                    // Add supply to inventory list
                    inventoryManager.addSupply(newSupply);
                    break;
                case 4: 
                    System.out.println();
                    System.out.println("------ Update a Supply ------\n");
                    System.out.print("Enter supply Id: ");
                    // Ensures a number is entered
                    if (!input.hasNextInt()) {
                        System.out.println("\n-- Invalid ID. Please enter a number. --\n");
                        input.nextLine(); // Clears input
                        break;
                    }
                    // Declaring variable for user input
                    int updateId = input.nextInt();
                    input.nextLine();
                    // Searching for supply
                    Supply supply = inventoryManager.findSupply(updateId);
                    // If not found
                    if (supply == null) {
                        System.out.println("\nSupply not found.\n");
                        break;
                    }
                    // Displays supply info if found
                    System.out.println("\nSupply Information:\n");
                    System.out.println(supply);
                    System.out.println();
                    // Ask for the new quantity
                    System.out.print("Enter new quantity: ");
                    // Error hanndling
                    if (!input.hasNextInt()) {
                        System.out.println("\n-- Invalid quantity. Supply not updated. --\n");
                        input.nextLine();
                        break;
                    }
                    int updatedCurrentQuantity = input.nextInt();
                    input.nextLine();
                    // Error handling
                    if (updatedCurrentQuantity < 0) {
                        System.out.println("\n-- Quantity can't be negative. Supply not updated. --\n");
                        break;
                    }
                    // update existing supply info
                    supply.setCurrentQuantity(updatedCurrentQuantity);
                    // Saving it to list
                    inventoryManager.updateSupply(supply);
                    break;
                case 5: 
                    System.out.println();
                    System.out.println("------ Delete a Supply ------\n");
                    System.out.print("Enter supply Id: ");
                    // Ensures a number is entered
                    if (!input.hasNextInt()) {
                        System.out.println("\n-- Invalid ID. Please enter a number. --\n");
                        input.nextLine(); // Clears input
                        break;
                    }
                    // Declaring variable for user input
                    int deleteId = input.nextInt();
                    input.nextLine();
                    //  Checks if supple exists and deletes it
                    inventoryManager.deleteSupply(deleteId);
                    break;
                case 6: 
                    System.out.println();
                    System.out.println("------ View Supplies Low in Stock ------\n");
                    // Gets list of supplies low in stock
                    List<Supply> lowStockSupplies = inventoryManager.getLowStock();
                    // If no supplies are low in stock
                    if (lowStockSupplies.isEmpty()) {
                        System.out.println("\nNo supplies are currently low in stock.");
                    } else {
                        // If a list is returned with supplies
                        for (Supply lowStockSupply : lowStockSupplies) {
                            System.out.println(lowStockSupply);
                            System.out.println();
                        }
                    }
                    break;
                case 7:
                    System.out.println("\n----- Exiting program. -----\n");
                    break;
                default:
                    System.out.println("\n-- Invalid selection. You must enter a number 1-7. --\n");
            }
        }
        // Closes scanner
        input.close();
        conn.close();
    }
}
