package dot.ui;

import java.util.ArrayList;

/**
 * The Ui class is responsible for most of the UI interaction with user.
 */
public class Ui {

    private static final String HORIZONTAL_RULE = "_".repeat(38) + "\n";

    private static final String WELCOME_MESSAGE = "Hello! I'm Dot, "
            + "let me help you finish your tasks on the dot!\n"
            + "Datetime format for deadline/events: dd/MM/yyyy hhmm (e.g. 13/01/2020 1800)\n"
            + "Type 'help' to access the help menu!\n"
            + "What can I do(t) for you?";


    private static final String GOODBYE_MESSAGE = "Bye! DOnT forget to finish your tasks!\n";

    private static final String HELP_MESSAGE = "Welcome to the help menu, DonT worry ^o^!\n"
            + "<datetime> format for deadline/events: dd/MM/yyyy hhmm (e.g. 13/01/2020 1800)\n"
            + "<date> format for whatsgoingon: dd/MM/yyyy (e.g. 13/01/2020)\n\n"
            + "Commands:\n"
            + "todo <description> - Add a todo task\n"
            + "deadline <description> /by <deadline_description/datetime>\n"
            + "event <description> /from <start_description/datetime> /to <start_description/datetime>\n"
            + "list - List out all your tasks\n"
            + "unmark <taskNo> - Unmark task based on no. on the list\n"
            + "mark <taskNo> - Check off task based on no. on the list\n"
            + "delete <taskNo> - Delete task based on no. on the list\n"
            + "whatsgoingon <date> - See what deadlines/events are ongoing for given date\n"
            + "find <query> - List out all tasks that fit <query>"
            + "help - access this help menu\n"
            + "bye - close Dot";

    /**
     * Displays the welcome message for the user.
     *
     * @return The welcome message as String.
     */
    public static String getWelcomeMessage() {
        return wrapStringWithHorizontalRules(WELCOME_MESSAGE);
    }

    /**
     * Displays help message with descriptions of all commands.
     *
     * @return The help message as String.
     */
    public static String getHelpMessage() {
        return wrapStringWithHorizontalRules(HELP_MESSAGE);
    }

    /**
     * Displays the exit message for the user.
     *
     * @return The goodbye message as String.
     */
    public static String getGoodbyeMessage() {
        return wrapStringWithHorizontalRules(GOODBYE_MESSAGE);
    }

    /**
     * Displays argument with horizontal rules.
     *
     * @param str Input.
     * @return The input wrapped in HORIZONTAL_RULE.
     */
    public static String wrapStringWithHorizontalRules(String str) {
        return String.format("%s%s\n%s\n",
                HORIZONTAL_RULE, str, HORIZONTAL_RULE);
    }


    /**
     * This method displays a message after a mark or unmark command
     * is successfully executed.
     *
     * @param isMarking If true, the mark command is the one being run,
     *                  else it is the unmark command.
     * @param task      The task's string representation to display.
     * @return Mark or unmark message as String.
     */
    public static String getDisplayMarkOrUnmarkMessage(boolean isMarking, String task) {
        if (isMarking) {
            return wrapStringWithHorizontalRules(String.format(
                    "Nice! I've marked this task as done:\n  %s", task));
        } else {
            return wrapStringWithHorizontalRules(String.format(
                    "OK, I've marked this task as not done yet:\n  %s", task));
        }

    }

    /**
     * Displays an ArrayList of Strings to the UI.
     *
     * @param displayList list of strings (usually tasks) to display.
     * @return Concatenated array list as String.
     */
    public static String concatArrayList(ArrayList<String> displayList) {
        StringBuilder sb = new StringBuilder();
        for (String s : displayList) {
            sb.append(s).append('\n');
        }
        return wrapStringWithHorizontalRules(sb.toString().strip());
    }
}
