package mattbot.command;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Starts the MattBot.
     * @param args Additional arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
