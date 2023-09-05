package geraldbot.util;

import java.util.Scanner;

/**
 * Represents the user interface for interacting with GeraldBot.
 */
public class Ui {
    private Scanner sc = new Scanner(System.in);

    /**
     * Greets the user with a welcome message.
     */
    public void greet() {
        System.out.println("Hello! I'm GeraldBot\n" + "What can I do for you?");
    }

    /**
     * Returns a farewell message.
     */
    public String bye() {
        String message = "Bye. Hope to see you again soon!";
        return message;
    }
    /**
     * Reads the next line of input from the user.
     * @return The next line of input from the user.
     */
    public String readInput() {
        String input = this.sc.nextLine();
        return input;
    }
}
