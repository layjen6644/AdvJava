/*
Name: Layla Jenkins
Date: 10/23/2026
Assignment: SDC330 - Lecture Project

Main application class.
*/
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Layla Jenkins - Week 3 Lecture Project\n");

        // Scanner for user input
        Scanner input = new Scanner(System.in);
        // Creating database object
        DatabaseManager databaseManager = new DatabaseManager();
        // Creating object to use interface
        InventoryOperations inventoryManager = new InventoryManager(databaseManager);

        // Welcome message -- Title of application and instructions
        System.out.println("--- Dental Supply Inventory System ---");
        System.out.println("Select an option from the menu below.");
        // Declaring variable and initializing to zero
        int choice = 0;
        // Main menu, loops until user opts to exit
        while (choice != 7) {
            System.out.println("1. View all Supplies");
            System.out.println("2. Search for a supply");
            System.out.println("3. Add a supply");
            System.out.println("4. Update a supply");
            System.out.println("5. Delete a supply");
            System.out.println("6. View supplies low in stock");
            System.out.println("7. Exit");
            // Validating user input
            if (!input.hasNextInt()) {
                System.out.println("Invalid selection. Try again.");
                input.nextLine(); // Clearing input
                continue; // Goes back to start of loop
            }
            // Declaring variable for user input and clearing after reading it
            choice = input.nextInt();
            input.nextLine();

            // Switch/case based on user input
            switch (choice) {
                case 1: 
                    // Calling method to display supplies
                    List<Supply> supplies = inventoryManager.getViewSupplies();
                    // Error handling, if no supplies are found
                    if (supplies.isEmpty()) {
                        System.out.println("No supplies found.");
                    } else {
                        // Loops through list and displays it
                        for (Supply supply : supplies) {
                            System.out.println(supply);
                        }
                    }
                    break;
                case 2:
                    System.out.println("Enter supply ID: ");
                    // Declaring variable for user input
                    String searchId = input.nextLine();
                    // Calling method to search for id based on user input
                    Supply foundSupply = inventoryManager.findSupply(searchId);
                    // if statement to display result
                    if (foundSupply == null) {
                        System.out.println("Supply not found.");
                    } else {
                        System.out.println(foundSupply);
                    }
                    break;
                case 3: 
                    System.out.println("Enter supply ID: ");
                    // Declaring variable for user input
                    String supplyId = input.nextLine();
                    // Checks for duplicates
                    if (inventoryManager.findSupply(supplyId) != null) {
                        System.out.println("Supply ID already exists.");
                        break;
                    }
                    // Asks user to enter supply info if supply doesn't exist
                    System.out.println("Enter supply name: ");
                    String name = input.nextLine();
                    System.out.println("Enter category: ");
                    String category = input.nextLine();
                    System.out.println("Enter supply type (disposable or reusable): ");
                    String supplyType = input.nextLine();
                    // Error handling
                    if (!supplyType.equalsIgnoreCase("Disposable") && !supplyType.equalsIgnoreCase("Reusable")) {
                        System.out.println("Invalid type. Supply not added.");
                        break;
                    }
                    System.out.println("Enter quantity: ");
                    //Error handling
                    if (!input.hasNextInt()) {
                        System.out.println("Invalid quantity. Supply not added.");
                        input.nextLine();
                        break;
                    }
                    int currentQuantity = input.nextInt();
                    input.nextLine();
                    // error handling
                    if (currentQuantity < 0) {
                        System.out.println("Quantity can't be negative. Supply not added.");
                        break;
                    }
                    System.out.println("Enter minimum quantity: ");
                    // error handling
                    if (!input.hasNextInt()) {
                        System.out.println("Invalid quantity. Supply not added.");
                        input.nextLine();
                        break;
                    }
                    int minimumQuantity = input.nextInt();
                    input.nextLine();
                    // error handling
                    if (minimumQuantity < 0) {
                        System.out.println("Quantity can't be negative. Supply not added.");
                        break;
                    }
                    // Creating supply type object
                    // Polymorphism
                    Supply newSupply;
                    if (supplyType.equalsIgnoreCase("Disposable")) {
                        newSupply = new DisposableSupply(supplyId, name, category, currentQuantity, minimumQuantity);
                    } else {
                        newSupply = new ReusableSupply(supplyId, name, category, currentQuantity, minimumQuantity);
                    }
                    // Add supply to inventory list
                    inventoryManager.addSupply(newSupply);
                    break;
                case 4: 
                    System.out.println("Enter supply Id: ");
                    // Declaring variable for user input
                    String updateId = input.nextLine();
                    // Searching for supply
                    Supply supply = inventoryManager.findSupply(updateId);
                    // If not found
                    if (supply == null) {
                        System.out.println("Supply not found.");
                        break;
                    }
                    // Displays supply info if found
                    System.out.println("Supply Information:\n");
                    System.out.println(supply);
                    // Ask for the new quantity
                    System.out.println("Enter new quantity: ");
                    // Error hanndling
                    if (!input.hasNextInt()) {
                        System.out.println("Invalid quantity. Supply not updated.");
                        input.nextLine();
                        break;
                    }
                    int updatedCurrentQuantity = input.nextInt();
                    input.nextLine();
                    // Error handling
                    if (updatedCurrentQuantity < 0) {
                        System.out.println("Quantity can't be negative. Supply not updated.");
                        break;
                    }
                    // update existing supply info
                    supply.setCurrentQuantity(updatedCurrentQuantity);
                    // Saving it to list
                    inventoryManager.updateSupply(supply);
                    break;
                case 5: 
                    System.out.println("Enter supply Id: ");
                    // Declaring variable for user input
                    String deleteId = input.nextLine();
                    //  Checks if suppl exists and deletes it
                    inventoryManager.deleteSupply(deleteId);
                    break;
                case 6: 
                    // Gets list of supplies low in stock
                    List<Supply> lowStockSupplies = inventoryManager.getLowStock();
                    // If no supplies are low in stock
                    if (lowStockSupplies.isEmpty()) {
                        System.out.println("No supplies are currently low in stock.");
                    }
                    // If a list is returned with supplies
                    for (Supply lowStockSupply : lowStockSupplies) {
                        System.out.println(lowStockSupply);
                    }
                    break;
                case 7:
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid selection. Please choose 1-7.");
            }
        }
        // Closes scanner
        input.close();
    }
}
