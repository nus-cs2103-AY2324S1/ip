package duke;

/**
 * The Ui class handles user interface interactions, such as displaying messages and prompts.
 */
public class Ui {

    /**
     * Constructs a Ui object.
     */
    public Ui() {}

    /**
     * Prints and returns a welcome message to the user.
     *
     * @return A welcome message ot the user.
     */
    public static String printWelcomeMessage() {
        System.out.println("Hi! I'm Philip");
        System.out.println("What can I do for you?");
        return "Hi! I'm Philip. " + "What can I do for you?";
    }

    /**
     * Prints and returns a farewell message to the user.
     *
     * @return A farewell message to the user.
     */
    public static String sendExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints an error message indicating a problem with loading tasks from a file.
     */
    public void printLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

}
