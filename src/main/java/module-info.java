module duke {
    requires javafx.controls;
    requires javafx.fxml;

    opens duke to javafx.fxml;
    exports duke;
    exports duke.tasks;
    opens duke.tasks to javafx.fxml;
    exports duke.gui;
    opens duke.gui to javafx.fxml;
    exports duke.logic;
    opens duke.logic to javafx.fxml;
}