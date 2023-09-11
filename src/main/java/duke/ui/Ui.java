package duke.ui;

import java.util.Scanner;

/**
 * Represents the user interface of the Duke application.
 * This class is responsible for displaying messages and reading user input.
 */
public class Ui {

    private final String CHAT_BOT_NAME = "CARL BOT";
    private Scanner scanner;
    private String message = "";

    /**
     * Displays a message in the message card.
     *
     * @param message The message to be displayed.
     * @return String of message.
     */
    public String sendMessage(String message) {
        return this.message = message;
    }

    /**
     * Displays a welcome message.
     *
     * @return The welcome message to be displayed.
     */
    public String showWelcome() {
        return CHAT_BOT_NAME + ":\nHello! I'm " + CHAT_BOT_NAME
                + "\nWhat can I do for you?\n" +
                "Type \"help\" to begin";
    }

    /**
     * Displays a welcome message.
     *
     * @return The welcome message to be displayed.
     */
    public String displayMessage() {
        return CHAT_BOT_NAME + ":\n" + this.message;
    }

}
