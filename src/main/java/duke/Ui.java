package duke;

/**
 * The Ui class handles user interface interactions in the Duke application.
 * It provides methods to display messages, errors, and task-related information
 * to the user using the console.
 */
public class Ui {
    /** Constant divider used for separating UI sections. */
    private static final String DIVIDER = "      ____________________________________________________________";

    /**
     * Displays a divider line in the console.
     */
    public static void showLine() {
        System.out.println(DIVIDER);
    }

    /**
     * Displays a welcome message to the user.
     */
    public static void showWelcome() {
        showLine();
        System.out.println("        Hello! I'm Valerie!");
        System.out.println("        What can I do for you?");
        showLine();
    }

    /**
     * Displays an error message to the user.
     *
     * @param error The error message to be displayed.
     */
    public static void showError(String error) {
        showLine();
        System.out.println("        ERROR: " + error + "!");
        showLine();
    }

    /**
     * Displays a message indicating a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public static void showMarkedTask(Task task) {
        Ui.showLine();
        System.out.println("        Nice! I've marked this task as done:");
        System.out.println("            " + task);
        Ui.showLine();
    }

    /**
     * Displays a message indicating a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     */
    public static void showUnmarkedTask(Task task) {
        showLine();
        System.out.println("         OK! I've marked this task as not done yet:");
        System.out.println("            " + task);
        showLine();
    }

    /**
     * Displays a message indicating a task has been added to the task list.
     *
     * @param task     The task that has been added.
     * @param taskList The current task list.
     */
    public static void showAddedTask(Task task, TaskList taskList) {
        Ui.showLine();
        System.out.println("        Got it! I've added this task: ");
        System.out.println("            " + task);
        System.out.println("        Now you have " + taskList.getSize() + " tasks in the list.");
        Ui.showLine();
    }

    /**
     * Displays a message indicating a task has been deleted from the task list.
     *
     * @param task     The task that has been deleted.
     * @param taskList The current task list.
     */
    public static void showDeletedTask(Task task, TaskList taskList) {
        Ui.showLine();
        System.out.println("        Noted. I've removed this task:");
        System.out.println("            " + task);
        System.out.println("        Now you have " + taskList.getSize() + " tasks in the list.");
        Ui.showLine();
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param taskList The task list to be displayed.
     */
    public static void showList(TaskList taskList) {
        Ui.showLine();
        System.out.println("        Sure! Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            String str = String.format("            %d. %s", i + 1, taskList.getTask(i));
            System.out.println(str);
        }
        Ui.showLine();
    }

    public static void showMatchingTasks(TaskList matchingTasks) {
        Ui.showLine();
        System.out.println("        Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.getSize(); i++) {
            String str = String.format("            %d.%s", i + 1, matchingTasks.getTask(i));
            System.out.println(str);
        }
        Ui.showLine();
    }

    /**
     * Displays a farewell message to the user.
     */
    public static void showExit() {
        showLine();
        System.out.println("        Bye ~ Hope to see you again soon ~");
        showLine();
    }
}
