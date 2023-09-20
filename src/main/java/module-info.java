module ip.main {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;
    opens peko;
    opens peko.commands;
    opens peko.exceptions;
    opens peko.memory;
    opens peko.tasks;
}