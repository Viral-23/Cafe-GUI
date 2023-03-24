module rucafe.project4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens rucafe.project4 to javafx.fxml;
    exports rucafe.project4;
}