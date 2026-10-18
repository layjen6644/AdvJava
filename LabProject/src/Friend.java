/*
Name: Layla Jenkins
Date: 10/18/2026
Assignment: Java Lab Project: Part 2 - Week 2

This is a sub class. It will inherit properties and methods from the Contact (super) class.
*/

public class Friend extends Contact {
    // This last has no properties of its own
    // Constructor set properties of parent and this class. 
    public Friend(String firstName, String lastName, String phone, String email, String contactType) {
        super(firstName, lastName, phone, email, contactType);
    }
}