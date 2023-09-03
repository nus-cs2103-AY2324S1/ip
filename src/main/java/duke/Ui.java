package duke;

/**
 * The UI Class deals with the App's User Interface, inclusive of but not limited to
 * System.out formatting, line formatting, and other output formatting.
 */
public class Ui {

    /** Default constructor */
    public Ui() {}

    /**
     * Prints any given text with the appropriate tab-spacing.
     *
     * @param s The input String to be printed.
     */
    public void tabPrinter(String s) {
        System.out.println("      " + s);
    }

    /**
     * Prints any given text with the appropriate tab-spacing,
     * from a static context. Used when it is inappropriate to create
     * a new Ui object.
     *
     * @param s The input String to be printed.
     */
    public static void staticTabPrinter(String s) {
        System.out.println("      " + s);
    }

    /**
     * Prints a sequence of dashes to cumulatively form a line separator.
     */
    public void linePrinter() {
        this.tabPrinter
                ("___________________________________________________________");
        System.out.println(" ");
    }

    /**
     * Sandwiches a String of text in-between two line separators.
     *
     * @param s The input String to be printed.
     */
    public void slicePrinter(String s) {
        this.linePrinter();
        this.tabPrinter(s);
        this.linePrinter();
    }

    /**
     * Prints the start screen with basic usage information.
     */
    public void startScreen() {
        this.linePrinter();
        this.tabPrinter("Hello! I'm ChatterBox");
        this.tabPrinter("What can I do for you?");
        System.out.println("");
        this.tabPrinter("Available commands:");
        this.tabPrinter("todo <TASK>");
        this.tabPrinter("deadline <TASK> /by YYYY-MM-DD");
        this.tabPrinter("event <TASK> /from <START> /to <END>");
        this.linePrinter();
    }

    /** Prints the "Goodbye" screen */
    public void byeScreen() {
        this.slicePrinter("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the list of tasks inside a given TaskList object.
     *
     * @param tl The given TaskList object.
     */
    public void taskListPrinter(TaskList tl) {
        this.linePrinter();
        this.tabPrinter("Here are the tasks in your list:");
        tl.taskIterator();
        this.linePrinter();
    }

    /**
     * Prints the list of tasks matching a given expression.
     * @param tl The given TaskList object.
     * @param s The string pattern to search.
     */
    public void findListPrinter(TaskList tl, String s) {
        this.linePrinter();
        this.tabPrinter("Here are the matching tasks in your list:");
        tl.findTask(s);
        this.linePrinter();
    }


    /**
     * Prints the process of marking a task as done.
     *
     * @param tl The given TaskList object.
     * @param a The index of the Task being marked as done.
     */
    public void markPrinter(TaskList tl, int a) {
        this.linePrinter();
        this.tabPrinter("Nice! I've marked this task as done:");
        this.tabPrinter(tl.taskString(a));
        this.linePrinter();
    }

    /**
     * Prints the process of marking a task as undone.
     *
     * @param tl The given TaskList object.
     * @param a The index of the Task being marked as undone.
     */
    public void unmarkPrinter(TaskList tl, int a) {
        this.linePrinter();
        this.tabPrinter("OK, I've marked this task as not done yet:");
        this.tabPrinter(tl.taskString(a));
        this.linePrinter();
    }

    /**
     * Prints the size of a TaskList based on a given input integer.
     *
     * @param a The int input equalling the current size of the TaskList object.
     */
    private void sizePrinter(int a) {
        this.tabPrinter(
                String.format("Now you have %d tasks in the list.",
                        a));
    }

    /**
     * Prints the process of adding a Task.
     *
     * @param task The given Task
     * @param a The int input equalling the current size of the TaskList object
     */
    public void addedTaskScreen(Task task, int a) {
        this.linePrinter();
        this.tabPrinter("Got it. I've added this task:");
        this.tabPrinter(" " + task.toString());
        this.sizePrinter(a);
        this.linePrinter();
    }

    /**
     * Prints the process of removing a Task.
     *
     * @param task The given Task
     * @param a The int input equalling the current size of the TaskList object
     */
    public void removedTaskScreen(Task task, int a) {
        this.linePrinter();
        this.tabPrinter("Noted. I've removed this task:");
        this.tabPrinter(task.toString());
        this.linePrinter();
    }


    /** Returns an Error String for ToDo objects */
    public String todoErrorString() {
        return "The description of a todo cannot be empty!";
    }

    public void todoErrorPrinter() {
        this.slicePrinter(this.todoErrorString());
    }

    /** Returns an Error String for Deadline objects */
    public String deadlineErrorString() {
        return "The due date of a deadline cannot be empty!";
    }
    public void deadlineErrorPrinter() {
        this.slicePrinter(this.deadlineErrorString());
    }

    /** Returns an Error String for Event objects */
    public String eventErrorString() {
        return "An event must have both start and end date";
    }
    public void eventErrorPrinter() {
        this.slicePrinter(this.eventErrorString());
    }

    /** Returns an Error String for Unknown Errors. */
    public String unknownError() {
        return "I'm sorry I don't know what that means.";
    }


}
