package arona.ui;

import java.util.ArrayList;

import arona.task.Task;
import arona.task.TaskList;

/**
 * The UI class handles user interface-related functionality in the GUI.
 */
public class Ui {

    /**
     * Returns the list of tasks as a formatted string.
     *
     * @param tasks The list of tasks to display.
     * @return A formatted string representing the list of tasks.
     */
    public static String showTaskList(TaskList tasks) {
        if (tasks.getTasks().isEmpty()) {
            return "Great job! No tasks right now, enjoy your day!\n";
        } else {
            StringBuilder message = new StringBuilder("Hello! Here is your list of tasks:\n");
            for (int i = 0; i < tasks.getTasks().size(); i++) {
                message.append((i + 1)).append(". ").append(tasks.getTasks().get(i)).append("\n");
            }
            return message.toString();
        }
    }

    /**
     * Returns a message confirming the addition of a task.
     *
     * @param task       The task that was added.
     * @param totalTasks The total number of tasks after adding.
     * @return A formatted string confirming the addition of a task.
     */
    public static String showTaskAdded(Task task, int totalTasks) {
        return "Got it! I've added this task:\n"
                + "  " + task + "\n"
                + "Now you have " + totalTasks + (totalTasks <= 1 ? " task" : " tasks")
                + " in the list.\n";
    }

    /**
     * Returns a message confirming the removal of a task.
     *
     * @param task       The task that was removed.
     * @param totalTasks The total number of tasks after removal.
     * @return A formatted string confirming the removal of a task.
     */
    public static String showTaskRemoved(Task task, int totalTasks) {
        return "Sure thing! I've removed this task:\n"
                + "  " + task + "\n"
                + "Now you have " + totalTasks + (totalTasks <= 1 ? " task" : " tasks")
                + " in the list.\n";
    }

    /**
     * Returns a message confirming that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     * @return A formatted string confirming the task as done.
     */
    public static String showTaskMarkedAsDone(Task task) {
        return "Awesome! I've marked this task as done:\n" + "  " + task + "\n";
    }

    /**
     * Returns a message confirming that a task has been marked as not done yet.
     *
     * @param task The task that was marked as not done yet.
     * @return A formatted string confirming the task as not done yet.
     */
    public static String showTaskUnmarked(Task task) {
        return "Sure thing! I've marked this task as not done yet:\n" + "  " + task + "\n";
    }
    /**
     * Returns an error message when a specified task does not exist.
     *
     * @return An error message indicating the task does not exist.
     */
    public static String showTaskDoesNotExist() {
        return "Sorry... I can't find the task. Please input the task number correctly!\n";
    }

    /**
     * Returns an error message as a string.
     *
     * @param e The exception that occurred.
     * @return A formatted string representing the error message.
     */
    public static String showErrorMessage(Exception e) {
        return e.getMessage() + "\n";
    }

    /**
     * Returns a message indicating that the user input is invalid.
     *
     * @return A string indicating invalid user input.
     */
    public static String showInvalidArgumentMessage() {
        return "Oops! I'm not quite sure what that means...\n";
    }

    /**
     * Returns the search results as a formatted string.
     *
     * @param result The TaskList containing the matching tasks.
     * @return A formatted string representing the search results.
     */
    public static String showSearchResult(TaskList result) {
        if (result.getTasks().isEmpty()) {
            return "Ehhh... There are no matching tasks.\n";
        } else {
            StringBuilder message = new StringBuilder("Yay! Here are your matching tasks:\n");
            ArrayList<Task> tasks = result.getTasks();
            for (int i = 0; i < tasks.size(); i++) {
                message.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
            }
            return message.toString();
        }
    }

    /**
     * Returns a message indicating that there is no valid command to undo.
     *
     * @return A string indicating that there is nothing to undo.
     */
    public static String showCannotUndo() {
        return "Hmm... There is no valid command I can undo.\n";
    }

    /**
     * Returns a message indicating that a delete command has been undone.
     *
     * @param task The task that was added back.
     * @return A string indicating the success of undoing a delete command.
     */
    public static String showUndoDeleteCommand(String task) {
        return "Okay... I've re-added back this task:\n"
                + "    " + task;
    }

    /**
     * Returns a message indicating that a mark command has been undone.
     *
     * @return A string indicating the success of undoing a mark command.
     */
    public static String showUndoMarkCommand(Task task) {
        return "Okay... I've re-updated the task to be not done.\n"
                + "    " + task;
    }

    /**
     * Returns a message indicating that an unmark command has been undone.
     *
     * @return A string indicating the success of undoing an unmark command.
     */
    public static String showUndoUnmarkCommand(Task task) {
        return "Okay... I've re-updated the task to be done.\n"
                + "    " + task;
    }

    /**
     * Returns a message indicating that a to-do command has been undone.
     *
     * @return A string indicating the success of undoing a to-do command.
     */
    public static String showUndoToDoCommand(Task task) {
        return "Okay... I've deleted the recently added task:\n"
                + "    " + task;
    }

    /**
     * Returns a message indicating that an event command has been undone.
     *
     * @return A string indicating the success of undoing an event command.
     */
    public static String showUndoEventCommand(Task task) {
        return "Okay... I've deleted the recently added task.\n"
                + "    " + task;
    }

    /**
     * Returns a message indicating that a deadline command has been undone.
     *
     * @return A string indicating the success of undoing a deadline command.
     */
    public static String showUndoDeadlineCommand(Task task) {
        return "Okay... I've deleted the recently added task:\n"
                + "    " + task;
    }
}
