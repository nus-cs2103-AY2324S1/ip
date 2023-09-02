package duke.ui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Launches the GUI window.
     *
     * @param args The arguments passed in.
     */
    public static void main(String[] args) {
        Application.launch(Gui.class, args);
    }
}
