module com.example.supplychainsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.supplychainsystem to javafx.fxml;
    exports com.example.supplychainsystem;
}