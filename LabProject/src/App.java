/*
Name: Layla Jenkins
Date: 10/11/2026
Assigment: Java Lab Project: Part 2 - Week 1

Main application.
*/
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Layla Jenkins - Project Week 1");
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
        while (choice != 4) {
            System.out.println("1.  View All Contacts");
            System.out.println("2. Search Contacts by Last Name Initial");
            System.out.println("3. Add a New Contact");
            System.out.println("4. Exit");
            System.out.println("Enter Selection: ");
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
                System.out.println("Under Construction");
                System.out.println(); // Blank line
            } else if (choice == 4) {
                System.out.println("Exiting Program");
                System.out.println(); // Blank line
            } else {
                System.out.println("Invalid selection. Try again.");
            }
        }
        input.close();
    }
}
