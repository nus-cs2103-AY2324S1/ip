package chatbot.alain;

/**
 * User interface class for displaying messages and interactions with the Alain chatbot.
 */
public class GuiUi {
    private static String logo = "    _        _____ \n"
            + "   / \\       |_   _| \n"
            + "  / _ \\        | |   \n"
            + " / /_\\ \\     _| |_  \n"
            + "/_/    \\_\\ |____| \n";

    /**
     * Displays a line separator.
     */
    public static String showlines() {
        return ("_______________________________________________\n");
    }

    /**
     * Displays a welcome message.
     */
    public static String showWelcome() {
        String output = "";
        output += showlines();
        output += logo + "\n";
        output += "Hello! I'm Ai \nWhat can I do for you, Conan?\n";
        output += showlines();
        return output;
    }

    /**
     * Displays a goodbye message.
     */
    public static String showGoodbye() {
        String output = "";
        output += showlines();
        output += "Bye. Hope to see you again soon, Conan!\n";
        output += showlines();
        return output;
    }

    /**
     * Displays an error message.
     *
     * @param errorMessage The error message to display.
     */
    public static String showError(String errorMessage) {
        String output = "";
        output += showlines();
        output += " OOPS!!! " + errorMessage + "\n";
        output += showlines();
        return output;
    }

    /**
     * Displays the list of tasks.
     *
     * @param list The list of tasks to display.
     */
    public static String showList(TaskList list) {
        String output = "";
        output += showlines();
        output += "Here are the tasks in your list:\n";
        for (int i = 0; i < list.size(); i++) {
            output += " " + (i + 1) + ". " + list.getTask(i) + "\n";
        }
        output += showlines();
        return output;
    }

    /**
     * Displays the list of tasks containing the given keyword.
     *
     * @param list The list of tasks to display.
     */
    public static String showListContainingKeyword(TaskList list) {
        String output = "";
        output += showlines();
        output += "Here are the matching tasks in your list:\n";
        for (int i = 0; i < list.size(); i++) {
            output += " " + (i + 1) + ". " + list.getTask(i) + "\n";
        }
        output += showlines();
        return output;
    }

    /**
     * Displays a message indicating a removed task.
     *
     * @param removedTask The task that was removed.
     * @param list The updated list of tasks.
     */
    public static String showRemoveTask(Task removedTask, TaskList list) {
        String output = "";
        output += showlines();
        output += " Noted. I've removed this task:\n"
                + "   " + removedTask + "\n"
                + " Now you have " + list.size() + " tasks in the list.\n";
        output += showlines();
        return output;
    }

    /**
     * Displays a message indicating an added task.
     *
     * @param task The task that was added.
     * @param list The updated list of tasks.
     */
    public static String showAddTask(Task task, TaskList list) {
        String output = "";
        output += showlines();
        output += " Got it. I've added this task:\n"
                + "   " + task + "\n"
                + " Now you have " + list.size() + " tasks in the list.\n";
        output += showlines();
        return output;
    }

    /**
     * Displays a message indicating a marked task as done.
     *
     * @param numericPart The numeric part of the user input.
     * @param list The updated list of tasks.
     */
    public static String showMarkTask(String numericPart, TaskList list) {
        String output = "";
        output += showlines();
        output += " Nice! I've marked this task as done:\n"
                + "   " + list.getTask(Integer.parseInt(numericPart) - 1) + "\n";
        output += showlines();
        return output;
    }

    /**
     * Displays a message indicating a marked task as not done.
     *
     * @param numericPart The numeric part of the user input.
     * @param list The updated list of tasks.
     */
    public static String showUnmarkTask(String numericPart, TaskList list) {
        String output = "";
        output += showlines();
        output += " Nice! I've marked this task as not done yet:\n"
                + "   " + list.getTask(Integer.parseInt(numericPart) - 1) + "\n";
        output += showlines();
        return output;
    }
}
