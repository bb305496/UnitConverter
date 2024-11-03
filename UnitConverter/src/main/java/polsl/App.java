package polsl;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The App class serves as the entry point for the JavaFX application.
 * It initializes the primary stage and loads the main menu interface.
 * 
 * @author Bartek Bielak
 * @version 1.0
 */
public class App extends Application {

    /**
     * Starts the JavaFX application by setting up the primary stage.
     *
     * @param stage the primary stage for this application, onto which 
     *              the application scene can be set
     */
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene); 
            stage.show(); 
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }

    /**
     * The main entry point for the JavaFX application.
     * 
     * @param args command-line arguments for the application
     */
    public static void main(String[] args) {
        launch(args); 
    }
}
