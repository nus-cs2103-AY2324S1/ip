package ui;

/**
 * This class deals with the next user input and contains some standard prints.
 */
public class Ui {

    /**
     * Initialize the Ui object.
     */
    public Ui() {

    }

    /**
     * Prints the greeting of the program.
     */
    public static String greet() {
        return "Hello. I am Luxion.\n"
                + "What can I do for you?\n";
    }

    /**
     * Prints the exit of the program.
     */
    public static String exit() {
        return "Bye. See you soon.";
    }
}
