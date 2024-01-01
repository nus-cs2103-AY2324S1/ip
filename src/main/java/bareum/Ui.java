package bareum;

import java.util.Scanner;

/**
 * This class implements the thing that manages interactions with the user.
 */
public class Ui {
    /**
     * Scanner that reads in the user's input.
     */
    private static Scanner sc = new Scanner(System.in);

    /**
     * Prints out the message introducing Bareum to the user upon opening the chat.
     */
    public static String getWelcomeMessage() {
        return "Hello! I'm Bareum! What can I do for you? ^^";
    }

    /**
     * Prints out the message bidding the user farewell upon closing the chat.
     */
    public static String getGoodbyeMessage() {
        return "Bye! Hope to see you again soon ^^";
    }
}
