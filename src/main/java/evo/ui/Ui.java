package evo.ui;

/**
 * The Ui class handles interactions with the user, including displaying messages and receiving input.
 * It provides methods for showing welcome and exit messages, reading user commands, and displaying text to the user.
 */
public class Ui {

    /**
     * Constructs an Ui object.
     */
    public Ui() {
    }

    /**
     * Displays a welcome message to the user.
     * This message typically includes a logo and a greeting.
     */
    public String showWelcome() {
        // Displays Evo logo and welcome message
        String logo = " _____\n"
                + "|     ___|\n"
                + "|    |___  __         __   _____\n"
                + "|     ___| \\    \\      /    / |    __    |\n"
                + "|    |___    \\    \\  /    /   |   |__|   |\n"
                + "|_____|     \\___/      |_____|\n";

        // Initialises welcome messages
        String welcomeMsg = "Hello! I'm Evo.\n" + "What can I do for you?\n";

        return "Hello from\n" + logo + welcomeMsg;
    }

    /**
     * Displays an exit message to the user.
     */
    public String showExit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays an error message related to loading a file.
     * This is used when there is an issue loading data from storage.
     */
    public void showLoadingError() {
        System.out.println("Error on loading file.");
    }

    /**
     * Displays a text message to the user.
     *
     * @param text The text message to be displayed.
     */
    public String showText(String text) {
        return text;
    }
}
