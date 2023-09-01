package duke;

import duke.exception.DukeException;


/**
 * This class represents the UI that interacts with the user.
 */
public class Ui {
    private final String line = "____________________________________________________________";

    /**
     * Prints the greeting message.
     */
    public void greet() {
        System.out.println(line);
        System.out.println("Hello! I'm Fong!");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    /**
     * Prints the goodbye message.
     */
    public void bye() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    /**
     * Prints the exception message.
     */
    public void explainException(DukeException e) {
        System.out.println(line);
        System.out.println(e.getMessage());
        System.out.println(line);
    }

    /**
     * Prints the invalid task message.
     */
    public void handleInvalid() {
        System.out.println(line);
        System.out.println("You did not enter a valid command.");
        System.out.println(line);
    }
}
