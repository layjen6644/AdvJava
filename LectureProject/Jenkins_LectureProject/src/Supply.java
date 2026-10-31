/*
Name: Layla Jenkins
Date: 10/31/2026
Assignment: SDC330 - Lecture Project

This class is the abstract parent (super) class to the DisposableSupply and ReusableSupply classes.
It contains shared properties and methods for the subclasses. It has an abstract method
for the subclasses to specify if a supply is disposable or reusable. It also has a boolean
method to determine if a supply is low in stock. Lastly, it has a toString override to display
formatted info.
*/

public abstract class Supply {
    // Properties, I intend for none of these fields to contain null values. I want my application
    // to contain all information to be able to manage inventory reliably. My int data types
    // can contain 0s, but not negative or null values. 
    private int supplyId;
    private String name;
    private String category;
    private int currentQuantity;
    private int minimumQuantity;

    // Constructor
    public Supply(int supplyId, String name, String category, int currentQuantity, int minimumQuantity) {
        this.supplyId = supplyId;
        this.name = name;
        this.category = category;
        this.currentQuantity = currentQuantity;
        this.minimumQuantity = minimumQuantity;
    }

    // Constructor with no supplyId to allow the database to auto-generate the supplyId. This is for future implementation when I add the database functionality.
    public Supply(String name, String category, int currentQuantity, int minimumQuantity) {
        this.name = name;
        this.category = category;
        this.currentQuantity = currentQuantity;
        this.minimumQuantity = minimumQuantity;
    }

    // Abstract method
    public abstract String getSupplyType();

    // Boolean method to check if supply is low in stock
    public boolean isLowStock() {
        return currentQuantity <= minimumQuantity;
    }

    // Getters and setters
    public int getSupplyId() {
        return supplyId;
    }
    public void setSupplyId(int supplyId) {
        this.supplyId = supplyId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public int getCurrentQuantity() {
        return currentQuantity;
    }
    public void setCurrentQuantity(int currentQuantity) {
        this.currentQuantity = currentQuantity;
    }
    public int getMinimumQuantity() {
        return minimumQuantity;
    }
    public void setMinimumQuantity(int minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

    // Default constructor
    public Supply() {
    }

    // Override toString to display formatted information
    @Override 
    public String toString() {
        return "Supply ID: " + supplyId + "\n" +
        "   Name: " + name + "\n" + 
        "   Category: " + category + "\n" +
        "   Type: " + getSupplyType() + "\n" +
        "   Current Quantity: " + currentQuantity + "\n" +
        "   Minimum Quantity: " + minimumQuantity + "\n" +
        "   Status: " + (isLowStock() ? "LOW STOCK" : "In Stock") + "\n";
    }
}