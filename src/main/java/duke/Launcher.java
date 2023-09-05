package duke;

import duke.gui.Main;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Launchs the GUI of the application
     *
     * @param args the list of arguments
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
