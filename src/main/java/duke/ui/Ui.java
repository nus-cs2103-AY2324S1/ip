package duke.ui;

import duke.exceptions.DukeException;

import java.util.Scanner;

/**
 * Implementation for User Interface.
 */
public class Ui {

    /**
     * Returns greeting message.
     *
     * @return Greeting message.
     */
    public static String greet() {
        return "Hello! I'm Max\n" + "What can I do for you?";
    }

    /**
     * Returns exit message.
     *
     * @return Exit message.
     */
    public static String exit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Gets user input.
     *
     * @return User input in String format.
     * @throws DukeException If any exception is encountered.
     */
    public static String getUserInput() throws DukeException {
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        return userInput;
    }

    /**
     * Displays message to user.
     *
     * @param message Message to be displayed.
     */
    public static void display(String message) {
        lines();
        System.out.println(message);
        lines();
    }

    /**
     * Draws horizontal lines.
     */
    public static void lines() {
        System.out.println("");
        for (int i = 0; i < 50; i++) {
            System.out.print("-");
        }
        System.out.println("");
    }
}
