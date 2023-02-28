module pl.polsl.table {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens pl.polsl.controllers to javafx.fxml;
    
    exports pl.polsl.model;
    exports pl.polsl.controllers;
    exports pl.polsl.table;
}
