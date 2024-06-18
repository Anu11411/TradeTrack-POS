package controller;

import helper.AlertHelper;
import database.DbConnection;
import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;
import model.Item;

/**
 * Controller class for managing purchase orders.
 * This class handles the UI interactions and database operations related to adding purchases.
 * It implements the Initializable interface to initialize the UI components.
 * 
 */
public class AddPurchaseController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private TextField textFieldItem;

    @FXML
    private ComboBox<String> comboBoxUom;

    @FXML
    private TextField textFieldQty;

    @FXML
    private TextField textFieldPrice;

    @FXML
    private TextField textFieldAmount;

    @FXML
    private TableView<Item> tableViewItem;

    @FXML
    private ComboBox<String> comboBoxLocation;

    @FXML
    private TextField textFieldTotalQuantity;

    @FXML
    private TextField textFieldTotalAmount;

    @FXML
    private Button buttonSave;

    @FXML
    private TextField textFieldTotalOther;

    @FXML
    private TextField textFieldTotalPaybleAmount;

    @FXML
    private TextField textFieldTotalPaidAmount;

    @FXML
    private TextField textFieldTotalDueAmount;

    @FXML
    private TextField textFieldParty;

    @FXML
    private TextField textFieldContact;

    @FXML
    private TextField textFieldRemarks;
    
    @FXML
    private DatePicker date;

    // Sets to store item and customer names for auto-completion
    Set<String> items = new HashSet<>();
    SuggestionProvider<String> provider = SuggestionProvider.create(items);
    private AutoCompletionBinding<String> autoCompleteBinding;

    Set<String> customers = new HashSet<>();
    SuggestionProvider<String> provider1 = SuggestionProvider.create(customers);
    private AutoCompletionBinding<String> autoCompleteBinding1;

    private final Connection con;

    // Variables to track selected table row and item ID
    private int selectedTableViewRow = 0;
    private long itemId;

    // Constructor to establish the database connection
    public AddPurchaseController() {
        this.con = DbConnection.getConnection();
    }

    /**
     * Initializes the controller class.
     * Sets up the table columns, auto-completion for item and customer text fields,
     * and initial values for combo boxes.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String rightPositionCSS = "-fx-alignment: CENTER-RIGHT;";
        String centerPostionCSS = "-fx-alignment: CENTER;";
        
        // Set up auto-completion for item text field
        AutoCompletionTextFieldBinding<String> test = new AutoCompletionTextFieldBinding<>(textFieldItem, provider);
        test.setOnAutoCompleted(e -> setUomAndPrice());

        // Set up auto-completion for customer text field
        AutoCompletionTextFieldBinding<String> test1 = new AutoCompletionTextFieldBinding<>(textFieldParty, provider1);
        test1.setOnAutoCompleted(e -> setCustomer());

        // Define and configure table columns
        TableColumn<Item, String> columnItem = new TableColumn<>("Item");
        columnItem.setCellValueFactory(new PropertyValueFactory<>("item"));
        columnItem.setPrefWidth(400);

        TableColumn<Item, String> columnUom = new TableColumn<>("Uom");
        columnUom.setCellValueFactory(new PropertyValueFactory<>("uom"));

        TableColumn<Item, Float> columnQuantity = new TableColumn<>("Quantity");
        columnQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        columnQuantity.setStyle(rightPositionCSS);

        TableColumn<Item, String> columnLocation = new TableColumn<>("Location");
        columnLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        columnLocation.setStyle(centerPostionCSS);

        TableColumn<Item, Float> columnPrice = new TableColumn<>("Price");
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnPrice.setStyle(rightPositionCSS);

        TableColumn<Item, Float> columnAmount = new TableColumn<>("Amount");
        columnAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        columnAmount.setStyle(rightPositionCSS);

        TableColumn<Item, Long> columnItemId = new TableColumn<>("Item ID");
        columnItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        columnItemId.setVisible(false);

        // Add columns to the table view
        tableViewItem.getColumns().add(columnItemId);
        tableViewItem.getColumns().add(columnItem);
        tableViewItem.getColumns().add(columnUom);
        tableViewItem.getColumns().add(columnQuantity);
        tableViewItem.getColumns().add(columnLocation);
        tableViewItem.getColumns().add(columnPrice);
        tableViewItem.getColumns().add(columnAmount);

        // Initialize combo box for location
        comboBoxLocation.getItems().setAll("Rack", "Depot", "Display");
        comboBoxLocation.getSelectionModel().select("Depot");
    }

    /**
     * Searches for items based on user input and updates the auto-completion suggestions.
     */
    @FXML
    void searchItem() {
        String typedItem = textFieldItem.getText();
        if (typedItem != null && typedItem.length() > 2) {
            try {
                boolean isNumeric = typedItem.chars().allMatch(Character::isDigit);
                LocalDate documentDate = LocalDate.now();
                Statement stmt = con.createStatement();
                String query = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;
                
                if (isNumeric) {
                    // Query for numeric item codes
                    query = "select code, a.name item_name, b.name as pack_unit, a.pack_size, c.name as standard_unit, "
                            + "p.sale_price from items a, uoms b, uoms c, "
                            + "(select item_id, sale_price from item_prices "
                            + "where ? between effective_from and effective_to) p "
                            + "where code = ? and a.pack_unit_id = b.UOM_ID and a.standard_unit_id = c.uom_id "
                            + "and a.item_id = p.item_id "
                            + "order by 2";
                    pstmt = con.prepareStatement(query);
                    pstmt.setDate(1, java.sql.Date.valueOf(documentDate));
                    pstmt.setString(2, typedItem);
                    rs = pstmt.executeQuery();

                    String selectedItem = null;
                    while (rs.next()) {
                        items.add(rs.getString("item_name"));
                        selectedItem = rs.getString("item_name");
                    }
                    if (selectedItem != null) {
                        textFieldItem.setText(selectedItem);
                    } else {
                        // Fallback query for partial name matches
                        searchItemByName(typedItem, documentDate);
                    }
                } else {
                    // Query for partial name matches
                    searchItemByName(typedItem, documentDate);
                }

                // Update the provider with the filtered suggestions
                Set<String> filteredAutoCompletions = new HashSet<>(items);
                provider.clearSuggestions();
                provider.addPossibleSuggestions(filteredAutoCompletions);
                
            } catch (SQLException ex) {
                Logger.getLogger(AddPurchaseController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Helper method to search items by partial name matches.
     */
    private void searchItemByName(String typedItem, LocalDate documentDate) throws SQLException {
        String query;
        PreparedStatement pstmt;
        ResultSet rs;
        query = "select code, a.name item_name, b.name as pack_unit, a.pack_size, c.name as standard_unit, "
                + "p.sale_price from items a, uoms b, uoms c, "
                + "(select item_id, sale_price from item_prices "
                + "where ? between effective_from and effective_to) p "
                + "where upper(a.name) LIKE upper(?) and a.pack_unit_id = b.UOM_ID and a.standard_unit_id = c.uom_id "
                + "and a.item_id = p.item_id "
                + "order by 2";
        pstmt = con.prepareStatement(query);
        pstmt.setDate(1, java.sql.Date.valueOf(documentDate));
        pstmt.setString(2, "%" + typedItem + "%");
        rs = pstmt.executeQuery();

        while (rs.next()) {
            items.add(rs.getString("item_name"));
        }
    }

    /**
     * Sets the UOM and price for the selected item.
     */
    private void setUomAndPrice() {
        // Implement the logic to set the UOM and price for the selected item
    }

    /**
     * Sets the customer details based on the selected customer name.
     */
    private void setCustomer() {
        // Implement the logic to set the customer details based on the selected customer name
    }
}
