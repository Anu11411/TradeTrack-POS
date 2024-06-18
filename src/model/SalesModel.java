package model;

/**
 * Represents a model class for sales transactions, including attributes such as order ID,
 * invoice date, party name, currency, exchange rate, total quantities and amounts, payments,
 * and dues. Provides constructors and accessor methods for accessing and modifying these properties.
 */
public class SalesModel {
    
    private long orderId;                // Unique identifier for the sales order
    private String invoiceDate;          // Date of the sales invoice
    private String partyName;            // Name of the party associated with the sales
    private float totalQuantity;         // Total quantity of items sold
    private float totalAmount;           // Total amount of the sale
    private float otherAmount;           // Additional miscellaneous charges for the sale
    private float totalPaybleAmount;     // Total payable amount after adjustments for the sale
    private float totalPaidAmount;       // Total amount paid against the sale
    private float totalDueAmount;        // Total amount due for payment related to the sale
    private String currency;             // Currency used for the sale transaction
    private float taux;                  // Exchange rate of the currency used

    // Default constructor
    public SalesModel() {}

    // Parameterized constructor to initialize all properties
    public SalesModel(long orderId, String invoiceDate, String partyName, String currency, float taux, float totalQuantity, float totalAmount, float otherAmount, float totalPaybleAmount, float totalPaidAmount, float totalDueAmount) {
        this.orderId = orderId;
        this.invoiceDate = invoiceDate;
        this.partyName = partyName;
        this.currency = currency;
        this.taux = taux;
        this.totalQuantity = totalQuantity;
        this.totalAmount = totalAmount;
        this.otherAmount = otherAmount;
        this.totalPaybleAmount = totalPaybleAmount;
        this.totalPaidAmount = totalPaidAmount;
        this.totalDueAmount = totalDueAmount;
    }

    // Getters and setters for all attributes
}
