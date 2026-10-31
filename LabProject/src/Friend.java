/*
Name: Layla Jenkins
Date: 10/31/2026
Assignment: Java Lab Project: Part 4 - Week 4

This is a sub class. It will inherit properties and methods from the Contact (super) class.
*/

public class Friend extends Contact {
    // This last has no properties of its own
    // Constructor set properties of parent and this class. 
    public Friend(String firstName, String lastName, String phone, String email, String contactType) {
        super(firstName, lastName, phone, email, contactType);
    }

     // Constructor with no email
    public Friend(String firstName, String lastName, String phone, String contactType) {
        super(firstName, lastName, phone, "None",contactType);
    }

    // Implements abstract method from Contact class
    @Override
    public String toString() {
        return super.toStringBase();
    }
}