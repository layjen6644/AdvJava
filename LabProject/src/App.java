/*
Name: Layla Jenkins
Date: 10/18/2026
Assignment: Java Lab Project: Part 2 - Week 2

Main application.
*/
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Layla Jenkins - Project Week 2");
        System.out.println(); // Blank line
        // Creates scanner for user input
        Scanner input = new Scanner(System.in);
        // Creating rolodex object
        Rolodex myRolodex = new Rolodex();
        // Adding instances
        Business b1 = new Business("Jane", "Doe", "555-345-8973", "JDoe@email.com", "Business", "Jane's Bakery");
        Business b2 = new Business("Alex", "Smith", "555-345-9012", "owner@smithconcrete.com", "Business", "Smith's Concrete");
        Family fam1 = new Family("Allie", "Brown", "555-345-1234", "alliebrown@email.com", "Family");
        Family fam2 = new Family("Bobby", "Brown", "555-345-3456", "None", "Family");
        Friend f1 = new Friend("Allen", "Jones", "555-345-7812", "a.jones@email.com", "Friend");
        Friend f2 = new Friend("Sam", "Frankenstien", "555-345-6622", "sam.frank@email.com", "Friend");

        // Adding contacts to rolodex
        myRolodex.addContact(b1);
        myRolodex.addContact(b2);
        myRolodex.addContact(fam1);
        myRolodex.addContact(fam2);
        myRolodex.addContact(f1);
        myRolodex.addContact(f2);

        // Welcome message
        System.out.println("Welcome to the Rolodex Management System");
        // Instructions
        System.out.println("Select an option from the menu below.");
        //Declaring variable for user input and initializing
        int choice = 0; 
        // Main menu, loop until user selects option to exit
        while (choice != 6) {
            System.out.println("1.  View All Contacts");
            System.out.println("2. Search Contacts by Last Name Initial");
            System.out.println("3. Add a New Contact");
            System.out.println("4. Update a Contact");
            System.out.println("5. Remove a Contact");
            System.out.println("6. Exit");
            System.out.println("Enter Selection: ");
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
                System.out.println("All Contacts");
                // Loop through contacts and orders them starting at 1
                for (int i = 0; i < contacts.size(); i++) {
                    System.out.println("\nContact "+ (i + 1));
                    // Demonstrates polymorphism by calling the toString() method of each contact object, which will invoke the appropriate overridden method based on the actual type of the object (Business, Family, or Friend).
                    System.out.println(contacts.get(i));
                }
            } else if (choice == 2) {
                System.out.println("Enter the first initial of the last name: ");
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
                    System.out.println("Matching Contacts");
                    // Loops through matching contacts to number them
                    for (int i = 0; i <matches.size(); i++) {
                        System.out.println("\nContact " + (i + 1));
                        System.out.println(matches.get(i));
                    }
                }
            } else if (choice == 3) {
                System.out.println("\nAdd a New Contact");
                System.out.println(); // Blank line
                System.out.print("Enter first name: ");
                String firstName = input.nextLine();
                System.out.print("Enter last name: ");
                String lastName = input.nextLine();
                System.out.print("Enter phone number: ");
                String phone = input.nextLine();
                System.out.print("Enter email address: ");
                String email = input.nextLine();
                System.out.print("Enter contact type (Business, Family, Friend): ");
                String contactType = input.nextLine();
                if (contactType.equalsIgnoreCase("Business")) {
                    System.out.print("Enter business name: ");
                    String businessName = input.nextLine();
                    Business newBusiness = new Business(firstName, lastName, phone, email, contactType, businessName);
                    myRolodex.addContact(newBusiness);
                    System.out.println("Contact added successfully.");
                } else if (contactType.equalsIgnoreCase("Family")) {
                    Family newFamily = new Family(firstName, lastName, phone, email, contactType);
                    myRolodex.addContact(newFamily);
                    System.out.println("Contact added successfully.");
                } else if (contactType.equalsIgnoreCase("Friend")) {
                    Friend newFriend = new Friend(firstName, lastName, phone, email, contactType);
                    myRolodex.addContact(newFriend);
                    System.out.println("Contact added successfully.");
                } else {
                    System.out.println("Invalid contact type. Contact not added.");
                }
                
            } else if (choice == 4) {
                System.out.println("\nUnder contstruction");
                System.out.println(); // Blank line

            } else if (choice == 5) {
                System.out.println("\nUnder construction");
                System.out.println(); // Blank line
            } else {
                System.out.println("Invalid selection. Try again.");
            }
        }
        input.close();
    }
}
