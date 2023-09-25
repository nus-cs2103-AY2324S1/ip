package duke;

/**
 * The UI Class deals with the App's User Interface, inclusive of but not limited to
 * System.out formatting, line formatting, and other output formatting.
 */
public class Ui {
    private static final String TABS_SPACING = "_________________________";
    private static final String WHITE_SPACING = "      ";
    /** Default constructor */
    public Ui() {}

    /**
     * Prints any given text with the appropriate tab-spacing.
     *
     * @param s The input String to be printed.
     */
    public String tabPrinter(String s) {
        return WHITE_SPACING + s;
    }

    /**
     * Prints any given text with the appropriate tab-spacing,
     * from a static context. Used when it is inappropriate to create
     * a new Ui object.
     *
     * @param s The input String to be printed.
     */
    public static String staticTabPrinter(String s) {
        return WHITE_SPACING + s;
    }

    /**
     * Prints a sequence of dashes to cumulatively form a line separator.
     */
    public String linePrinter() {
        return this.tabPrinter(TABS_SPACING);

    }

    /**
     * Sandwiches a String of text in-between two line separators.
     *
     * @param s The input String to be printed.
     */
    public String slicePrinter(String s) {
        String finalOutput = "";
        finalOutput += this.linePrinter()
                + "\n"
                + this.tabPrinter(s)
                + "\n"
                + this.linePrinter();

        return finalOutput;
    }

    /**
     * Prints the start screen with basic usage information.
     */
    public String startScreen() {
        String finalOutput = "";
        finalOutput += this.linePrinter()
                + "\n"
                + this.tabPrinter("Hello! I'm ChatterBox!")
                + "\n"
                + this.tabPrinter("What can I do for you?")
                + "\n"
                + this.tabPrinter("Available commands:")
                + "\n"
                + this.tabPrinter("todo <TASK>")
                + "\n"
                + this.tabPrinter("deadline <TASK> /by YYYY-MM-DD")
                + "\n"
                + this.tabPrinter("event <TASK> /from <START> /to <END>")
                + "\n"
                + this.tabPrinter("list")
                + "\n"
                + this.tabPrinter("mark/unmark/delete <INDEX>")
                + "\n"
                + this.linePrinter();

        return finalOutput;
    }

    /** Prints the "Goodbye" screen */
    public String byeScreen() {
        return this.slicePrinter("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the list of tasks inside a given TaskList object.
     *
     * @param tl The given TaskList object.
     */
    public String taskListPrinter(TaskList tl) {
        String finalOutput = "";

        finalOutput += this.linePrinter()
                + "\n"
                + this.tabPrinter("Here are the tasks in your list:")
                + "\n"
                + tl.taskIterator()
                + this.linePrinter();

        return finalOutput;
    }

    /**
     * Prints the list of tasks matching a given expression.
     * @param tl The given TaskList object.
     * @param s The string pattern to search.
     */
    public String findListPrinter(TaskList tl, String s) {
        String finalOutput = "";

        finalOutput = this.linePrinter()
                + "\n"
                + this.tabPrinter("Here are the matching tasks in your list:")
                + "\n"
                + tl.findTask(s)
                + this.linePrinter();

        return finalOutput;
    }


    /**
     * Prints the process of marking a task as done.
     *
     * @param tl The given TaskList object.
     * @param a The index of the Task being marked as done.
     */
    public String markPrinter(TaskList tl, int a) {
        String finalOutput = "";

        finalOutput += this.linePrinter()
                + "\n"
                + this.tabPrinter("Nice! I've marked this task as done:")
                + "\n"
                + this.tabPrinter(tl.taskString(a))
                + this.linePrinter();

        return finalOutput;
    }

    /**
     * Prints the process of marking a task as undone.
     *
     * @param tl The given TaskList object.
     * @param a The index of the Task being marked as undone.
     */
    public String unmarkPrinter(TaskList tl, int a) {
        String finalOutput = "";

        finalOutput += this.linePrinter()
                + "\n"
                + this.tabPrinter("OK, I've marked this task as not done yet:")
                + "\n"
                + this.tabPrinter(tl.taskString(a))
                + this.linePrinter();

        return finalOutput;

    }

    /**
     * Prints the size of a TaskList based on a given input integer.
     *
     * @param a The int input equalling the current size of the TaskList object.
     */
    private String sizePrinter(int a) {
        return this.tabPrinter(
                String.format("Now you have %d tasks in the list.",
                        a));
    }

    /**
     * Prints the process of adding a Task.
     *
     * @param task The given Task
     * @param a The int input equalling the current size of the TaskList object
     */
    public String addedTaskScreen(Task task, int a) {
        String finalOutput = "";

        finalOutput += this.linePrinter()
                + "\n"
                + this.tabPrinter("Got it. I've added this task:")
                + "\n"
                + this.tabPrinter(" " + task.toString())
                + "\n"
                + this.sizePrinter(a)
                + "\n"
                + this.linePrinter();

        return finalOutput;
    }

    /**
     * Prints the process of removing a Task.
     *
     * @param task The given Task
     * @param a The int input equalling the current size of the TaskList object
     */
    public String removedTaskScreen(Task task, int a) {
        String finalOutput = "";

        finalOutput += this.linePrinter()
                + "\n"
                + this.tabPrinter("Noted. I've removed this task:")
                + "\n"
                + this.tabPrinter(task.toString())
                + "\n"
                + this.linePrinter();

        return finalOutput;
    }


    /** Returns an error message String for ToDo objects */
    public String todoErrorString() {
        return "The description of a todo cannot be empty!";
    }

    /** Returns an error message String for Deadline objects */
    public String deadlineErrorString() {
        return "The due date of a deadline cannot be empty!";
    }

    /** Returns an error message String for Event objects */
    public String eventErrorString() {
        return "An event must have both start and end date";
    }

    /** Returns an error message String for Unknown Errors. */
    public String unknownErrorString() {
        return "I'm sorry I don't know what that means.";
    }

    /** Returns an error message String for File related errors. */
    public String fileErrorString() {
        return "A file-related error occurred. Please try again.";
    }

    /** Returns a success message String for mass removal of tasks. */
    public String removedAllTaskScreen() {
        return "Successfully removed all tasks.";
    }

    /** Returns an error message String for index-related errors. */
    public String indexErrorString() {
        return "Invalid index, try again with this format: <command> <number>";
    }

    /** Returns an error message String for task-finding related errors. */
    public String textErrorString() {
        return "Invalid text, try again with this format <command> <text>";
    }

    /** Returns an error message String when the task list is empty */
    public String emptyListErrorString() {
        return "Your task list is empty!";
    }

    /** Returns an error message String when a wrong date format is input. */
    public String deadlineFormatErrorString() {
        return "Deadline must be in the format YYYY-MM-DD";
    }

    /** Returns a generic error message for all non-specific errors. */
    public String generalErrorString() {
        return "An unknown error occurred. Please try again. " +
                "Enter help for a list of valid commands";
    }
}
