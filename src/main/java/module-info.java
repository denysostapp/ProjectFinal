module com.example.projectfinal {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projectfinal to javafx.fxml;
    exports com.example.projectfinal;
}