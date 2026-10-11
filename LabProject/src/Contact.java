/*
Name: Layla Jenkins
Date: 10/11/2026
Assigment: Java Lab Project: Part 2 - Week 1

This is the base/super class. It stores common contact information.
*/

public class Contact {
    // Class properties
    private String FirstName;
    private String LastName;
    private String Phone;
    private String Email;
    private String ContactType;

    // Constructor to set contact properties
    public Contact(String firstName, String lastName, String phone, String email, String contactType) {
        FirstName = firstName;
        LastName = lastName;
        Phone = phone;
        Email = email;
        ContactType = contactType;
    }

    // Getters and setters
    public String getFirstName() {
        return FirstName;
    }
    public void setFirstName(String firstName) {
        FirstName = firstName;
    }
    public String getLastName() {
        return LastName;
    }
    public void setLastName(String lastName) {
        LastName = lastName;
    }
    public String getPhone() {
        return Phone;
    }
    public void setPhone(String phone) {
        Phone = phone;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }
    public String getContactType() {
        return ContactType;
    }
    public void setContactType(String contactType) {
        ContactType = contactType;
    }

    // Override toString to display contact information
    @Override 
    public String toString() {
        return String.format("%s%s%n%s%s%n%s%s%n%s%s%n",
            "Name: ", FirstName + " " + LastName,
            "Phone: ", Phone,
            "Email: ", Email,
            "Contact Type: ", ContactType);
    }
}