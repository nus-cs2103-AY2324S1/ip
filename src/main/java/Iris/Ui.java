package iris;

/**
 * The class responsible for user interface interactions in the Iris application.
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "      " +
            "_______________________________________________________________________________";

    /**
     * Displays the welcome message when the application starts.
     */
    public static void welcomeMsg() {
        String greetings = "Hello! I'm Iris! \n What can I do for you?";
        System.out.println(HORIZONTAL_LINE);
        System.out.println(greetings);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays the exit message when the user exits the application.
     */
    public static void exitMsg() {
        String byeMsg = "Bye. Hope to see you again soon!";
        System.out.println(HORIZONTAL_LINE);
        System.out.println(byeMsg);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a general response message to the user.
     *
     * @param message The message to be displayed.
     */
    public static void generalRespond(String message) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public static void markTaskMsg(Task task) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Nice! I've marked this task as done: \n" + task);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a message when a task is marked as not done.
     *
     * @param task The task that has been marked as not done.
     */
    public static void unmarkTaskMsg(Task task) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Okay, I've marked this task as not done yet: \n" + task);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param toDoList The ToDoList containing the tasks to be displayed.
     */
    public static void printTasks(ToDoList toDoList) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(toDoList);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays the number of tasks in the list.
     *
     * @param toDoList The ToDoList for which the number of tasks will be displayed.
     */
    public static void printLength(ToDoList toDoList) {
        int listSize = toDoList.size();
        System.out.println("Now you have " + listSize + " tasks in the list.");
    }
}
