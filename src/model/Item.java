
package model;

/**
 * The Item class represents an item in the inventory with various properties 
 * such as item name, unit of measure, quantity, price, amount, location, and item ID.
 * It provides constructors for creating instances and getter and setter methods 
 * for accessing and modifying the properties.
 *
 * @author Anupriya Rani
 */
public class Item {
    private String item;      // Name of the item
    private String uom;       // Unit of measure
    private Float quantity;   // Quantity of the item
    private Float price;      // Price per unit
    private Float amount;     // Total amount
    private String location;  // Location of the item
    private long itemId;      // Unique identifier for the item

    // Default constructor
    public Item() {
    }

    // Parameterized constructor to initialize all properties
    public Item(String item, String uom, Float quantity, Float price, Float amount, String location, long itemId) {
        this.item = item;
        this.uom = uom;
        this.quantity = quantity;
        this.price = price;
        this.amount = amount;
        this.location = location;
        this.itemId = itemId;
    }

    // Getters
    public String getItem() {
        return item;
    }

    public String getUom() {
        return uom;
    }

    public Float getQuantity() {
        return quantity;
    }

    public Float getPrice() {
        return price;
    }

    public Float getAmount() {
        return amount;
    }

    public String getLocation() {
        return location;
    }

    public long getItemId() {
        return itemId;
    }

    // Setters
    public void setItem(String item) {
        this.item = item;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }
}
