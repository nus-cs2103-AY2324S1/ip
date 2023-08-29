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
    public static void welcome() {
        System.out.println(welcome);
    }

    /**
     * Prints the message for when Eddie exits.
     */
    public static void exit() {
        String goodbye = "Bye. Hope to see you again soon!";
        System.out.println(goodbye);
    }

    /**
     * Prints the message for when a task is deleted
     * @param task The description of the task.
     * @param size The current number of tasks left.
     */
    public static void removeTask(String task, int size) {
        System.out.println("Noted. I've removed this task: \n" +
                task + "\n" +
                "Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints the list of tasks currently loaded.
     * @param num The number assigned to the task.
     * @param name The description of the task.
     */
    public static void listTask(int num, String name) {
        System.out.println(num + ". " + name);
    }

    /**
     * Prints the message for when a task is added.
     * @param task The description for the task to be added.
     * @param size The number of tasks in the Tasklist.
     */
    public static void addTask(String task, int size) {
        System.out.println("Got it. I've added this task:\n "
                + task + "\n"
                + "Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints the message for when the tasks are cleared.
     */
    public static void clear(){
        System.out.println("List Cleared!");
    }
}
