/*
Name: Layla Jenkins
Date: 10/31/2026
Assignment: Java Lab Project: Part 4 - Week 4

This is a sub class. It will inherit properties and methods from the Contact (super) class.
*/

public class Business extends Contact {
    // Class property
    private String company;

    // Constructor to set super and this class's properties
    public Business(String firstName, String lastName, String phone, String email, String contactType, String company) {
        super(firstName, lastName, phone, email, contactType);
        this.company = company;
    }

    // Constructor with no email
    public Business(String firstName, String lastName, String phone, String contactType, String company) {
        super(firstName, lastName, phone, "None", contactType);
        this.company = company;
    }

    // Getters and setters
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }

    // Implements abstract method from Contact class
    @Override 
    public String toString() {
        return String.format("%s%n%s%s",
            super.toStringBase(),
            "  Company Name: ", company);
    }
}
