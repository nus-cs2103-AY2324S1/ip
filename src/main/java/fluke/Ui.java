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
     * Shows the deletion of task(s) to the user.
     * @param tasks the list of tasks.
     * @param deletedTasks the task(s) deleted.
     * @return
     */
    public String getTaskDeleted(TaskList tasks, Task... deletedTasks) {
        return "fluke.tasks.Task deleted! I hope it's the right one:\n"
                + getSimpleTaskList(deletedTasks)
                + "I think there are now " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Shows tasks being marked as done to the user.
     * @param markedTasks the task(s) marked as done.
     */
    public String getTaskMarkedAsDone(Task... markedTasks) {
        return "I have marked this task as done, I hope it's the right one:\n"
                + getSimpleTaskList(markedTasks);
    }

    /**
     * Shows tasks being marked as not done to the user.
     * @param markedTasks the task(s) marked as not done.
     */
    public String getTaskMarkedAsUndone(Task... markedTasks) {
        return "I have marked this task as not done yet, I hope it's the right one:\n"
                + getSimpleTaskList(markedTasks);
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

    /**
     * Helper function to provide a simple representation of a list of tasks
     * @param tasks list of tasks
     * @return string representation of the list of tasks
     */
    private String getSimpleTaskList(Task[] tasks) {
        String taskList = "";
        for (Task t: tasks) {
            taskList += "  " + t + "\n";
        }
        return taskList;
    }
}
