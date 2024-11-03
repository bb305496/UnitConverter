module polsl {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    
    opens Controller to javafx.fxml;
    exports Controller;

    opens polsl to javafx.fxml;
    exports polsl;
    
    opens Exceptions to javafx.fxml;
    exports Exceptions to javafx.fxml;
    
    opens Model to javafx.fxml;
    exports Model to javafx.fxml;
    
    
}
