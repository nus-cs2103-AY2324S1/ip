package duke.ui;

import duke.tasks.Task;

/**
 * Represents the Ui Class.
 * Responsible for returning commands.
 *
 * @author Shishir
 */
public class Ui {
    /** Returns a greeting message. */
    public String greet() {
        return "Greetings, I am Jarvis! How may I assist you today?";
    }

    /** Returns a farewell message. */
    public String leave() {
        return "I shall now take my leave. Farewell!";
    }

    /**
     * Returns an acknowledgment message on successful addition to the list.
     * @param size Length of the list.
     * @param task Newly added task.
     */
    public String showAdd(int size, Task task) {
        return "Added the following task to the list.\n"
                + size + ") " + task.toString() + "\n"
                + "You currently have " + size + " tasks in your list.\n";
    }

    /**
     * Returns an acknowledgment message on a successful mark/unmark.
     * @param index Index of the newly marked/unmarked task.
     * @param task Newly marked/unmarked task.
     * @param isMark Mark if true, Unmark if false.
     */
    public String showStatus(int index, Task task, boolean isMark) {
        String message;
        if (isMark) {
            message = "The following task is marked as complete:\n";
        } else {
            message = "The following task has been unmarked:\n";
        }
        return message + index + ") " + task.toString() + "\n"
                + "Is there anything else I can assist you with?";
    }

    /**
     * Returns an acknowledgment message on a successful deletion of a task.
     * @param index Index of the newly deleted task.
     * @param task Newly deleted task.
     */
    public String showDelete(int index, Task task) {
        return "The following task has been removed:\n"
                + index + ") " + task.toString() + "\n"
                + "Is there anything else I can assist you with?";
    }

    /**
     * Returns an acknowledgment message on request to display all the tasks.
     * @param size Size of the task list.
     */
    public String showList(int size) {
        return size == 0
                ? "Your task list is empty! Add a task to view it here."
                : "Tasks displayed. Your guidance is requested.";
    }

    /**
     * Returns out the list of tasks which match with the keyword.
     * @param word Entered keyword.
     */
    public String showFind(String word) {
        return "Finding tasks that contain the entered keyword (" + word + ")";
    }
}
