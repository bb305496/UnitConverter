
package Controller;

import Exceptions.NegativeValueException;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Model.WeightModel;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import polsl.ConversionRecord;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

/**
 * Controller class for weight conversion. Manages UI elements and conversions between units.
 * Implements {@link Initializable} to set up the initial UI state and tooltips.
 * Includes keyboard shortcuts for conversion and navigation.
 * Handles invalid input and negative values with exception handling.
 * 
 * @author Bartek Bielak
 * @version 1.0
 */
public class WeightController implements Initializable {
    
    /** Model object to handle weight conversions */
    private WeightModel weightModel = new WeightModel();

    /** The primary stage of the application, used to switch scenes */
    @FXML
    private Stage stage;

    /** The current scene in use */
    private Scene scene;

    /** The root element of the current scene */
    private Parent root;

    /** Label to display messages or conversion results */
    @FXML
    private Label myLabel;

    /** Text field for user input, typically to enter a name or value */
    @FXML
    private TextField nameTextField;

    /** ChoiceBox for selecting the unit to convert from */
    @FXML
    private ChoiceBox<String> ChoiceBoxFrom;

    /** ChoiceBox for selecting the unit to convert to */
    @FXML
    private ChoiceBox<String> ChoiceBoxTo;

    /** Button to trigger the conversion operation */
    @FXML
    private Button convertButton;

    /** Button to navigate back to the previous screen */
    @FXML
    private Button backButton;

    /** TableView to display a list of past conversions */
    @FXML
    private TableView<ConversionRecord> conversionTable;

    /** Column in the table to display the original value entered for conversion */
    @FXML
    private TableColumn<ConversionRecord, Double> valueColumn;

    /** Column in the table to display the original unit of the value */
    @FXML
    private TableColumn<ConversionRecord, String> fromUnitColumn;

    /** Column in the table to display the target unit of the conversion */
    @FXML
    private TableColumn<ConversionRecord, String> toUnitColumn;

    /** Column in the table to display the result of the conversion */
    @FXML
    private TableColumn<ConversionRecord, Double> resultColumn;

    /** Array of available weight units for conversion */
    private String[] weight = {"g", "mg", "dg", "kg"};
    
    /**
     * Converts the input value from one unit to another.
     * Adds the conversion result to the table view.
     *
     * @param e the action event triggered by pressing the convert button
     * @throws IOException if an I/O error occurs
     */
    public void convert(ActionEvent e) throws IOException
    {
        try
        {
            double value = Double.parseDouble(nameTextField.getText());
            
            String fromUnit = ChoiceBoxFrom.getValue();
            String toUnit = ChoiceBoxTo.getValue();
            
            double result = weightModel.convert(value, fromUnit, toUnit);
            
            myLabel.setText(result + " " + toUnit);
            
            ConversionRecord record = new ConversionRecord(value, fromUnit, toUnit, result);
            conversionTable.getItems().add(record);
            
        } catch(NumberFormatException ex)
        {
            myLabel.setText("Invalid Input. Please enter a number");
        } catch(NegativeValueException ex)
        {
            myLabel.setText("Invalid Input. " + ex.getMessage());
        }
    }
    
