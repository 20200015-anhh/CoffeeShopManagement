module com.example.coffeshop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;
    requires java.naming;
    opens com.example.coffeeshop to javafx.fxml;
    opens com.example.coffeeshop.resource to javafx.fxml;
    exports com.example.coffeeshop;
    exports com.example.coffeeshop.resource;
}