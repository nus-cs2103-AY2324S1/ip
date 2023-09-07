package duke;

import duke.task.Task;
import java.util.Scanner;

/**
 * An interface that interacts with the user.
 */
public class Ui {
    /**
     * Prints a welcome message to the screen.
     */
    public void sayHi() {
        System.out.println("Hello! I'm BoxBox\nWhat can I do for you?");
    }

    /**
     * Prints a farewell message to the screen.
     */
    public void sayBye() {
        System.out.println("Bye! Hope to see you again!");
    }

    /**
     * Prints the provided string to the screen.
     *
     * @param s String to be printed.
     */
    public void print(String s) {
        System.out.println(s);
    }

    /**
     * Prints the provided task to the screen.
     *
     * @param t Task to be printed.
     */
    public void print(Task t) {
        System.out.println(t);
    }

    /**
     * Reads the input on the terminal and returns the input.
     * If nothing is on the terminal, returns an empty string.
     *
     * @return Input on the terminal.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextLine()) {
            return sc.nextLine();
        } else {
            return "";
        }
    }
}
