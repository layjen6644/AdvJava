/*
Name: Layla Jenkins
Date: 10/31/2026
Assignment: Java Lab Project: Part 4 - Week 4

Main application.
*/
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Layla Jenkins - Project Week 4");
        System.out.println(); // Blank line
        final String dbName = "Rolodex.db";
        // Creates scanner for user input
        Scanner input = new Scanner(System.in);
        // Creates connection to SQLite database
        Connection conn = SQLiteDatabase.connect(dbName);
        // Using interface to access methods implemented by Rolodex
        RolodexOperations myRolodex = new Rolodex(conn);
        
        // Welcome message
        System.out.println("----   Welcome to the Rolodex Management System  ----\n");
    
        //Declaring variable for user input and initializing
        int choice = 0; 
        // Main menu, loop until user selects option to exit
        while (choice != 6) {
            // Instructions
            System.out.println("\nSelect an option from the menu below.\n");
            System.out.println("   1.  View All Contacts");
            System.out.println("   2. Search Contacts by Last Name Initial");
            System.out.println("   3. Add a New Contact");
            System.out.println("   4. Update a Contact");
            System.out.println("   5. Remove a Contact");
            System.out.println("   6. Exit");
            System.out.print("\nEnter Selection: ");
            // Validating user input
            if (!input.hasNextInt()) {
                System.out.println("\n-- Invalid selection. Try again. --\n");
                input.nextLine(); // Clear the invalid input
                continue; // Go back to the start of the loop   
            }
            // Declaring variable for user input and clearing after reading user input
            choice = input.nextInt();
            input.nextLine();

            // If statements based on user selection
            // Option 1: View all contacts
            if (choice == 1) {
                ArrayList<Contact> contacts = myRolodex.getContacts();
                System.out.println("\n-- All Contacts --\n");
                // If no contacts
                if (contacts.size() == 0) {
                    System.out.println("\nNo contacts found.\n");
                } else {
                    // Loop through contacts and orders them starting at 1
                    for (int i = 0; i < contacts.size(); i++) {
                        System.out.println("\nContact "+ (i + 1) + ":");
                        // Demonstrates polymorphism by calling the toString() method of each contact object, which will invoke the appropriate overridden method based on the actual type of the object (Business, Family, or Friend).
                        System.out.println(contacts.get(i));
                    }
                }
            
            // Option 2: Search by initial of last name
            } else if (choice == 2) {
                System.out.println("\n-- Search for a Contact --\n");
                System.out.print("\nEnter the first initial of the last name: ");
                String initial = input.nextLine();
                // Checks if user entered 1 letters -- Error handling
                if (initial.length() != 1 || !Character.isLetter(initial.charAt(0))) {
                    System.out.println("\n-- Error. Try again. --\n");
                    continue;
                }
                // Gets matching contacts
                ArrayList<Contact> matches = myRolodex.getInitialFilter(initial.charAt(0));
                // Checks to see if there is matches
                if (matches.size() == 0) {
                    System.out.println("\nNo Contacts Found.\n");
                } else {
                    System.out.println("\n-- Matching Contacts --\n");
                    // Loops through matching contacts to number them
                    for (int i = 0; i <matches.size(); i++) {
                        System.out.println("\nContact " + (i + 1));
                        System.out.println(matches.get(i));
                    }
                }

            // Option 3: Add a contact
            } else if (choice == 3) {
                System.out.println("\n-- Add a New Contact --\n");
                System.out.print("Enter first name: ");
                String firstName = input.nextLine();
                System.out.print("Enter last name: ");
                String lastName = input.nextLine();
                System.out.print("Enter phone number: ");
                String phone = input.nextLine();
                System.out.print("Enter email address: ");
                String email = input.nextLine();
                if (email.isEmpty()) {
                    email = "None";
                }
                System.out.print("Enter contact type (Business, Family, Friend): ");
                String contactType = input.nextLine();
                if (contactType.equalsIgnoreCase("Business")) {
                    System.out.print("Enter business name: ");
                    String businessName = input.nextLine();
                    Business newBusiness = new Business(firstName, lastName, phone, email, contactType, businessName);
                    myRolodex.addContact(newBusiness);
                } else if (contactType.equalsIgnoreCase("Family")) {
                    Family newFamily = new Family(firstName, lastName, phone, email, contactType);
                    myRolodex.addContact(newFamily);
                } else if (contactType.equalsIgnoreCase("Friend")) {
                    Friend newFriend = new Friend(firstName, lastName, phone, email, contactType);
                    myRolodex.addContact(newFriend);
                } else {
                    System.out.println("Invalid contact type. Contact not added.");
                }
            
            // Option 4: Update a contact
            } else if (choice == 4) {
                System.out.println("\n-- Update a Contact --\n");
                ArrayList<Contact> contacts = myRolodex.getContacts();
                // checks to see if there are contacts in list
                if (contacts.size() == 0) {
                    System.out.println("\n-- No contacts available to update. --\n");
                } else {
                    // Displays contacts using temporary list numbers, not the actual contactId
                    // And displaying the first and last names only instead of all the contact info
                    for (int i = 0; i < contacts.size(); i++) {
                        System.out.println((i + 1) + ". " 
                            + contacts.get(i).getFirstName() + " "
                            + contacts.get(i).getLastName());
                        
                    }
                    // Asks for user's input for which contact to update
                    System.out.print("\nSelect a contact to update:");
                    // Error handling, checks to see if a number was entered
                    if (!input.hasNextInt()) {
                        System.out.println("Invalid selection. Try again.");
                        input.nextLine();
                        continue; // Go back to the start of the loop
                    }
                    int index = input.nextInt() -1;
                    input.nextLine();
                    // Checks to see if the index is valid
                    if (index < 0 || index >= contacts.size()) {
                        System.out.println("Invalid contact number. Try again.");
                    } else {
                        // Displays contact info
                        Contact selectedContact = contacts.get(index);
                        System.out.println("\n- Selected Contact: -\n");
                        System.out.println(selectedContact);
                        // Asks user what info they want to update
                        System.out.println("\nWhat information would you like to update?");
                        System.out.println("1. First Name");
                        System.out.println("2. Last Name");
                        System.out.println("3. Phone Number");
                        System.out.println("4. Email Address");
                        // If it's a business contact
                        if (selectedContact instanceof Business) {
                            System.out.println("5. Company Name");
                        }
                        System.out.print("\nEnter your selection: ");
                        // Error handling, checks to see if a number was entered
                        if (!input.hasNextInt()) {
                            System.out.println("Invalid selection. Try again.");
                            input.nextLine();
                            continue; // Go back to the start of the loop
                        }
                        int updateChoice = input.nextInt();
                        input.nextLine();
                        // Update the information the user selected
                        if (updateChoice == 1) {
                            System.out.print("Enter new first name: ");
                            selectedContact.setFirstName(input.nextLine());
                        } else if (updateChoice == 2) {
                            System.out.print("Enter new last name: ");
                            selectedContact.setLastName(input.nextLine());
                        } else if (updateChoice == 3) {
                            System.out.print("Enter new phone number: ");
                            selectedContact.setPhone(input.nextLine());
                        } else if (updateChoice == 4) {
                            System.out.print("Enter new email address: ");
                            selectedContact.setEmail(input.nextLine());
                        } else if (updateChoice == 5 && selectedContact instanceof Business) {
                            System.out.print("Enter new company name: ");
                            String newCompany = input.nextLine();
                            ((Business) selectedContact).setCompany(newCompany);
                        } else {
                            System.out.println("Invalid selection. No changes made.");
                            continue; // Go back to the start of the loop
                        }
                        // Saves info to database
                        myRolodex.updateContact(selectedContact.getContactId(), selectedContact);
                        System.out.println("\n-- Contact updated successfully. --\n");
                    }
                }
            
            // Option 5: Remove contact
            } else if (choice == 5) {
                System.out.println("\n-- Remove a Contact --\n");
                // Get list of contacts
                ArrayList<Contact> contacts = myRolodex.getContacts();
                // Checks to see if there are contacts in list
                if (contacts.size() == 0) {
                    System.out.println("\nNo contacts available to remove.\n");
                } else {
                    // Displays contacts with temporary list of numbers, not the contactId
                    System.out.println("\nSelect a contact to remove:\n");
                    for (int i = 0; i < contacts.size(); i++) {
                        System.out.println((i + 1) + ". " 
                              + contacts.get(i).getFirstName() + " "
                              + contacts.get(i).getLastName());
                    }
                    // Asks for user's input for which contact to remove
                    System.out.print("\nEnter the contact number to remove: ");
                    // Error handling, checks to see if a number was entered
                    if (!input.hasNextInt()) {
                        System.out.println("\n-- Invalid selection. Try again. --\n");
                        input.nextLine();
                        continue; // Go back to the start of the loop
                    }
                    int index = input.nextInt() - 1;
                    input.nextLine();
                    // Checks to see if the index is valid
                    if (index < 0 || index >= contacts.size()) {
                        System.out.println("\n-- Invalid contact number. Try again. --\n");
                    } else {
                        // Gets selected contact info
                        Contact selectedContact = contacts.get(index);
                        // Declaring variable for first and last name together
                        String contactName = selectedContact.getFirstName() + " " + selectedContact.getLastName();
                        // Asks user to confirm if they want to delete
                        System.out.print("\nAre you sure you want to remove " + contactName + "? (Y/N): ");
                        // Declaring variable for user input
                        String confirm = input.nextLine();
                        if (confirm.equalsIgnoreCase("Y")) {
                            // Get contactId from database
                            int contactId = selectedContact.getContactId();
                            //Remove from database
                            myRolodex.removeContact(contactId);
                            System.out.println("\n-- Contact removed successfully. --\n");
                        } else {
                            System.out.println("\nMission aborted. Contact not removed.\n");
                        }
                    }
                }

            // Option 6: Exit
            } else if (choice == 6) {
                System.out.println("\n-- Exiting the Rolodex Management System. Goodbye! --");
            } else {
                System.out.println("\n-- Invalid selection. Try again. --");
            }
        }
        input.close();
        conn.close();
    }
}
