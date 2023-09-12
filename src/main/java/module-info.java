module nyanbot {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;

    opens com.nyanbot.dukeguicontrollers to javafx.fxml;
    opens com.nyanbot.dukeguielements to javafx.fxml;

    exports com.nyanbot.dukeguicontrollers;
}