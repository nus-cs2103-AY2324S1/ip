module duke {
    requires javafx.controls;
    requires javafx.fxml;

    opens duke to javafx.fxml;
    exports duke;
    exports duke.task;
    opens duke.task to javafx.fxml;
    exports duke.gui;
    opens duke.gui to javafx.fxml;
    exports duke.logic;
    opens duke.logic to javafx.fxml;
    exports duke.exceptions;
    opens duke.exceptions to javafx.fxml;
}