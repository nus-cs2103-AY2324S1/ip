package ui;

import java.util.Scanner;

import enums.WoofMessage;
import woof.Woof;

/**
 * The `Ui` class provides user interface-related functionality for the Woof application.
 * It includes methods for generating and returning messages, getting user input, and printing divider lines.
 */
public class Ui {
    /**
     * Generates and returns a welcome message when the Woof application starts.
     *
     * @return A welcome message.
     */
    public static String getHelloWorldMessage() {
        return WoofMessage.HI.toFormattedValue();
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
     * Generates and returns a horizontal divider line as a string to separate messages in the chat window.
     *
     * @return A string representing a horizontal divider line.
     */
    public static String getDividerLine() {
        return '\n' + "═".repeat(Woof.getChatWidth());
    }
}
