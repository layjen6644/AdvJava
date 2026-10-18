/*
Name: Layla Jenkins
Date: 10/18/2026
Assignment: Java Lab Project: Part 2 - Week 2

This is a composite class, it is made of objects from the Contact class and 
stores those objects in ArrayLists.
*/

import java.util.ArrayList;

public class Rolodex implements RolodexOperations {
    // Class property
    private ArrayList<Contact> Contacts;

    // Constructor to create arraylist
    public Rolodex() {
        Contacts = new ArrayList<Contact>();
    }

    // Option 1 Gets full list of contacts
    @Override
    public ArrayList<Contact> getContacts() {
        return Contacts;
    }

    // Option 2: Filter through contacts by last name initial
    @Override 
    public ArrayList<Contact> getInitialFilter(char initial) {
        ArrayList<Contact> matches = new ArrayList<Contact>();
        // Filters through contact list and including upper and lower 
        for (Contact contact : Contacts) {
            if (Character.toUpperCase(contact.getLastName().charAt(0)) == Character.toUpperCase(initial)) {
                matches.add(contact);
            }
        }
        return matches;
    }

    // Adds a new contact using Contact object
    @Override
    public void addContact(Contact contact) {
        Contacts.add(contact);
    }

    // Updates contact at index location with new contact information
    @Override 
    public void updateContact(int index, Contact updatedContact) {
        if (index >= 0 && index < Contacts.size()) {
            Contacts.set(index, updatedContact);
        } else {
            System.out.println("Invalid index. Contact not updated.");
        }
    }

    // Removes contact at index location
    @Override 
    public void removeContact(int index) {
        if (index >= 0 && index < Contacts.size()) {
            Contacts.remove(index);
        } else {
            System.out.println("Invalid index. Contact not removed.");
        }
    }
}
