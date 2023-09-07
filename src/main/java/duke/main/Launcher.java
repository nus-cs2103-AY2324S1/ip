package duke.main;

import duke.ui.Ui;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Ui.class, args);
    }
}
