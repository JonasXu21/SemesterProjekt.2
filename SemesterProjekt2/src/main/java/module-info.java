module com.example.semesterprojekt2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens presentation to javafx.fxml;
    exports presentation;
}