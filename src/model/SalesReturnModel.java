package model;

/**
 * Represents a model class for sales return transactions, including attributes such as order ID,
 * invoice date, party name, total quantities and amounts, payments, and dues. Provides constructors
 * and accessor methods for accessing and modifying these properties.
 */
public class SalesReturnModel {
    
    private long orderId;                // Unique identifier for the sales return order
    private String invoiceDate;          // Date of the sales return invoice
    private String partyName;            // Name of the party associated with the sales return
    private float totalQuantity;         // Total quantity of items returned
    private float totalAmount;           // Total amount of the sales return
    private float otherAmount;           // Additional miscellaneous charges for the return
    private float totalPaybleAmount;     // Total payable amount after adjustments for the return
    private float totalPaidAmount;       // Total amount paid against the return
    private float totalDueAmount;        // Total amount due for payment related to the return

    // Default constructor
    public SalesReturnModel() {}

    // Parameterized constructor to initialize all properties
    public SalesReturnModel(long orderId, String invoiceDate, String partyName, float totalQuantity, float totalAmount, float otherAmount, float totalPaybleAmount, float totalPaidAmount, float totalDueAmount) {
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

    // Getters and setters for all attributes
}
