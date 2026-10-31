/*
Name: Layla Jenkins
Date: 10/31/2026
Assignment: Java Lab Project: Part 4 - Week 4

This is the abstract base/super class. It stores common shared contact information.
*/

public abstract class Contact {
    // Private properties
    private int contactId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String contactType;

    // Constructor to set shared/common contact properties
    public Contact(String firstName, String lastName, String phone, String email, String contactType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.contactType = contactType;
    }

    // Constructor to use using 4 parameters instead of six. Using "none" when email isn't provided
    public Contact(String firstName, String lastName, String phone, String contactType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = "None";
        this.contactType = contactType;
    }

    // Getters and setters
    public int getContactId() {
        return contactId;
    }
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getContactType() {
        return contactType;
    }
    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    // Override toString to display contact information
    // Will be protected so that subclasses can use it without it being public
   protected String toStringBase() {
        return String.format("%s%s%n%s%s%n%s%s%n%s%s",
            "  Name: ", firstName + " " + lastName,
            "  Phone: ", phone,
            "  Email: ", email,
            "  Contact Type: ", contactType);
    }

    // Abstract toString method to be implemented in subclasses
    @Override
    public abstract String toString();
}