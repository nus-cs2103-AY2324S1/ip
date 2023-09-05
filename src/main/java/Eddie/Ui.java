package Eddie;

/**
 * Class which handles the user interface.
 */
public class Ui {
    static String welcome = "Hello! I'm Eddie\n" +
            "What can I do for you?";

    /**
     * Prints the welcome message for when Eddie starts.
     */
    public static String welcome() {
        return welcome;
    }

    /**
     * Prints the message for when Eddie exits.
     */
    public static String exit() {
        String goodbye = "Bye. Hope to see you again soon!";
        return goodbye;
    }

    /**
     * Prints the message for when a task is deleted
     * @param task The description of the task.
     * @param size The current number of tasks left.
     */
    public static String removeTask(String task, int size) {
        String s = "Noted. I've removed this task: \n" +
                task + "\n" +
                "Now you have " + size + " tasks in the list.";
        return s;
    }

    /**
     * Prints the list of tasks currently loaded.
     * @param num The number assigned to the task.
     * @param name The description of the task.
     */
    public static String listTask(int num, String name) {
        String s = num + ". " + name;
        return s;
    }

    /**
     * Prints the message for when a task is added.
     * @param task The description for the task to be added.
     * @param size The number of tasks in the Tasklist.
     */
    public static String addTask(String task, int size) {
        String s = "Got it. I've added this task:\n "
                + task + "\n"
                + "Now you have " + size + " tasks in the list.";
        return s;
    }

    /**
     * Prints the message for when the tasks are cleared.
     */
    public static String clear(){
        return "List Cleared!";
    }

    public static String mark(int s) {
        return "Marked task " + s + " as done.";
    }

    public static String unmark(int s) {
        return "Marked task " + s + " as undone.";
    }
}
