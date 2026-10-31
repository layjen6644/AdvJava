/*
Name: Layla Jenkins
Date: 10/31/2026
Assignment: Java Lab Project: Part 4 - Week 4

This is a composite class that uses objects from the Contact class and manages those objects 
throughs the RolodexDb class. it also implements the RolodexOperations interface, which defines
methods that it must implement in this class
*/

import java.sql.Connection;
import java.util.ArrayList;

public class Rolodex implements RolodexOperations {
    // Class properties
    private Connection conn;
    // Composition: Creating RolodexDb object so I can use the methods in it
    private RolodexDb rolodexDb;

    // Constructor 
    public Rolodex(Connection conn) {
        this.conn = conn;
        this.rolodexDb = new RolodexDb();
        // Creates table if it doesn't exist
        this.rolodexDb.createTable(conn);
    }

    // Option 1 Gets full list of contacts
    @Override
    public ArrayList<Contact> getContacts() {
        return rolodexDb.getAllContacts(conn);
    }

    // Option 2: Filter through contacts by last name initial
    @Override 
    public ArrayList<Contact> getInitialFilter(char initial) {
        return rolodexDb.getContactByInitial(conn, initial);
    }

    // Option 3: Adds a new contact 
    @Override
    public void addContact(Contact contact) {
        // Gets all contacts 
        ArrayList<Contact> contacts = getContacts();
        // Checks if contact already exists
        for (Contact existingContact : contacts) {
            // Compares first and last names and phone number
            if (existingContact.getFirstName().equalsIgnoreCase(contact.getFirstName())
               && existingContact.getLastName().equalsIgnoreCase(contact.getLastName())
               && existingContact.getPhone().equalsIgnoreCase(contact.getPhone())) {
               System.out.println("\nContact already exists.\n");
               return;
            }   
        } 
        // Adds to rolodex if contact wasn't found
        rolodexDb.addContact(conn, contact);
        System.out.println("\n-- Contact added successfully. --");
        return;
    }

    // Option 4: Updates contact 
    @Override 
    public void updateContact(int contactId, Contact updatedContact) {
        // Keeps original contactId from database after updating
        updatedContact.setContactId(contactId);
        // updates database
        rolodexDb.updateContact(conn, updatedContact);
    }

    // Option 5: Removes contact 
    @Override 
    public void removeContact(int contactId) {
        rolodexDb.removeContact(conn, contactId);
    }
}
