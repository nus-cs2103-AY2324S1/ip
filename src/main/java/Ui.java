import java.util.Scanner;

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
    public void showWelcome() {
        // Displays Evo logo and welcome message
        String logo = " _____\n"
                + "|  ___|\n"
                + "| |___ __    __  _____\n"
                + "|  ___|\\ \\  / / |     |\n"
                + "| |___  \\ \\/ /  |     |\n"
                + "|_____|  \\__/   |_____|\n";
        System.out.println("Hello from\n" + logo);

        // Initialises welcome messages
        String welcomeMsg = "Hello! I'm Evo.\n" + "What can I do for you?\n";

        // Prints out welcome message once the user using Evo
        System.out.println(welcomeMsg);

    }

    /**
     * Displays an exit message to the user.
     */
    public void showExit() {
        // Initialises goodbye messages
        String byeMsg = "Bye. Hope to see you again soon!";

        // Prints out welcome message once the user using Evo
        System.out.println(byeMsg);
    }

    /**
     * Reads a command entered by the user.
     *
     * @return The user's input command as a string.
     */
    public String readCommand() {
        // Initialise a scanner to receive text input from user
        Scanner scanner = new Scanner(System.in);
        // Assign the text to this string variable called instruction
        String instruction = scanner.nextLine();
        return instruction;
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
    public void showText(String text) {
        System.out.println(text);
    }

    /**
     * Displays a new line (blank line) to the user.
     * This is used to format output for readability.
     */
    public void showNewLine() {
        System.out.println("\n");
    }
}
