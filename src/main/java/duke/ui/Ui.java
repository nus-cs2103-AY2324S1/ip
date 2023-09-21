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
            response += (line + "\n");
        }

        System.out.println(response);
        return response;
    }

    /**
     * Gets a greeting message for when the user opens the chatbot.
     */
    public static String getGreetingMessage() {
        return getResponse("Hello I'm ADJ",
                "What can I do for you?",
                "",
                "(type 'help' to get the list of commands)");
    }

    /**
     * Gets a goodbye message when the user exits.
     */
    public static String getExitMessage() {
        return getResponse("Bye. Hope to see you again soon!");
    }

    /**
     * Gets help page that provides a guide to new users.
     */
    public static String getHelpPage() {
        return getResponse("Here are a list of the available commands for this chatbot:",
                "- help: gets the help page with a list of available commands",
                "- todo [description]: creates a ToDo task with the description",
                "- deadline [description] /by [yyyy-mm-dd]: creates a Deadline task with its description and due date",
                "- event [description] /from [yyyy-mm-dd] /to [yyyy-mm-dd]: creates an Event task with its description, start date, and end date",
                "- delete [index]: deletes a task on a certain index (index starts from 1 and should be <= tasks length)",
                "- list: lists all tasks available along with the description of each task",
                "- mark [index]: marks a task on a certain index as done (index starts from 1 and should be <= tasks length)",
                "- unmark [index]: marks a task on a certain index as not done (index starts from 1 and should be <= tasks length)",
                "- bye: turns off chatbot"
        );
    }
}
