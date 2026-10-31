/*
Name: Layla Jenkins
Date: 10/31/2026
Assignment: SDC330 - Lecture Project

This is an interface, which defines the oeprations that the class InventoryManager 
will use to manager the inventory. It will have methods for adding/updating/deleting supplies,
viewing all the supplies, viewing supplies low in stock, and searching for a specific supply by 
the supply ID. 
*/

import java.util.List;

public interface InventoryOperations {
    // Methods to be used when this interface is being implemented
    public void addSupply(Supply supply);
    public void updateSupply(Supply supply);
    public void deleteSupply(int supplyId);
    public Supply findSupply(int supplyId);
    public List<Supply> getViewSupplies();
    public List<Supply> getLowStock();
}