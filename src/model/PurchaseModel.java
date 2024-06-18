/*
 * Represents a model class for purchase orders with attributes such as order ID,
 * invoice date, party name, total quantities and amounts, payments, and dues.
 * Provides constructors and accessor methods for accessing and modifying these properties.
 */
package model;

/**
 *
 * @author Anupriya Rani
 */
public class PurchaseModel {
    private long orderId;                // Unique identifier for the order
    private String invoiceDate;          // Date of the invoice
    private String partyName;            // Name of the party associated with the order
    private float totalQuantity;         // Total quantity of items in the order
    private float totalAmount;           // Total amount of the order
    private float otherAmount;           // Additional miscellaneous charges
    private float totalPaybleAmount;     // Total payable amount after adjustments
    private float totalPaidAmount;       // Total amount paid against the order
    private float totalDueAmount;        // Total amount due for payment

    // Default constructor
    public PurchaseModel() {}

    // Parameterized constructor to initialize all properties
    public PurchaseModel(long orderId, String invoiceDate, String partyName, float totalQuantity, float totalAmount, float otherAmount, float totalPaybleAmount, float totalPaidAmount, float totalDueAmount) {
        this.orderId = orderId;
        this.invoiceDate = invoiceDate;
        this.partyName = partyName;
        this.totalQuantity = totalQuantity;
        this.totalAmount = totalAmount;
        this.otherAmount = otherAmount;
        this.totalPaybleAmount = totalPaybleAmount;
        this.totalPaidAmount = totalPaidAmount;
        this.totalDueAmount = totalDueAmount;
    }

    // Getters
    public long getOrderId() {
        return orderId;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public String getPartyName() {
        return partyName;
    }

    public float getTotalQuantity() {
        return totalQuantity;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public float getOtherAmount() {
        return otherAmount;
    }

    public float getTotalPaybleAmount() {
        return totalPaybleAmount;
    }

    public float getTotalPaidAmount() {
        return totalPaidAmount;
    }

    public float getTotalDueAmount() {
        return totalDueAmount;
    }

    // Setters
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public void setTotalQuantity(float totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setOtherAmount(float otherAmount) {
        this.otherAmount = otherAmount;
    }

    public void setTotalPaybleAmount(float totalPaybleAmount) {
        this.totalPaybleAmount = totalPaybleAmount;
    }

    public void setTotalPaidAmount(float totalPaidAmount) {
        this.totalPaidAmount = totalPaidAmount;
    }

    public void setTotalDueAmount(float totalDueAmount) {
        this.totalDueAmount = totalDueAmount;
    }
}
