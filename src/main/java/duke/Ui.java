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
    public static void helloMsg() {
        System.out.println(lines + "\n" + greeting + lines);
    }

    /**
     * Prints out the goodbye message.
     */
    public static void goodbyeMsg() {
        System.out.println(lines + "\n" + goodbye + lines);
    }

    /**
     * Displays all current tasks in the task list.
     */
    public static void listTasks() {
        System.out.println(lines + "\n        Here are the tasks in your list:\n");
        TaskList.listOut();
        System.out.println(lines);
    }

    /**
     * Displays message when task if successfully marked.
     * @param description the description of the task marked.
     */
    public static void markMsg(String description) {
        System.out.println(lines
                + "\n        Nice! I've marked this task as done: (≧▽≦)\n          "
                + description + "\n"
                + lines);
    }

    /**
     * Displays message when task is successfully unmarked.
     * @param description the description of the task unmarked.
     */
    public static void unmarkMsg(String description) {
        System.out.println(lines
                + "\n        OK, I've marked this task as not done yet: (≧▽≦)\n          "
                + description + "\n"
                + lines);
    }

    /**
     * Displays message when task is successfully added to the task list.
     * @param type the type of the task added.
     * @param description the description of the task added.
     * @param size the updated number of tasks in the task list.
     */
    public static void successfulAdd(String type, String description, Integer size) {
        System.out.println(lines + "\n         Got it. I've added this new " + type + ":\n            "
                + description + "\n         Now you have " + size + " tasks in the list.\n" + lines);
    }

    /**
     * Displays message when task is successfully deleted from the task list.
     * @param description the description of the task deleted.
     * @param size the updated number of tasks in the task list.
     */
    public static void successfulDelete(String description, Integer size) {
        System.out.println(lines + "\n         Noted, I've removed this task:\n              "
                + description + "\n         Now you have " + size + " tasks in the list"
                + "\n" + lines);
    }
}
