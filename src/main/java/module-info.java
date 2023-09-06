module com.nyanbot {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;

    opens com.nyanbot.DukeGuiControllers to javafx.fxml;

    exports com.nyanbot.DukeGuiControllers;
}