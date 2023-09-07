package duke;

/** Ui prints statements that the user will interact with */
public class Ui {

    /** Creates a new Ui object. */
    public Ui() {}

    /** Greets the user. */
    public void greet() {
        System.out.println("Hello! I'm Aikent\n" + "What can I do for you?");
    }

    /**
     * Bids farewell to users.
     *
     * @return Farewell text to user.
     */
    public String farewell() {
        return "I have saved all your task!\nBye. Hope to see you again soon!";
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
    public String showTaskAdded(Task task, int listSize) {
        StringBuilder reply = new StringBuilder("Got it. I've added this task:\n");
        reply.append(task.toString() + "\n");
        reply.append("Now you have " + listSize + " tasks in the list.");
        return reply.toString();
    }

    /** Informs the user that task is successfully deleted from the list.
     *
     * @param task Task to be deleted from the list.
     * @param listSize Size of list after deleting task.
     */
    public String showTaskDeleted(Task task, int listSize) {
        StringBuilder reply = new StringBuilder("Noted. I've removed this task:\n");
        reply.append(task.toString() + "\n");
        reply.append("Now you have " + listSize + " tasks in the list.");
        return reply.toString();
    }

    /** Informs user that the desired task has been marked as completed.
     *
     * @param task Task to be marked.
     */
    public String showTaskMarked(Task task) {
        StringBuilder reply = new StringBuilder("Nice! I've marked this task as done:\n");
        reply.append(task.toString());
        return reply.toString();
    }

    /** Informs the user that the desired task has been unmarked.
     *
     * @param task Task to be unmarked.
     */
    public String showTaskUnmarked(Task task) {
        StringBuilder reply = new StringBuilder("OK, I've marked this task as not done yet:\n");
        reply.append(task.toString());
        return reply.toString();
    }

}
