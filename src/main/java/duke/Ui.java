package duke;

/** Ui prints statements that the user will interact with */
public class Ui {

    public Ui() {}

    /** Greets the user. */
    public void greet() {
        System.out.println("Hello! I'm Aikent\n" + "What can I do for you?");
    }

    /** Bids farewell to users */
    public void farewell() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the error message to the user.
     *
     * @param errorMsg Message to be printed.
     */
    public void showError(String errorMsg) {
        System.out.println(errorMsg);
    }

    /** Informs the user that task is successfully added to list.
     *
     * @param task Task to be added to list.
     * @param listSize Size of the list after task is added.
     */
    public void showTaskAdded(Task task, int listSize) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + listSize + " tasks in the list.");
    }

    /** Informs the user that task is successfully deleted from the list.
     *
     * @param task Task to be deleted from the list.
     * @param listSize Size of list after deleting task.
     */
    public void showTaskDeleted(Task task, int listSize) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + listSize + " tasks in the list.");
    }

    /** Informs user that the desired task has been marked as completed.
     *
     * @param task Task to be marked.
     */
    public void showTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /** Informs the user that the desired task has been unmarked.
     *
     * @param task Task to be unmarked.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

}
