package sisyphus;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Main function to launch chatbot.
     * @param args
     */
    public static void main(String[] args) {
        Application.launch(Sisyphus.class, args);
    }
}
