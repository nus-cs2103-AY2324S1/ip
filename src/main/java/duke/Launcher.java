package duke;

import duke.ui.Duke;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        try {
            Application.launch(Duke.class, args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}