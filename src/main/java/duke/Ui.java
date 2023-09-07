package duke;

/**
 * Deals with user interactions.
 */
public class Ui {
    //this is the new name for my chatbot
    private static String name;
    //these are the lines
    private static String lines;
    //this will be the greeting
    private static String greeting;
    //this will be goodbye
    private static String goodbye;

    /**
     * Constructs a new <code>Ui</code> object.
     */
    public Ui() {
        name = "dukey";
        lines = "_____________________________________";
        greeting = "        Hello! I'm " + name + "\n        What can I do for you?\n";
        goodbye = "        Bye. Hope to see you again soon! :D\n";
    }

    /**
     * Displays all current tasks in the task list.
     */
    public static String listTasks() {
        String tasks = lines + "\nHere are the tasks in your list:\n\n";
        tasks += TaskList.listOut();
        return tasks + lines;
    }

    /**
     * Displays message when task if successfully marked.
     * @param description the description of the task marked.
     */
    public static String markMsg(String description) {
        return lines + "\nNice! I've marked this task as done: (≧▽≦)\n"
                + description + "\n"
                + lines;
    }

    /**
     * Displays message when task is successfully unmarked.
     * @param description the description of the task unmarked.
     */
    public static String unmarkMsg(String description) {
        return lines
                + "\nOK, I've marked this task as not done yet: (≧▽≦)\n"
                + description + "\n"
                + lines;
    }

    /**
     * Displays message when task is successfully added to the task list.
     * @param type the type of the task added.
     * @param description the description of the task added.
     * @param size the updated number of tasks in the task list.
     */
    public static String successfulAdd(String type, String description, Integer size) {
        return lines + "\nGot it. I've added this new " + type + ":\n"
                + description + "\nNow you have " + size + " tasks in the list.\n" + lines;
    }

    /**
     * Displays message when task is successfully deleted from the task list.
     * @param description the description of the task deleted.
     * @param size the updated number of tasks in the task list.
     */
    public static String successfulDelete(String description, Integer size) {
        return lines + "\nNoted, I've removed this task:\n"
                + description + "\nNow you have " + size + " tasks in the list"
                + "\n" + lines;
    }
}
