package ui;

import java.util.Scanner;

/**
 * The `Ui` class provides user interface-related functionality for the Duke application.
 * It includes methods for displaying messages, getting user input, and printing divider lines.
 */
public class Ui {

    /**
     * Returns the width of the chat window.
     *
     * @return The width of the chat window.
     */
    public static int getChatWidth() {
        return 80;
    }

    /**
     * Displays a welcome message when the Duke application starts.
     */
    public static void helloWorld() {
        System.out.println("Hello! I'm Jing Sheng");
        System.out.println("What can I do for you?");
    }

    /**
     * Retrieves user input using a specified `Scanner` object and displays a user prompt.
     *
     * @param scanner The `Scanner` object for reading user input.
     * @return The user's input as a `String`.
     */
    public static String getUserInput(Scanner scanner) {
        printDividerLine();
        System.out.print("You: ");
        return scanner.nextLine();
    }

    /**
     * Displays a message prompt for the bot's responses.
     */
    public static void getBotMessage() {
        printDividerLine();
        System.out.println("Bot: ");
    }

    /**
     * Displays a farewell message when the user exits the Duke application.
     */
    public static void showByeUser() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays a message for cases when the bot is unable to understand the user's input.
     */
    public static void showConfused() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Prints a horizontal divider line to separate messages in the chat window.
     */
    public static void printDividerLine() {
        System.out.println("═".repeat(getChatWidth()));
    }
}
