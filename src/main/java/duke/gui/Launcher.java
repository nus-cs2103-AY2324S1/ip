package duke.gui;
import javafx.application.Application;
import duke.Main;

public class Launcher {

    /**
     * A launcher class to workaround classpath issues.
     */
        public static void main(String[] args) {
            Application.launch(Main.class, args);
        }
}
