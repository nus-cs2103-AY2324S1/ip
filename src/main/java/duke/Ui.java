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
        lines = "        ____________________________________________________________";
        greeting = "        Hello! I'm " + name + "\n        What can I do for you?\n";
        goodbye = "        Bye. Hope to see you again soon! :D\n";
    }

    /**
     * Prints out the hello message when the chatbot first starts.
     */
    public static String helloMsg() {
        return lines + "\n" + greeting + lines;
    }

    /**
     * Prints out the goodbye message.
     */
    public static String goodbyeMsg() {
        return lines + "\n" + goodbye + lines;
    }

    /**
     * Displays all current tasks in the task list.
     */
    public static String listTasks() {
        String tasks = lines + "\n        Here are the tasks in your list:\n";
        tasks += TaskList.listOut();
        return tasks + lines;
    }

    /**
     * Displays message when task if successfully marked.
     * @param description the description of the task marked.
     */
    public static String  markMsg(String description) {
        return lines
                + "\n        Nice! I've marked this task as done: (≧▽≦)\n          "
                + description + "\n"
                + lines;
    }

    /**
     * Displays message when task is successfully unmarked.
     * @param description the description of the task unmarked.
     */
    public static String unmarkMsg(String description) {
        return lines
                + "\n        OK, I've marked this task as not done yet: (≧▽≦)\n          "
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
        return lines + "\n         Got it. I've added this new " + type + ":\n            "
                + description + "\n         Now you have " + size + " tasks in the list.\n" + lines;
    }

    /**
     * Displays message when task is successfully deleted from the task list.
     * @param description the description of the task deleted.
     * @param size the updated number of tasks in the task list.
     */
    public static String successfulDelete(String description, Integer size) {
        return lines + "\n         Noted, I've removed this task:\n              "
                + description + "\n         Now you have " + size + " tasks in the list"
                + "\n" + lines;
    }
}
