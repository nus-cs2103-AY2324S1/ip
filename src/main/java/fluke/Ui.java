package fluke;

import fluke.tasks.Task;

/**
 * This class contains all user interface related functionalities.
 */
public class Ui {
    private static final String CHATBOT_NAME = "Fluke";
    private static final String GREETING =
            "Hello! I'm " + CHATBOT_NAME + ", everything I do is down to luck!" + "\n"
                    + "Feeling lucky today?";
    private static final String GOODBYE = "Bye. Good luck!";

    /**
     * Greets the user.
     */
    public String getGreeting() {
        // introduce Fluke
        return GREETING;
    }

    /**
     * Says goodbye to the user.
     */
    public String getBye() {
        return GOODBYE;
    }

    /**
     * prints an error
     * @param message the error message
     */
    public String getError(String message) {
        return "☹ OOPS!!! " + message;
    }

    /**
     * shows the current list of tasks to the user.
     * @param tasks the corresponding TaskList.
     */
    public String getListOfTasks(TaskList tasks) {
        String response = "Here are the tasks we have currently!\n" + tasks.toString();
        return response;
    }

    /**
     * shows the addition of a task to the user.
     * @param addedTask the task added.
     * @param tasks the list of tasks.
     */
    public String getTaskAdded(Task addedTask, TaskList tasks) {
        return "(Scribbles randomly). Hope I got it right!\n"
                + "  " + addedTask + "\n"
                + "I think there are now " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * shows the deletion of a task to the user.
     * @param deletedTask the task deleted.
     * @param tasks the list of tasks.
     */
    public String getTaskDeleted(Task deletedTask, TaskList tasks) {
        return "fluke.tasks.Task deleted! I hope it's the right one:\n"
                + "  " + deletedTask + "\n"
                + "I think there are now " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Shows a task being marked as done to the user.
     * @param markedTask the task marked as done.
     */
    public String getTaskMarkedAsDone(Task markedTask) {
        return "I have marked this task as done, I hope it's the right one:\n"
                + "  " + markedTask;
    }

    /**
     * Shows a task being marked as not done to the user.
     * @param markedTask the task marked as not done.
     */
    public String getTaskMarkedAsUndone(Task markedTask) {
        return "I have marked this task as not done yet, I hope it's the right one:\n"
                + "  " + markedTask;
    }

    /**
     * Shows the user tasks with a certain keyword
     * @param tasksWithKeyword list of tasks to show
     */
    public String getTasksWithKeyword(TaskList tasksWithKeyword) {
        return "I have randomly picked out a few tasks. Looks like they have what you want!"
                + tasksWithKeyword;
    }

    /**
     * Shows a loading error to the user.
     */
    public String getLoadingError() {
        return getError("Failed to load!");
    }
}
