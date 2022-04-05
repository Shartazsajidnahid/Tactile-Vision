module com.example.dotidentification {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires opencv;


    opens com.example.dotidentification to javafx.fxml;
    exports com.example.dotidentification;
}