    /**
     * Navigates back to the main menu.
     *
     * @param e the action event triggered by pressing the back button
     * @throws IOException if an I/O error occurs while loading the main menu
     */
    public void backToMainMenu(ActionEvent e) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Initializes the UI components, sets up tooltips, key shortcuts, and choice box options.
     * Adds table columns and defines shortcut keys for convert and back actions.
     *
     * @param url the location used to resolve relative paths for the root object
     * @param rb the resources used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
        convertButton.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                KeyCombination keyCombination = new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN);
                newScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
                    if (keyCombination.match(event)) {
                        convertButton.fire();
                        event.consume();
                    }
                });
            }
        });
        
        backButton.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                KeyCombination keyCombination = new KeyCodeCombination(KeyCode.B, KeyCombination.CONTROL_DOWN);
                newScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
                    if (keyCombination.match(event)) {
                        backButton.fire();
                        event.consume();
                    }
                });
            }
        });
        
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        fromUnitColumn.setCellValueFactory(new PropertyValueFactory<>("fromUnit"));
        toUnitColumn.setCellValueFactory(new PropertyValueFactory<>("toUnit"));
        resultColumn.setCellValueFactory(new PropertyValueFactory<>("result"));

        
        conversionTable.setItems(FXCollections.observableArrayList());
    
        ChoiceBoxFrom.getItems().addAll(weight);
        ChoiceBoxTo.getItems().addAll(weight);
        
        ChoiceBoxFrom.setValue("g");
        ChoiceBoxTo.setValue("g");
        
        Tooltip ChoiceBoxFromToolTip = new Tooltip("Select the unit to convert from");
        ChoiceBoxFromToolTip.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #00FF00; -fx-padding: 0px; -fx-background-radius: 5px; -fx-font-size: 17px;");
        ChoiceBoxFrom.setTooltip(ChoiceBoxFromToolTip);
        
        Tooltip ChoiceBoxToToolTip = new Tooltip("Select the unit to convert to");
        ChoiceBoxToToolTip.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #00FF00; -fx-padding: 0px; -fx-background-radius: 5px; -fx-font-size: 17px;");
        ChoiceBoxTo.setTooltip(ChoiceBoxToToolTip);
        
        Tooltip nameTextTooltip = new Tooltip("Please enter value to convert");
        nameTextTooltip.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #00FF00; -fx-padding: 0px; -fx-background-radius: 5px; -fx-font-size: 17px;");
        nameTextField.setTooltip(nameTextTooltip);
        
        Tooltip convertButtonTootip = new Tooltip("Press button to convert (CTRL + C)");
        convertButtonTootip.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #00FF00; -fx-padding: 0px; -fx-background-radius: 5px; -fx-font-size: 17px;");
        convertButton.setTooltip(convertButtonTootip);
        
        Tooltip backButtonToolTip = new Tooltip("Press button to back to main menu (CTRL + B)");
        backButtonToolTip.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #00FF00; -fx-padding: 0px; -fx-background-radius: 5px; -fx-font-size: 15px;");
        backButton.setTooltip(backButtonToolTip);
        
        nameTextField.setAccessibleText("Enter value to convert");
        nameTextField.setAccessibleHelp("Field to enter the value to be converted");
        
        convertButton.setAccessibleText("Convert value");
        convertButton.setAccessibleHelp("Press the button to convert your value");
        
        backButton.setAccessibleText("Back button");
        backButton.setAccessibleHelp("Press to back to main menu");
        
        ChoiceBoxFrom.setAccessibleText("Choose weight unit");
        ChoiceBoxFrom.setAccessibleHelp("Coose one of the following unit");
        
        nameTextField.setOnMouseEntered(event -> showNameTextTooltipIfVisible());
        nameTextField.setOnMouseExited(event -> nameTextTooltip.hide());
        
        convertButton.setOnMouseEntered(event -> showConvertButtonTooltipIfVisible());
        convertButton.setOnMouseExited(event -> convertButtonTootip.hide());
        
        backButton.setOnMouseEntered(event -> showBackButtonTooltipIfVisible());
        backButton.setOnMouseExited(event -> backButtonToolTip.hide());
        
        ChoiceBoxFrom.setOnMouseEntered(event -> showFromChoiceBoxTooltipIfVisible());
        ChoiceBoxFrom.setOnMouseExited(event -> ChoiceBoxFromToolTip.hide());
        
        ChoiceBoxTo.setOnMouseEntered(event -> showToChoiceBoxTooltipIfVisible());
        ChoiceBoxTo.setOnMouseExited(event -> ChoiceBoxToToolTip.hide());
        
    }
    
    /**
     * Shows the tooltip for the nameTextField if visible and not disabled.
     */
    private void showNameTextTooltipIfVisible() {
        if (nameTextField.isVisible() && !nameTextField.isDisabled()) {
            Tooltip tooltip = nameTextField.getTooltip();
            tooltip.show(nameTextField, 
                nameTextField.getScene().getWindow().getX() + nameTextField.getLayoutX(),
                nameTextField.getScene().getWindow().getY() + nameTextField.getLayoutY());
        }
    }
    
    /**
     * Shows the tooltip for the convertButton if visible and not disabled.
     */
    private void showConvertButtonTooltipIfVisible() {
        if (convertButton.isVisible() && !convertButton.isDisabled()) {
            Tooltip tooltip = convertButton.getTooltip();
            tooltip.show(convertButton, 
                convertButton.getScene().getWindow().getX() + convertButton.getLayoutX(),
                convertButton.getScene().getWindow().getY() + convertButton.getLayoutY());
        }
    }
    
    /**
     * Shows the tooltip for the backButton if visible and not disabled.
     */
    private void showBackButtonTooltipIfVisible() {
        if (backButton.isVisible() && !backButton.isDisabled()) {
            Tooltip tooltip = backButton.getTooltip();
            tooltip.show(backButton, 
                backButton.getScene().getWindow().getX() + backButton.getLayoutX(),
                backButton.getScene().getWindow().getY() + backButton.getLayoutY());
        }
    }
    
    /**
     * Shows the tooltip for the ChoiceBoxFrom if visible and not disabled.
     */
    private void showFromChoiceBoxTooltipIfVisible() {
        if (ChoiceBoxFrom.isVisible() && !ChoiceBoxFrom.isDisabled()) {
            Tooltip tooltip = ChoiceBoxFrom.getTooltip();
            tooltip.show(ChoiceBoxFrom, 
                ChoiceBoxFrom.getScene().getWindow().getX() + ChoiceBoxFrom.getLayoutX(),
                ChoiceBoxFrom.getScene().getWindow().getY() + ChoiceBoxFrom.getLayoutY());
        }
    }

    /**
     * Shows the tooltip for the ChoiceBoxTo if visible and not disabled.
     */
    private void showToChoiceBoxTooltipIfVisible() {
        if (ChoiceBoxTo.isVisible() && !ChoiceBoxTo.isDisabled()) {
            Tooltip tooltip = ChoiceBoxTo.getTooltip();
            tooltip.show(ChoiceBoxTo, 
                ChoiceBoxTo.getScene().getWindow().getX() + ChoiceBoxTo.getLayoutX(),
                ChoiceBoxTo.getScene().getWindow().getY() + ChoiceBoxTo.getLayoutY());
        }
    }
    
}
