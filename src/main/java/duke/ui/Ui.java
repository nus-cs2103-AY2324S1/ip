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
     * Gets response string.
     *
     * @param lines Lines to be in the response.
     */
    public static String getResponse(String ...lines) {
        String response = "";
        for (String line : lines) {
            response += ("\t " + line + "\n");
        }

        return response;
    }

    /**
     * Gets a greeting message for when the user opens the chatbot.
     */
    public static String getGreetingMessage() {
        return getResponse("Hello I'm ADJ", "What can I do for you?");
    }

    /**
     * Gets a goodbye message when the user exits.
     */
    public static String getExitMessage() {
        return getResponse("Bye. Hope to see you again soon!");
    }
}
