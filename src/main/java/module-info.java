module com.example.caso_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.caso_1 to javafx.fxml;
    exports com.example.caso_1;
}