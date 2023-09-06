package duke;

/**
 * The Main class serves as the primary entry point for the Duke application.
 * process for the application's Command-Line Interface (CLI).
 */
public class Main {

    /**
     * The main method serves as the starting point for the Duke application.
     *
     * @param args Command line arguments, not currently utilized in this application.
     */
    public static void main(String[] args) {
        Duke changooseBot = new Duke();
        changooseBot.run();
    }
}
