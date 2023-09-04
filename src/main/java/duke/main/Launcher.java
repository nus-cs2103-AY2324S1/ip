package duke.main;

import java.io.IOException;
import javafx.application.Application;

import duke.exception.DukeException;

public class Launcher {
    public static void main(String[] args) throws DukeException, IOException {
        Application.launch(Main.class, args);
    }
}
