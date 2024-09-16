package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

/**
 * The Ui class handles user interface-related operations and generates user-friendly messages for the Duke chatbot.
 *
 * @author selwyn
 */
public class Ui {
    private static final String NAME = "DUKE PRINCE";

    /**
     * Generates a representation of the task list, optionally filtered by a search query.
     *
     * @param taskList   The task list to be represented.
     * @param isMatching Whether the task list is filtered by a search query.
     * @return A string representation of the task list.
     */
    public static String printTaskList(TaskList taskList, boolean isMatching) {
        return taskList.taskListRepresentation(isMatching);
    }

    /**
     * Generates a message indicating that a task has been successfully added to the task list.
     *
     * @param addedTask            The task that has been added.
     * @param numOfTasksAfterAdding The number of tasks in the list after adding the new task.
     * @return A message indicating the successful addition of the task.
     */
    public static String printAddedTask(Task addedTask, int numOfTasksAfterAdding) {
        String strToReturn = "Got it. I've added this task: \n" + addedTask.toString() + "\n";

        if (numOfTasksAfterAdding == 1) {
            strToReturn += "Now you have " + numOfTasksAfterAdding + " task in the list.";
        } else {
            strToReturn += "Now you have " + numOfTasksAfterAdding + " tasks in the list.";
        }

        return strToReturn;
    }

    /**
     * Generates a message indicating that the done status of a task has been changed.
     *
     * @param taskToChange The task whose done status has been changed.
     * @param isDone       Whether the task is marked as done or not.
     * @return A message indicating the change in task done status.
     */
    public static String printChangeTaskDoneStatus(Task taskToChange, boolean isDone) {
        String strToReturn = "";
        if (isDone) {
            strToReturn += "Nice! I've marked this task done:\n";
        } else {
            strToReturn += "OK, I've marked this task as not done yet:\n";
        }

        strToReturn += taskToChange.toString();
        return strToReturn;
    }

    /**
     * Generates a message indicating that a task has been successfully deleted from the task list.
     *
     * @param deletedTask    The task that has been deleted.
     * @param numOfTasksLeft The number of tasks remaining in the list after deletion.
     * @return A message indicating the successful deletion of the task.
     */
    public static String printDeletedTask(Task deletedTask, int numOfTasksLeft) {
        String strToReturn = "Noted. I've removed this task:\n";
        strToReturn += deletedTask.toString() + "\n";

        if (numOfTasksLeft == 1 || numOfTasksLeft == 0) {
            strToReturn += "Now you have " + numOfTasksLeft + " task in the list.";
        } else {
            strToReturn += "Now you have " + numOfTasksLeft + " tasks in the list.";
        }

        return strToReturn;
    }

    /**
     * Generates a message indicating statistics for the task list.
     *
     * @param todoSize     The number of todo tasks.
     * @param deadlineSize The number of deadline tasks.
     * @param eventSize    The number of event tasks.
     * @param percentDone  The percentage of tasks marked as done.
     * @return A message containing task statistics.
     */
    public static String printStatistics(int todoSize, int deadlineSize, int eventSize, double percentDone) {
        return "These are the statistics for your task list:\n"
                + "Number of todos: " + todoSize + "\n"
                + "Number of deadlines: " + deadlineSize + "\n"
                + "Number of events: " + eventSize + "\n"
                + "Percentage of tasks done: " + percentDone + "%\n";
    }

    /**
     * Generates a welcome greeting message.
     *
     * @return A welcome message for the user.
     */
    public static String printGreet() {
        return "Hello! I'm " + NAME + "\n" + "What can I do for you?";
    }

    /**
     * Generates a farewell message for when the user exits the application.
     *
     * @return A farewell message.
     */
    public static String printExit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Generates an error message for the user.
     *
     * @param errorMsg The error message to display.
     * @return An error message for the user.
     */
    public static String printError(String errorMsg) {
        return "OOPS! " + errorMsg;
    }
}
