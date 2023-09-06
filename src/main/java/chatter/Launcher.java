package chatter;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Launches the chatbot.
     *
     * @param args CLI arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}