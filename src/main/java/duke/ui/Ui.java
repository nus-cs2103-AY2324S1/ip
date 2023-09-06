package duke.ui;

import java.util.Scanner;

/**
 * UI class deals with interactions with the user.
 */
public class Ui {

    private static Scanner scanner;

    /**
     * Class constructor of UI.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Closes the scanner.
     */
    public void closeScanner() {
        scanner.close();
    }

    /**
     * Returns the next user input.
     * @return String representation of user input.
     */
    public String getUserInput() {
        while (true) {
            if (scanner.hasNextLine()) {
                return scanner.nextLine();
            }
        }
    }

    /**
     * Prints greeting message.
     */
    public void greet() {
        String logo = "  _   _   _\n"
                + " / \\ / \\ / \\\n"
                + "( 안 | 녕 )\n"
                + " \\_/ \\_/ \\_/\n";
        System.out.println(logo + "Hello, I am your chatbot Kora!\nHow can I help you today?");
        System.out.println("------------------------------");
    }

    public String getGreetMessage() {
        String logo = "  _   _   _\n"
                + " / \\ / \\ / \\\n"
                + "( 안 | 녕 )\n"
                + " \\_/ \\_/ \\_/\n";
        String message = "Hello, I am your chatbot Kora!\nHow can I help you today?";
        return logo + message;
    }
}
