package duke;

/**
 * The this Class deals with the App's User Interface, inclusive of but not limited to
 * System.out formatting, line formatting, and other output formatting.
 */
public class Ui {

    /**
     * Prints any given text with the appropriate tab-spacing
     */
    public void tabPrinter(String s) {
        System.out.println("      " + s);
    }

    public static void staticTabPrinter(String s) {
        System.out.println("      " + s);
    }

    public void linePrinter() {
        this.tabPrinter
                ("___________________________________________________________");
        System.out.println(" ");
    }

    public void slicePrinter(String s) {
        this.linePrinter();
        this.tabPrinter(s);
        this.linePrinter();
    }

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

    public void byeScreen() {
        this.slicePrinter("Bye. Hope to see you again soon!");
    }

    public void taskListPrinter(TaskList tl) {
        this.linePrinter();
        this.tabPrinter("Here are the tasks in your list:");
        tl.taskIterator();
        this.linePrinter();
    }

    public void markPrinter(TaskList tl, int a) {
        this.linePrinter();
        this.tabPrinter("Nice! I've marked this task as done:");
        this.tabPrinter(tl.taskString(a));
        this.linePrinter();
    }

    public void unmarkPrinter(TaskList tl, int a) {
        this.linePrinter();
        this.tabPrinter("OK, I've marked this task as not done yet:");
        this.tabPrinter(tl.taskString(a));
        this.linePrinter();
    }

    public void addedTaskScreen(Task task, int a) {
        this.linePrinter();
        this.tabPrinter("Got it. I've added this task:");
        this.tabPrinter(" " + task.toString());
        this.sizePrinter(a);
        this.linePrinter();
    }

    public void removedTaskScreen(Task task, int a) {
        this.linePrinter();
        this.tabPrinter("Noted. I've removed this task:");
        this.tabPrinter(task.toString());
        this.linePrinter();
    }

    private void sizePrinter(int a) {
        this.tabPrinter(
                String.format("Now you have %d tasks in the list.",
                        a));
    }

    public void todoErrorPrinter() {
        this.slicePrinter(this.todoErrorString());
    }

    public String todoErrorString() {
        return "The description of a todo cannot be empty!";
    }


    public String deadlineErrorString() {
        return "The due date of a deadline cannot be empty!";
    }
    public void deadlineErrorPrinter() {
        this.slicePrinter(this.deadlineErrorString());
    }

    public String eventErrorString() {
        return "An event must have both start and end date";
    }
    public void eventErrorPrinter() {
        this.slicePrinter(this.eventErrorString());
    }

    public String unknownError() {
        return "I'm sorry I don't know what that means.";
    }


}
