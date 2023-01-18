module com.example.tresenrallas {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tresenrallas to javafx.fxml;
    exports com.example.tresenrallas;
}