/*
Name: Layla Jenkins
Date: 10/11/2026
Assigment: Java Lab Project: Part 2 - Week 1

This is a composite class, it is made of objects from the Contact class and 
stores those objects in ArrayLists.
*/

import java.util.ArrayList;

public class Rolodex {
    // Class property
    private ArrayList<Contact> Contacts;

    // Constructor to create arraylist
    public Rolodex() {
        Contacts = new ArrayList<Contact>();
    }

    // Option 1 Gets full list of contacts
    public ArrayList<Contact> getContacts() {
        return Contacts;
    }

    // Option 2: Filter through contacts by last name initial
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
    public void addContact(Contact val) {
        Contacts.add(val);
    }
}