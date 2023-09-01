package duke;

import duke.ui.GuiBridge;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(GuiBridge.class, args);
    }
}
