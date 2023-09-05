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

    public Ui() {
    }

    /**
     * Get the details of the task the user wants to create.
     * @return Details of the task the user wants to create.
     */
    String getUserInput() {
       return sc.nextLine();
    }

    /**
     * Print out Bareum's reply to the user's input.
     * @param reply Bareum's reply to the user's input.
     */
    public static void reply(String reply) {
        String botName = "Bareum: ";
        String fullReply = botName + reply;
        System.out.println(fullReply);
    }

    /**
     * Prints out the message introducing Bareum to the user upon opening the chat.
     */
    public void showWelcomeMessage() {
        reply("Hello! I'm Bareum! What can I do for you? ^^");
    }

    /**
     * Prints out the message bidding the user farewell upon closing the chat.
     */
    public void showGoodbyeMessage() {
        reply("Bye! Hope to see you again soon ^^");
    }

    /**
     * Prints out a line in the chat.
     */
    public void showLine() {
        System.out.println("________________________");
    }
}
