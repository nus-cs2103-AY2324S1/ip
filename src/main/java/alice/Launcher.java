package alice;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Launches the application in either CLI or GUI mode.
     *
     * @param args The arguments passed in.
     */
    public static void main(String[] args) {
        for (String arg : args) {
            if (arg.equals("--cli")) {
                new Alice().run();
                return;
            }
        }
        Application.launch(Main.class, args);
    }
}
