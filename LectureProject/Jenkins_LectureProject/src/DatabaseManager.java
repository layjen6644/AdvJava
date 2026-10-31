/*
Name: Layla Jenkins
Date: 10/31/2026
Assignment: SDC330 - Lecture Project

I have changed how this class will operate. 
This class will manage the database operations using the connection created
by the SQLiteDatabase class. It will create a Supplies table and handle SQL
statements/operations to add, update, delete, and search for supplies in the database.
*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseManager {
    // Constructor
    public DatabaseManager() {
    }

    // Creates Supplies table if it doesn't exist
    public boolean createTable(Connection conn) {
        String sql =
        "CREATE TABLE IF NOT EXISTS Supplies (\n"
        + "   supplyId integer PRIMARY KEY AUTOINCREMENT\n"  // SQLite will auto-generate id
        // Forcing these to have values
        + "   ,name text not null\n"
        + "   ,category text not null\n"
        + "   ,supplyType text not null\n"
        + "   ,currentQuantity integer not null\n"
        + "   ,minimumQuantity integer not null);";

        // I am choosing not to print this as I think it clutters the output.

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

    // Adds a new supply to database
    public void addSupply(Connection conn, Supply supply) {
        String sql =
            "INSERT INTO Supplies(name, category, supplyType, currentQuantity, minimumQuantity) "
            + "VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            // Using getters from Supply to access fields since they are private
            pst.setString(1, supply.getName());
            pst.setString(2, supply.getCategory());
            pst.setString(3, supply.getSupplyType());
            pst.setInt(4, supply.getCurrentQuantity());
            pst.setInt(5, supply.getMinimumQuantity());
            pst.executeUpdate();
            // Since SQL is assigning an ID, the following will assign it to the supply object
            // I just created 
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid()");
            // This checks to see if a row 
            if (rs.next()) {
                supply.setSupplyId(rs.getInt(1));
            }
            // Closes database connection
            rs.close();
            stmt.close();
            pst.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Updates a supply using supplyId
    public void updateSupply(Connection conn, Supply supply) {
        // Sql statement with condition to locate specific record using supplyId
        String sql =
            "UPDATE Supplies SET name=?, category=?, supplyType=?, currentQuantity=?, minimumQuantity=? WHERE supplyId=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            // Using getters to access fields 
            pst.setString(1, supply.getName());
            pst.setString(2, supply.getCategory());
            pst.setString(3, supply.getSupplyType());
            pst.setInt(4, supply.getCurrentQuantity());
            pst.setInt(5, supply.getMinimumQuantity());
            pst.setInt(6, supply.getSupplyId());
            pst.executeUpdate();
            // Closes database connection
            pst.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Deletes a supply using supplyId
    public void deleteSupply(Connection conn, int supplyId) {
        String sql =
            "DELETE FROM Supplies WHERE supplyId=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, supplyId);
            pst.executeUpdate();
            // Closes connection
            pst.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Gets all supplies in inventory database
    public ArrayList<Supply> getAllSupplies(Connection conn) {
        ArrayList<Supply> supplies = new ArrayList<Supply>();
        String sql = 
            "SELECT * FROM Supplies";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                // Getting info from database and assinging it to a variable as Supply is an abstract class
                int supplyId = rs.getInt("supplyId");
                String name = rs.getString("name");
                String category = rs.getString("category");
                String supplyType = rs.getString("supplyType");
                int currentQuantity = rs.getInt("currentQuantity");
                int minimumQuantity = rs.getInt("minimumQuantity");
                // Declaring variable to hold Supply object
                Supply supply;
                // if statement to determine if supply is disposable or reusable
                if (supplyType.equals("Disposable")) {
                    supply = new DisposableSupply(supplyId, name, category, currentQuantity, minimumQuantity);
                } else {
                    supply = new ReusableSupply(supplyId, name, category, currentQuantity, minimumQuantity);
                }
                // Adding to ArrayList
                supplies.add(supply);
            }
            // Closes connection
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return supplies;
    }

    // Searches for supply using supplyId
    public Supply findSupply(Connection conn, int supplyId) {
        // Declaring variable and initializing it to null
        // Not creating an object yet as Supply class is abstract
        Supply supply = null;
        String sql = 
            "SELECT * FROM Supplies WHERE supplyId = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, supplyId);
            ResultSet rs = pst.executeQuery();
            // If statment to locate supply, if it matches, if not it will return null
            if (rs.next()) {
                int foundId = rs.getInt("supplyId");
                String name = rs.getString("name");
                String category = rs.getString("category");
                String supplyType = rs.getString("supplyType");
                int currentQuantity = rs.getInt("currentQuantity");
                int minimumQuantity = rs.getInt("minimumQuantity");
                // if statement to determine if supply is disposable or reusable and creating the Supply object
                // via the child classes
                if (supplyType.equals("Disposable")) {
                    supply = new DisposableSupply(foundId, name, category, currentQuantity, minimumQuantity);
                } else {
                    supply = new ReusableSupply(foundId, name, category, currentQuantity, minimumQuantity);
                }
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return supply;
    }

    // Getting supplies low in stock
    public ArrayList<Supply> getLowStock(Connection conn) {
        ArrayList<Supply> supplies = new ArrayList<>();
        String sql =
            "SELECT * FROM Supplies WHERE currentQuantity <= minimumQuantity";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            // Loops through and gathers supplies low in quantity
            while (rs.next()) {
                int supplyId = rs.getInt("supplyId");
                String name = rs.getString("name");
                String category = rs.getString("category");
                String supplyType = rs.getString("supplyType");
                int currentQuantity = rs.getInt("currentQuantity");
                int minimumQuantity = rs.getInt("minimumQuantity");
                // Delcaring variable
                Supply supply;
                // if statement to determine if supply is disposable or reusable and creating the Supply object
                // via the child classes
                if (supplyType.equals("Disposable")) {
                    supply = new DisposableSupply(supplyId, name, category, currentQuantity, minimumQuantity);
                } else {
                    supply = new ReusableSupply(supplyId, name, category, currentQuantity, minimumQuantity);
                }
                // Adding to ArrayList
                supplies.add(supply);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return supplies;
    }
}