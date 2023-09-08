module ip.main {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    opens peko;
    opens peko.commands;
    opens peko.exceptions;
    opens peko.memory;
    opens peko.tasks;
}