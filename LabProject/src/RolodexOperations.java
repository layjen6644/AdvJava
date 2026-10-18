/*
Name: Layla Jenkins
Date: 10/18/2026
Assignment: Java Lab Project: Part 2 - Week 2

Interface for Rolodex operations
*/
import java.util.ArrayList;

public interface RolodexOperations {
    void addContact(Contact contact);     // Adds a contact
    void updateContact(int index, Contact updatedContact); // Updates a contact at a indicated index location
    void removeContact(int index);    // Removes a contact at indicated index location
    ArrayList<Contact> getContacts();    // Gets all contacts
    ArrayList<Contact> getInitialFilter(char initial);  // finds a contact by initial of last name
}