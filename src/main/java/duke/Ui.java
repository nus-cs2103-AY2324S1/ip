package duke;

/**
 * The Ui class is responsible for handling user interface interactions, such as displaying messages and prompts.
 */
public class Ui {

    /**
     * Constructs a Ui object.
     */
    public Ui() {}

    /**
     * Prints a welcome message to the user.
     */
    public static void printWelcomeMessage() {
        System.out.println("Hello! I'm BbabBBB");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints a farewell message to the user.
     */
    public static void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints an error message indicating a problem with loading tasks from a file.
     */
    public void printLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

}
