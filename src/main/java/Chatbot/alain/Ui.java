package chatbot.alain;

/**
 * User interface class for displaying messages and interactions with the Alain chatbot.
 */
public class Ui {
    private static String logo = "    _     __         _     _____   ___   __\n"
            + "   / \\   | |        / \\   |_   _| | |\\\\  | |\n"
            + "  / _ \\  | |       / _ \\    | |   | | \\\\ | |\n"
            + " / /_\\_\\ | |____  / /_\\_\\  _| |_  | |  \\\\| |\n"
            + "/_/   \\_\\|______|/ /   \\_\\|_____| |_|   \\__| \n";

    /**
     * Displays a line separator.
     */
    public static void showlines() {
        System.out.print("____________________________________________________________\n");
    }

    /**
     * Displays a welcome message.
     */
    public static void showWelcome() {
        showlines();
        System.out.print(logo + "\n");
        System.out.print("Hello! I'm Alain\nWhat can I do for you?\n");
        showlines();
    }

    /**
     * Displays a goodbye message.
     */
    public static void showGoodbye() {
        showlines();
        System.out.print("Bye. Hope to see you again soon!\n");
        showlines();
    }

    /**
     * Displays an error message.
     *
     * @param errorMessage The error message to display.
     */
    public static void showError(String errorMessage) {
        showlines();
        System.out.print(" OOPS!!! " + errorMessage + "\n");
        showlines();
    }

    /**
     * Displays the list of tasks.
     *
     * @param list The list of tasks to display.
     */
    public static void showList(TaskList list) {
        String output = "";
        showlines();
        output += "Here are the tasks in your list:\n";
        for (int i = 0; i < list.size(); i++) {
            output += " " + (i + 1) + ". " + list.getTask(i) + "\n";
        }
        System.out.print(output);
        showlines();
    }

    /**
     * Displays the list of tasks containing the given keyword.
     *
     * @param list The list of tasks to display.
     */
    public static void showListContainingKeyword(TaskList list) {
        String output = "";
        showlines();
        output += "Here are the matching tasks in your list:\n";
        for (int i = 0; i < list.size(); i++) {
            output += " " + (i + 1) + ". " + list.getTask(i) + "\n";
        }
        System.out.print(output);
        showlines();
    }

    /**
     * Displays a message indicating a removed task.
     *
     * @param removedTask The task that was removed.
     * @param list The updated list of tasks.
     */
    public static void showRemoveTask(Task removedTask, TaskList list) {
        showlines();
        String output = " Noted. I've removed this task:\n"
                + "   " + removedTask + "\n"
                + " Now you have " + list.size() + " tasks in the list.\n";
        System.out.print(output);
        showlines();
    }

    /**
     * Displays a message indicating an added task.
     *
     * @param task The task that was added.
     * @param list The updated list of tasks.
     */
    public static void showAddTask(Task task, TaskList list) {
        showlines();
        String output = " Got it. I've added this task:\n"
                + "   " + task + "\n"
                + " Now you have " + list.size() + " tasks in the list.\n";
        System.out.print(output);
        showlines();
    }

    /**
     * Displays a message indicating a marked task as done.
     *
     * @param numericPart The numeric part of the user input.
     * @param list The updated list of tasks.
     */
    public static void showMarkTask(String numericPart, TaskList list) {
        showlines();
        String output = " Nice! I've marked this task as done:\n"
                + "   " + list.getTask(Integer.parseInt(numericPart) - 1) + "\n";
        System.out.print(output);
        showlines();
    }

    /**
     * Displays a message indicating a marked task as not done.
     *
     * @param numericPart The numeric part of the user input.
     * @param list The updated list of tasks.
     */
    public static void showUnmarkTask(String numericPart, TaskList list) {
        showlines();
        String output = " Nice! I've marked this task as not done yet:\n"
                + "   " + list.getTask(Integer.parseInt(numericPart) - 1) + "\n";
        System.out.print(output);
        showlines();
    }
}
