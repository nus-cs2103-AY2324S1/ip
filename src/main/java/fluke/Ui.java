package fluke;

import fluke.tasks.Task;

/**
 * This class contains all user interface related functionalities.
 */
public class Ui {
    private final static String CHATBOT_NAME = "Fluke";
    private final static String LOGO =
            "    ________      __\n" +
                    "   / ____/ /_  __/ /_____\n" +
                    "  / /_  / / / / / //_/ _ \\\n" +
                    " / __/ / / /_/ / ,< /  __/\n" +
                    "/_/   /_/\\__,_/_/|_|\\___/";
    private final static String GREETING =
            "Hello! I'm " + CHATBOT_NAME + ", everything I do is down to luck!" + "\n" +
                    "Feeling lucky today?";
    private final static String GOODBYE = "Bye. Good luck!";

    /**
     * Greets the user.
     */
    public void greet() {
        // introduce Fluke
        System.out.println(LOGO);
        addHorizontalLine();
        System.out.println(GREETING);
        addHorizontalLine();
    }

    /**
     * Says goodbye to the user.
     */
    public void sayBye() {
        System.out.println(GOODBYE);
    }

    /**
     * Adds a horizontal line break
     */
    private void addHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * prints an error
     * @param message the error message
     */
    public void showError(String message) {
        System.out.println("☹ OOPS!!! " + message);
        addHorizontalLine();
    }

    /**
     * shows the current list of tasks to the user.
     * @param tasks the corresponding TaskList.
     */
    public void showListOfTasks(TaskList tasks) {
        System.out.println("Here are the tasks we have currently!");
        System.out.println(tasks);
        addHorizontalLine();
    }

    /**
     * shows the addition of a task to the user.
     * @param task the task added.
     * @param tasks the list of tasks.
     */
    public void showTaskAdded(Task task, TaskList tasks) {
        System.out.println("(Scribbles randomly). Hope I got it right!");
        System.out.println("  " + task);
        System.out.println("I think there are now " + tasks.getSize() + " tasks in the list.");
        addHorizontalLine();
    }

    /**
     * shows the deletion of a task to the user.
     * @param task the task deleted.
     * @param tasks the list of tasks.
     */
    public void showTaskDeleted(Task task, TaskList tasks) {
        System.out.println("fluke.tasks.Task deleted! I hope it's the right one:");
        System.out.println("  " + task);
        System.out.println("I think there are now " + tasks.getSize() + " tasks in the list.");
        addHorizontalLine();
    }

    /**
     * Shows a task being marked as done to the user.
     * @param task the task marked as done.
     */
    public void showTaskMarkedAsDone(Task task) {
        System.out.println("I have marked this task as done, I hope it's the right one:");
        System.out.println("  " + task);
        addHorizontalLine();
    }

    /**
     * Shows a task being marked as not done to the user.
     * @param task the task marked as not done.
     */
    public void showTaskMarkedAsUndone(Task task) {
        System.out.println("I have marked this task as not done yet, I hope it's the right one:");
        System.out.println("  " + task);
        addHorizontalLine();
    }

    /**
     * Shows a loading error to the user.
     */
    public void showLoadingError() {
        showError("Failed to load!");
    }
}
