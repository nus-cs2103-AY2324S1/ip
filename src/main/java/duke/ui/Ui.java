package duke.ui;

/**
 * Implements the Ui of the chatbot.
 * Deals with how the chatbot interacts with the user.
 *
 * @author Andrew Daniel Janong
 */
public class Ui {
    /** A line break for chatbot responses */
    private static final String LINE = "____________________________________________________________";

    /**
     * Prints all lines in a structured manner.
     * Response printed will start and end with a line break.
     *
     * @param lines Lines to be printed.
     */
    public static void printLines(String ...lines) {
        System.out.println("\t" + LINE);
        for (String line : lines) {
            System.out.println("\t " + line);
        }
        System.out.println("\t" + LINE);
    }

    /**
     * Greets the user everytime chatbot opens.
     */
    public static void greetUser() {
        printLines("Hello I'm ADJ", "What can I do for you?");
    }

    /**
     * Prints an goodbye message when the user exits.
     */
    public static void printExitMessage() {
        printLines("Bye. Hope to see you again soon!");
    }
}
