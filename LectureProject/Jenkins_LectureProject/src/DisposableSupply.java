/*
Name: Layla Jenkins
Date: 10/31/2026
Assignment: SDC330 - Lecture Project

This class is a child class of the Supply class and will implement the abstract method 
getSupplyType to return "disposable" in addition to inheriting the properties and methods 
from the super class. 
*/

public class DisposableSupply extends Supply {
    // Constructor
    public DisposableSupply(int supplyId, String name, String category, int currentQuantity, int minimumQuantity) {
        super(supplyId, name, category, currentQuantity, minimumQuantity);
    }

    // Constructor without supplyId
    public DisposableSupply(String name, String category, int currentQuantity, int minimumQuantity) {
        super(name, category, currentQuantity, minimumQuantity);
    }

    // Overriding abstract method to specify that this is a disposable supply
    @Override 
    public String getSupplyType() {
        return "   Disposable";
    }
}