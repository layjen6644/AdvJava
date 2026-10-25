/*
Name: Layla Jenkins
Date: 10/25/2026
Assignment: Java Lab Project: Part 3 - Week 3

Main application.
*/
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Layla Jenkins - Project Week 3");
        System.out.println(); // Blank line
        // Creates scanner for user input
        Scanner input = new Scanner(System.in);
        // Using interface to access methods implemented by Rolodex
        RolodexOperations myRolodex = new Rolodex();
        // Adding instances
        Business b1 = new Business("Jane", "Doe", "555-345-8973", "JDoe@email.com", "Business", "Jane's Bakery");
        Business b2 = new Business("Alex", "Smith", "555-345-9012", "owner@smithconcrete.com", "Business", "Smith's Concrete");
        Family fam1 = new Family("Allie", "Brown", "555-345-1234", "alliebrown@email.com", "Family");
        Family fam2 = new Family("Bobby", "Brown", "555-345-3456", "abrown@email.com", "Family");
        Friend f1 = new Friend("Allen", "Jones", "555-345-7812", "a.jones@email.com", "Friend");
        Friend f2 = new Friend("Sam", "Frankenstien", "555-345-6622", "sam.frank@email.com", "Friend");
        // Adding an instance with no email
        Friend f3 = new Friend("Sally", "Anderson", "555-345-8811", "Friend");
        // Adding contacts to rolodex
        myRolodex.addContact(b1);
        myRolodex.addContact(b2);
        myRolodex.addContact(fam1);
        myRolodex.addContact(fam2);
        myRolodex.addContact(f1);
        myRolodex.addContact(f2);
        myRolodex.addContact(f3);

        // Welcome message
        System.out.println("---   Welcome to the Rolodex Management System  ---\n");
    
        //Declaring variable for user input and initializing
        int choice = 0; 
        // Main menu, loop until user selects option to exit
        while (choice != 6) {
            // Instructions
            System.out.println("\nSelect an option from the menu below.");
            System.out.println("1.  View All Contacts");
            System.out.println("2. Search Contacts by Last Name Initial");
            System.out.println("3. Add a New Contact");
            System.out.println("4. Update a Contact");
            System.out.println("5. Remove a Contact");
            System.out.println("6. Exit");
            System.out.print("Enter Selection: ");
            // Validating user input
            if (!input.hasNextInt()) {
                System.out.println("Invalid selection. Try again.");
                input.nextLine(); // Clear the invalid input
                continue; // Go back to the start of the loop   
            }
            // Declaring variable for user input and clearing after reading user input
            choice = input.nextInt();
            input.nextLine();

            // If statements based on user selection
            if (choice == 1) {
                ArrayList<Contact> contacts = myRolodex.getContacts();
                System.out.println("\n-- All Contacts --");
                // Loop through contacts and orders them starting at 1
                for (int i = 0; i < contacts.size(); i++) {
                    System.out.println("\n- Contact "+ (i + 1) + " -");
                    // Demonstrates polymorphism by calling the toString() method of each contact object, which will invoke the appropriate overridden method based on the actual type of the object (Business, Family, or Friend).
                    System.out.println(contacts.get(i));
                }
            } else if (choice == 2) {
                System.out.println("\n-- Search for a Contact --");
                System.out.print("\nEnter the first initial of the last name: ");
                String initial = input.nextLine();
                // Checks if user entered 1 letters -- Error handling
                if (initial.length() != 1 || !Character.isLetter(initial.charAt(0))) {
                    System.out.println("Error. Try again.");
                    continue;
                }
                // Gets matching contacts
                ArrayList<Contact> matches = myRolodex.getInitialFilter(initial.charAt(0));
                // Checks to see if there is matches
                if (matches.size() == 0) {
                    System.out.println("No Contacts Found.");
                } else {
                    System.out.println("\n-- Matching Contacts --");
                    // Loops through matching contacts to number them
                    for (int i = 0; i <matches.size(); i++) {
                        System.out.println("\nContact " + (i + 1));
                        System.out.println(matches.get(i));
                    }
                }
            } else if (choice == 3) {
                System.out.println("\n-- Add a New Contact --");
                System.out.print("\nEnter first name: ");
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
                    System.out.println("\n-- Contact added successfully. --");
                } else if (contactType.equalsIgnoreCase("Family")) {
                    Family newFamily = new Family(firstName, lastName, phone, email, contactType);
                    myRolodex.addContact(newFamily);
                    System.out.println("\n-- Contact added successfully. --");
                } else if (contactType.equalsIgnoreCase("Friend")) {
                    Friend newFriend = new Friend(firstName, lastName, phone, email, contactType);
                    myRolodex.addContact(newFriend);
                    System.out.println("\n-- Contact added successfully. --");
                } else {
                    System.out.println("Invalid contact type. Contact not added.");
                }
                
            } else if (choice == 4) {
                System.out.println("\n-- Update a Contact --");
                ArrayList<Contact> contacts = myRolodex.getContacts();
                // checks to see if there are contacts in list
                if (contacts.size() == 0) {
                    System.out.println("No contacts available to update.");
                } else {
                    // Displays contacts with index numbers 
                    System.out.print("\nSelect a contact to update:");
                    for (int i = 0; i < contacts.size(); i++) {
                        System.out.println("\nContact " + (i + 1));
                        System.out.println(contacts.get(i));
                    }
                    // Asks for user's input for which contact to update
                    System.out.print("\nEnter the contact number to update: ");
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
                        System.out.println("\n-- Contact updated successfully. --");
                    }
                }

            } else if (choice == 5) {
                System.out.println("\n-- Remove a Contact --");
                // Get list of contacts
                ArrayList<Contact> contacts = myRolodex.getContacts();
                // Checks to see if there are contacts in list
                if (contacts.size() == 0) {
                    System.out.println("No contacts available to remove.");
                } else {
                    // Displays contacts with index numbers 
                    System.out.println("\nSelect a contact to remove:");
                    for (int i = 0; i < contacts.size(); i++) {
                        System.out.println("\nContact " + (i + 1));
                        System.out.println(contacts.get(i));
                    }
                    // Asks for user's input for which contact to remove
                    System.out.print("\nEnter the contact number to remove: ");
                    // Error handling, checks to see if a number was entered
                    if (!input.hasNextInt()) {
                        System.out.println("Invalid selection. Try again.");
                        input.nextLine();
                        continue; // Go back to the start of the loop
                    }
                    int index = input.nextInt() - 1;
                    input.nextLine();
                    // Checks to see if the index is valid
                    if (index < 0 || index >= contacts.size()) {
                        System.out.println("Invalid contact number. Try again.");
                    } else {
                        System.out.println("\nRemoving contact...");
                        // Removes the contact from the Rolodex
                        myRolodex.removeContact(index);
                        System.out.println("\n-- Contact removed successfully. --");
                    }
                }
            } else if (choice == 6) {
                System.out.println("\n-- Exiting the Rolodex Management System. Goodbye! --");
            } else {
                System.out.println("\n-- Invalid selection. Try again. --");
            }
        }
        input.close();
    }
}
