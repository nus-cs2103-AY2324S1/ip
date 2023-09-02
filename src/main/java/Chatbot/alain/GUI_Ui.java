package chatbot.alain;

/**
 * User interface class for displaying messages and interactions with the Alain chatbot.
 */
public class GUI_Ui {
    private String logo = "    _     __         _     _____   ___   __\n"
            + "   / \\   | |        / \\   |_   _| | |\\\\  | |\n"
            + "  / _ \\  | |       / _ \\    | |   | | \\\\ | |\n"
            + " / /_\\_\\ | |____  / /_\\_\\  _| |_  | |  \\\\| |\n"
            + "/_/   \\_\\|______|/ /   \\_\\|_____| |_|   \\__| \n";

    /**
     * Displays a line separator.
     */
    public String showlines() {
        return ("_______________________________________________\n");
    }

    /**
     * Displays a welcome message.
     */
    public String showWelcome() {
        String output = "";
        output += this.showlines();
        output += logo + "\n";
        output += "Hello! I'm Alain\nWhat can I do for you?\n";
        output += this.showlines();
        return output;
    }

    /**
     * Displays a goodbye message.
     */
    public String showGoodbye() {
        String output = "";
        output += this.showlines();
        output += "Bye. Hope to see you again soon!\n";
        output += this.showlines();
        return output;
    }

    /**
     * Displays an error message.
     *
     * @param errorMessage The error message to display.
     */
    public String showError(String errorMessage) {
        String output = "";
        output += this.showlines();
        output += " OOPS!!! " + errorMessage + "\n";
        output += this.showlines();
        return output;
    }

    /**
     * Displays the list of tasks.
     *
     * @param list The list of tasks to display.
     */
    public String showList(TaskList list) {
        String output = "";
        output += this.showlines();
        output += "Here are the tasks in your list:\n";
        for (int i = 0; i < list.size(); i++) {
            output += " " + (i + 1) + ". " + list.getTask(i) + "\n";
        }
        output += this.showlines();
        return output;
    }

    /**
     * Displays the list of tasks containing the given keyword.
     *
     * @param list The list of tasks to display.
     */
    public String showListContainingKeyword(TaskList list) {
        String output = "";
        output += this.showlines();
        output += "Here are the matching tasks in your list:\n";
        for (int i = 0; i < list.size(); i++) {
            output += " " + (i + 1) + ". " + list.getTask(i) + "\n";
        }
        output += this.showlines();
        return output;
    }

    /**
     * Displays a message indicating a removed task.
     *
     * @param removedTask The task that was removed.
     * @param list The updated list of tasks.
     */
    public String showRemoveTask(Task removedTask, TaskList list) {
        String output = "";
        output += this.showlines();
        output += " Noted. I've removed this task:\n"
                + "   " + removedTask + "\n"
                + " Now you have " + list.size() + " tasks in the list.\n";
        output += this.showlines();
        return output;
    }

    /**
     * Displays a message indicating an added task.
     *
     * @param task The task that was added.
     * @param list The updated list of tasks.
     */
    public String showAddTask(Task task, TaskList list) {
        String output = "";
        output += this.showlines();
        output += " Got it. I've added this task:\n"
                + "   " + task + "\n"
                + " Now you have " + list.size() + " tasks in the list.\n";
        output += this.showlines();
        return output;
    }

    /**
     * Displays a message indicating a marked task as done.
     *
     * @param numericPart The numeric part of the user input.
     * @param list The updated list of tasks.
     */
    public String showMarkTask(String numericPart, TaskList list) {
        String output = "";
        output += this.showlines();
        output += " Nice! I've marked this task as done:\n"
                + "   " + list.getTask(Integer.parseInt(numericPart) - 1) + "\n";
        output += this.showlines();
        return output;
    }

    /**
     * Displays a message indicating a marked task as not done.
     *
     * @param numericPart The numeric part of the user input.
     * @param list The updated list of tasks.
     */
    public String showUnmarkTask(String numericPart, TaskList list) {
        String output = "";
        output += this.showlines();
        output += " Nice! I've marked this task as not done yet:\n"
                + "   " + list.getTask(Integer.parseInt(numericPart) - 1) + "\n";
        output += this.showlines();
        return output;
    }
}
