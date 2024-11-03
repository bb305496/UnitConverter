
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Controller class for mainMenu. Manages UI elements and waping between units conversion.
 * Implements {@link Initializable} to set up the initial UI state and tooltips.
 * Includes keyboard shortcuts for conversion and navigation.
 * Handles invalid input and negative values with exception handling.
 * 
 * @author Bartek Bielak
 * @version 1.0
 */
public class MainMenuController implements Initializable {
    
    /** The primary stage of the application, used to manage the main window */
    @FXML
    private Stage stage;

    /** The current scene displayed in the application */
    private Scene scene;

    /** The root node of the current scene, used for layout management */
    private Parent root;

    /** Button to exit the application */
    @FXML
    private Button exitButton;

    /** Button to navigate to the length conversion scene */
    @FXML
    private Button lengthButton;

    /** Button to navigate to the weight conversion scene */
    @FXML
    private Button weightButton;

    /** Button to navigate to the temperature conversion scene */
    @FXML
    private Button temperatureButton;

    /** AnchorPane used as the layout for the main menu scene */
    @FXML
    private AnchorPane scenePane;
    
    /**
     * Switches the application view to the Length conversion scene.
     * 
     * @param e the ActionEvent triggered by the user interaction that initiated the switch
     * @throws IOException if the FXML file for the Length scene cannot be loaded
     */
    public void switchToLength(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Length.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Switches the application view to the Weight conversion scene.
     * 
     * @param e the ActionEvent triggered by the user interaction that initiated the switch
     * @throws IOException if the FXML file for the Weight scene cannot be loaded
     */
    public void switchToWeight(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Weight.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Switches the application view to the Temperature conversion scene.
     * 
     * @param e the ActionEvent triggered by the user interaction that initiated the switch
     * @throws IOException if the FXML file for the Temperature scene cannot be loaded
     */
    public void switchToTemperature(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Temperature.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    /**
    * Closes the application window when the exit action is triggered.
    *
    * @param e the ActionEvent triggered by the user interaction that initiated the exit action
    */
    public void exit(ActionEvent e)
    {
        stage = (Stage) scenePane.getScene().getWindow();
        stage.close();
    }

    /**
     * Initializes the UI components, sets up tooltips, key shortcuts.
     *
     * @param url the location used to resolve relative paths for the root object
     * @param rb the resources used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        exitButton.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                KeyCombination keyCombination = new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN);
                newScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
                    if (keyCombination.match(event)) {
                        exitButton.fire();
                        event.consume();
                    }
                });
            }
        });
        
        lengthButton.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                KeyCombination keyCombination = new KeyCodeCombination(KeyCode.L, KeyCombination.CONTROL_DOWN);
                newScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
                    if (keyCombination.match(event)) {
                        lengthButton.fire();
                        event.consume();
                    }
                });
            }
        });
        
        weightButton.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                KeyCombination keyCombination = new KeyCodeCombination(KeyCode.W, KeyCombination.CONTROL_DOWN);
                newScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
                    if (keyCombination.match(event)) {
                        weightButton.fire();
                        event.consume();
                    }
                });
            }
        });
        
        temperatureButton.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                KeyCombination keyCombination = new KeyCodeCombination(KeyCode.T, KeyCombination.CONTROL_DOWN);
                newScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
                    if (keyCombination.match(event)) {
                        temperatureButton.fire();
                        event.consume();
                    }
                });
            }
        });
        
        Tooltip exitButtonTooltip = new Tooltip("Press button to exit (CTRL + E)");
        exitButtonTooltip.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #00FF00; -fx-padding: 0px; -fx-background-radius: 5px; -fx-font-size: 17px;");
        exitButton.setTooltip(exitButtonTooltip);
        
        exitButton.setOnMouseEntered(event -> showExitButtonTooltipIfVisible());
        exitButton.setOnMouseExited(event -> exitButtonTooltip.hide());
        
        Tooltip lengthButtonTooltip = new Tooltip("Press the button to go to change the length conversion (CTRL + L)");
        lengthButtonTooltip.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #00FF00; -fx-padding: 0px; -fx-background-radius: 5px; -fx-font-size: 17px;");
        lengthButton.setTooltip(lengthButtonTooltip);
        
        lengthButton.setOnMouseEntered(event -> showLengthButtonTooltipIfVisible());
        lengthButton.setOnMouseExited(event -> lengthButtonTooltip.hide());
        
        Tooltip weightButtonTooltip = new Tooltip("Press the button to go to change the weight conversion (CTRL + W)");
        weightButtonTooltip.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #00FF00; -fx-padding: 0px; -fx-background-radius: 5px; -fx-font-size: 17px;");
        weightButton.setTooltip(weightButtonTooltip);
        
        weightButton.setOnMouseEntered(event -> showWeightButtonTooltipIfVisible());
        weightButton.setOnMouseExited(event -> weightButtonTooltip.hide());
        
        Tooltip temperatureButtonTooltip = new Tooltip("Press the button to go to change the temperature conversion (CTRL + T)");
        temperatureButtonTooltip.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #00FF00; -fx-padding: 0px; -fx-background-radius: 5px; -fx-font-size: 17px;");
        temperatureButton.setTooltip(temperatureButtonTooltip);
        
        lengthButton.setAccessibleText("Length conversion button");
        lengthButton.setAccessibleHelp("Press to go to length conversion");
        
        weightButton.setAccessibleText("Weight conversion button");
        weightButton.setAccessibleHelp("Press to go to weight conversion");
        
        temperatureButton.setAccessibleText("Temperature conversion button");
        temperatureButton.setAccessibleHelp("Press to go to temperature conversion");
        
        exitButton.setAccessibleText("Exit button");
        exitButton.setAccessibleHelp("Press to close the program");
        
        temperatureButton.setOnMouseEntered(event -> showTemperatureButtonTooltipIfVisible());
        temperatureButton.setOnMouseExited(event -> temperatureButtonTooltip.hide());
    }
    
    /**
    * Displays the tooltip for the exit button if it is visible and enabled.
    */
   private void showExitButtonTooltipIfVisible() {
       if (exitButton.isVisible() && !exitButton.isDisabled()) {
           Tooltip tooltip = exitButton.getTooltip();
           tooltip.show(exitButton, 
               exitButton.getScene().getWindow().getX() + exitButton.getLayoutX(),
               exitButton.getScene().getWindow().getY() + exitButton.getLayoutY());
       }
   }

   /**
    * Displays the tooltip for the length button if it is visible and enabled.
    */
   private void showLengthButtonTooltipIfVisible() {
       if (lengthButton.isVisible() && !lengthButton.isDisabled()) {
           Tooltip tooltip = lengthButton.getTooltip();
           tooltip.show(lengthButton, 
               lengthButton.getScene().getWindow().getX() + lengthButton.getLayoutX(),
               lengthButton.getScene().getWindow().getY() + lengthButton.getLayoutY());
       }
   }

   /**
    * Displays the tooltip for the weight button if it is visible and enabled.
    */
   private void showWeightButtonTooltipIfVisible() {
       if (weightButton.isVisible() && !weightButton.isDisabled()) {
           Tooltip tooltip = weightButton.getTooltip();
           tooltip.show(weightButton, 
               weightButton.getScene().getWindow().getX() + weightButton.getLayoutX(),
               weightButton.getScene().getWindow().getY() + weightButton.getLayoutY());
       }
   }

   /**
    * Displays the tooltip for the temperature button if it is visible and enabled.
    */
   private void showTemperatureButtonTooltipIfVisible() {
       if (temperatureButton.isVisible() && !temperatureButton.isDisabled()) {
           Tooltip tooltip = temperatureButton.getTooltip();
           tooltip.show(temperatureButton, 
               temperatureButton.getScene().getWindow().getX() + temperatureButton.getLayoutX(),
               temperatureButton.getScene().getWindow().getY() + temperatureButton.getLayoutY());
       }
   }

}
