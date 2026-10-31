/*
Name: Layla Jenkins
Date: 10/31/2026
Assignment: Assignment: Java Lab Project: Part 4 Week 4

This class will manage the database operations using the connection created by the SQLiteDatabase class. 
It will create a Contacts table and handle SQL statements/operations to add, update, delete, and search 
for contacts in the database.
*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RolodexDb {
    // Constructor
    public RolodexDb() {
    }
    
    // Creates a Contacts table if one doesn't exist
    public boolean createTable(Connection conn) {
        String sql = 
            "CREATE TABLE IF NOT EXISTS Contacts (\n"
            + "  contactId INTEGER PRIMARY KEY AUTOINCREMENT\n"
            + "  ,firstName varchar(20)\n"
            + "  ,lastName  varchar(50)\n"
            + "  ,phone varchar(12)\n"
            + "  ,email varchar(50)\n"
            + "  ,contactType varchar(8)\n"
            + "  ,company varchar(50)\n"
            + ")";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            stmt.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // Adds a contact to database
    public void addContact(Connection conn, Contact contact) {
        String sql = 
            "INSERT INTO Contacts(firstName, lastName, phone, email, contactType, company) "
            + "VALUES(?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            // Using getters from Contact since the fields are private
            pst.setString(1, contact.getFirstName());
            pst.setString(2, contact.getLastName());
            pst.setString(3, contact.getPhone());
            pst.setString(4, contact.getEmail());
            pst.setString(5, contact.getContactType());

            // If contactType is a business, will add company name
            if (contact instanceof Business) {
                Business business = (Business) contact;
                pst.setString(6, business.getCompany());
            } else {
                pst.setString(6, null);
            }
            pst.executeUpdate();
            // The following will assign and retrieve contactId for the object I just created
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid()");
            if (rs.next()) {
                contact.setContactId(rs.getInt(1));
            }
            // Closes database connection
            rs.close();
            stmt.close();
            pst.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Updates a contact using contactId
    public void updateContact(Connection conn, Contact contact) {
        String sql =
            "UPDATE Contacts SET firstName=?, lastName=?, phone=?, email=?, contactType=?, company=? WHERE contactId=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            // Using getters to access fields
            pst.setString(1, contact.getFirstName());
            pst.setString(2, contact.getLastName());
            pst.setString(3, contact.getPhone());
            pst.setString(4, contact.getEmail());
            pst.setString(5, contact.getContactType());
            
            // Updates company name if applicable
            if (contact instanceof Business) {
                Business business = (Business) contact;
                pst.setString(6, business.getCompany());
            } else {
                pst.setString(6, null);
            }

            pst.setInt(7, contact.getContactId());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Deletes a contact using contactId
    public void removeContact(Connection conn, int contactId) {
        String sql = 
            "DELETE FROM Contacts WHERE contactId=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, contactId);
            pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Get all contacts
    public ArrayList<Contact> getAllContacts(Connection conn) {
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        String sql =
            // Requesting contacts come back in alaphebetical order by last name, then first name
            // Upper and lower cases accepted
            "SELECT * FROM Contacts ORDER BY lastName COLLATE NOCASE, firstName COLLATE NOCASE";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int contactId = rs.getInt("contactId");
                String lastName = rs.getString("lastName");
                String firstName = rs.getString("firstName");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String contactType = rs.getString("contactType");
                String company = rs.getString("company");
                // Declaring variable to hold Contact object
                Contact contact;
                // Creates different objects based on contact type
                if (contactType.equalsIgnoreCase("Business")) {
                    contact = new Business(firstName, lastName, phone, email, contactType, company);
                } else if (contactType.equalsIgnoreCase("FamilY")) {
                    contact = new Family(firstName, lastName, phone, email, contactType);
                } else {
                    contact = new Friend(firstName, lastName, phone, email, contactType);
                }
                contact.setContactId(contactId);
                contacts.add(contact);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return contacts;
    }

    // Searches for contact by last name initital
    public ArrayList<Contact> getContactByInitial(Connection conn, char initial) {
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        String sql =
            "SELECT * FROM Contacts WHERE lastName LIKE ? ORDER BY lastName COLLATE NOCASE, firstName COLLATE NOCASE";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            // searches for last names with initial that user entered
            pst.setString(1, initial + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int contactId = rs.getInt("contactId");
                String lastName = rs.getString("lastName");
                String firstName = rs.getString("firstName");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String contactType = rs.getString("contactType");
                String company = rs.getString("company");
                // Declaring variable to hold Contact object
                Contact contact;
                // Creates different objects based on contact type
                if (contactType.equalsIgnoreCase("Business")) {
                    contact = new Business(firstName, lastName, phone, email, contactType, company);
                } else if (contactType.equalsIgnoreCase("FamilY")) {
                    contact = new Family(firstName, lastName, phone, email, contactType);
                } else {
                    contact = new Friend(firstName, lastName, phone, email, contactType);
                }
                contact.setContactId(contactId);
                contacts.add(contact);
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return contacts;
    }

}
