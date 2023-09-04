package duke.ui;

import java.util.Scanner;

/**
 * Represents the user interface of the Duke application.
 * This class is responsible for displaying messages and reading user input.
 */
public class Ui {

    private String chatBotName = "Carl";
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
        return "CARL BOT:\nHello! I'm " + chatBotName
                + "\nWhat can I do for you?\n" +
                "Type \"help\" to begin";
    }

    /**
     * Displays a welcome message.
     *
     * @return The welcome message to be displayed.
     */
    public String displayMessage() {
        return "CARL:\n" + this.message;
    }

}
