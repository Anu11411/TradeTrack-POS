package helper;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Window;

public class AlertHelper {

    // A static boolean to store the result of the alert dialog
    public static boolean result = false;

    /**
     * Displays an alert dialog with the specified parameters.
     *
     * @param alertType The type of alert to be displayed (e.g., INFORMATION, WARNING, ERROR).
     * @param owner The owner window of the alert dialog.
     * @param title The title of the alert dialog.
     * @param message The message content of the alert dialog.
     */
    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        // Create a new Alert instance with the specified alert type
        Alert alert = new Alert(alertType);
        
        // Set the title of the alert dialog
        alert.setTitle(title);
        
        // Set the header text of the alert dialog to null (no header)
        alert.setHeaderText(null);
        
        // Set the content message of the alert dialog
        alert.setContentText(message);
        
        // Set the owner window of the alert dialog
        alert.initOwner(owner);
        
        // Show the alert dialog and wait for the user's response
        Optional<ButtonType> result = alert.showAndWait();
        
        // Check the user's response and set the static result field accordingly
        if (result.isPresent() && result.get() == ButtonType.OK) {
            AlertHelper.result = true;
        } else {
            AlertHelper.result = false;
        }
    }
}
