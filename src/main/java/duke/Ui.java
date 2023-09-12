package duke;

/**
 * Ui handles the user interface and displays messages to interact with the user.
 * It provides methods for greeting the user and saying goodbye.
 */
public class Ui {

    /**
     * Displays a greeting message when Duke is initialized.
     */
    public void hello() {
        System.out.println("Hello! I'm froggie!");
        System.out.println("What can I do for you?");
    }

    /**
     * Displays a farewell message when Duke is about to exit.
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
