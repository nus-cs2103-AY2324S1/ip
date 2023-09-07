module duke {
    requires javafx.controls;
    requires javafx.fxml;

    opens duke to javafx.fxml;
    exports duke;
}