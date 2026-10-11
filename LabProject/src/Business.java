/*
Name: Layla Jenkins
Date: 10/11/2026
Assigment: Java Lab Project: Part 2 - Week 1

This is a sub class. It will inherit properties and methods from the Contact (super) class.
*/

public class Business extends Contact {
    // Class property
    private String Company;

    // Constructor to set super and this class's properties
    public Business(String firstName, String lastName, String phone, String email, String contactType, String company) {
        super(firstName, lastName, phone, email, contactType);
        Company = company;
    }

    // Getters and setters
    public String getCompany() {
        return Company;
    }
    public void setCompany(String company) {
        Company = company;
    }

    // Overrides toString() to return contact info
    @Override 
    public String toString() {
        return String.format("%s%n%s%s",
            super.toString(),
            "Company Name: ", Company);
    }
}
