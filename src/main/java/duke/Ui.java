package duke;

import duke.exceptions.DukeException;
import duke.exceptions.DukeIndexOutOfBoundsException;
import duke.tasks.Task;

import java.util.Scanner;

public class Ui {

    /**
     * Displays greeting message.
     */
    public void greet() {
        display("Hello! I'm Max\n" + "What can I do for you?");
    }

    /**
     * Displays exit message.
     */
    public void exit() {
        display("Bye. Hope to see you again soon!");
    }

    /**
     * Gets user input.
     *
     * @return User input in String format.
     * @throws DukeException If any exception is encountered.
     */
    public String getUserInput() throws DukeException {
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
