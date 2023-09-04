package rat;

import javafx.application.Application;
import rat.gui.Main;

/**
 * A launcher class to launch the application with GUI.
 */
public class RatLauncher {

    /**
     * Launches the application with GUI.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

}
