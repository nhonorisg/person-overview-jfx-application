module fr.example.addressapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.example.addressapp to javafx.fxml;
    exports fr.example.addressapp;
}