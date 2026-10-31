/*
Name: Layla Jenkins
Date: 10/31/2026
Assignment: Java Lab Project: Part 4 - Week 4

Interface for Rolodex operations. Holds methods that will be implemented in the Rolodex class.
*/
import java.util.ArrayList;

public interface RolodexOperations {
    void addContact(Contact contact);     // Adds a contact
    void updateContact(int contactId, Contact updatedContact); // Updates a contact at a indicated index location
    void removeContact(int contactId);    // Removes a contact at indicated index location
    ArrayList<Contact> getContacts();    // Gets all contacts
    ArrayList<Contact> getInitialFilter(char initial);  // finds a contact by initial of last name
}