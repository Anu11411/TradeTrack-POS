package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * MainPanel is the entry point of the JavaFX application.
 * It loads the initial FXML view and sets up the main application window.
 * 
 * @author Anupriya Rani
 */
public class MainPanel extends Application {
    
    /**
     * The start method is the main entry point for all JavaFX applications.
     * It is called after the init method has returned and after the system is ready for the application to begin running.
     *
     * @param stage The primary stage for this application, onto which the application scene can be set.
     * @throws Exception If an error occurs during loading the FXML resource.
     */
    @Override
    public void start(Stage stage) throws Exception {
        
        // Load the FXML file for the main panel view
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainPanelView.fxml"));
        
        // Uncomment to load the sales invoice view instead of the main panel view
        // Parent root = FXMLLoader.load(getClass().getResource("/view/ListSalesInvoiceView.fxml"));
        
        // Uncomment to load the login view instead of the main panel view
        // Parent root = FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
        
        // Create a scene with the loaded FXML root node
        Scene scene = new Scene(root);
       
        // Set the scene to the stage
        stage.setScene(scene);
        
        // Set the title of the stage (window)
        stage.setTitle("Store POS - Powered by Ramesh Godara");
        
        // Add an icon to the stage
        stage.getIcons().add(new Image("/asset/icon.png"));
        
        // Display the stage
        stage.show();
    }

    /**
     * The main method is the entry point for the Java application.
     * It launches the JavaFX application by calling launch(args).
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
