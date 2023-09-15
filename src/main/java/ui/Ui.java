package ui;

import java.util.Scanner;

/**
 * The `Ui` class provides user interface-related functionality for the Woof application.
 * It includes methods for generating and returning messages, getting user input, and printing divider lines.
 */
public class Ui {

    /**
     * Returns the width of the chat window.
     *
     * @return The width of the chat window.
     */
    public static int getChatWidth() {
        return 51;
    }

    /**
     * Generates and returns a welcome message when the Woof application starts.
     *
     * @return A welcome message.
     */
    public static String getHelloWorldMessage() {
        return "Woof Woof! I'm Doggo\nWhat can I do for you?";
    }

    /**
     * Retrieves user input using a specified `Scanner` object and returns a user prompt.
     *
     * @param scanner The `Scanner` object for reading user input.
     * @return A user prompt.
     */
    public static String getUserInput(Scanner scanner) {
        return scanner.nextLine();
    }

    /**
     * Generates and returns a title header for the bot's responses.
     *
     * @return A message prompt for the bot.
     */
    public static String getBotTitle() {
        return getDividerLine() + "\nDoggo: ";
    }

    /**
     * Generates and returns a title header for the user's responses.
     *
     * @return A message prompt for the bot.
     */
    public static String getUserTitle() {
        return getDividerLine() + "\nYou: ";
    }

    /**
     * Returns a farewell message when the user exits the Woof application.
     *
     * @return A farewell message.
     */
    public static String getByeUserMessage() {
        return "Bye. Hope to see you again soon!\n"
                + "Closing Woof Woof...\n";
    }

    /**
     * Returns a message for cases when the bot is unable to understand the user's input.
     *
     * @return A message indicating confusion.
     */
    public static String getConfusedMessage() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(\n";
    }

    /**
     * Generates and returns a horizontal divider line as a string to separate messages in the chat window.
     *
     * @return A string representing a horizontal divider line.
     */
    public static String getDividerLine() {
        return "═".repeat(getChatWidth());
    }
}
