package geraldbot.util;

import java.util.Scanner;

/**
 * Represents the user interface for interacting with GeraldBot.
 */
public class Ui {
    private final String line = "_____________________________________________________";

    private Scanner sc = new Scanner(System.in);

    /**
     * Greets the user with a welcome message.
     */
    public void greet() {
        System.out.println("Hello! I'm GeraldBot\n" + "What can I do for you?");
    }

    /**
     * Displays a farewell message.
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays a horizontal line separator.
     */
    public void showLine() {
        System.out.println(line);
    }

    /**
     * Reads user input from the console.
     *
     * @return The input provided by the user.
     */
    public String readInput() {
        String input = this.sc.nextLine();
        return input;
    }
}
