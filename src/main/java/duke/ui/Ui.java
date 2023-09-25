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
     * Prints greet message.
     */
    public void greet() {
        System.out.println(getGreetMessage());
    }

    /**
     * Returns greet message.
     * @return String greet message.
     */
    public String getGreetMessage() {
        String logo =
                  "   _    _    _\n"
                + " /  \\ /  \\ /  \\\n"
                + "(  KO | RA  )\n"
                + " \\_/ \\_/ \\_/\n";
        String message = "Annyeonghaseyo! I am your chatbot Kora!\nHow can I help you today?";
        return logo + message;
    }

    /**
     * Prints the message.
     * @param message String message to be printed.
     */
    public void koraReply(String message) {
        System.out.println(message);
    }
}
