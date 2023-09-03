package duke.ui;

import java.util.List;

import duke.tasks.Task;

/**
 * UI of the application
 */
public class Ui {
    /**
     * Returns success message for setting marked value of a task.
     *
     * @param isMark boolean value whether to mark the task
     * @param task the task to be marked
     */
    public String showSuccessMark(boolean isMark, Task task) {
        String res = "";
        if (isMark) {
            res += "Nice! I've marked this task as done:\n";
        } else {
            res += "OK, I've marked this task as not done yet:\n";
        }
        res += String.format("%s\n", task);
        return res;
    }

    /**
     * Returns message after adding a task to Tasks.
     *
     * @param task the task to be added
     * @param totalTasks the updated total number of tasks
     */
    public String showAddTask(Task task, int totalTasks) {
        return "Got it. I've added this task:\n"
                + String.format("  %s\n", task)
                + String.format("Now you have %s tasks in the list.\n", totalTasks);
    }

    /**
     * Returns message with no tasks.
     */
    public String showNoTasks() {
        return "No tasks found!\n";
    }

    /**
     * Returns all tasks.
     *
     * @param tasks the tasks object to be printed
     * @param isFiltered boolean flag for whether the tasks are filtered
     */
    public String showTasks(List<Task> tasks, boolean isFiltered) {
        String header = isFiltered
                ? "Here are the matching tasks in your list:"
                : "Here are the tasks in your list:";
        String res = header + "\n";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            res += String.format("%s.%s\n", i + 1, task);
        }
        return res;
    }

    /**
     * Returns success message after deleting a task.
     *
     * @param task the task to be deleted
     * @param totalTasks total number of tasks left after deletion
     */
    public String showSuccessDelete(Task task, int totalTasks) {
        return "Noted. I've removed this task:\n"
                + String.format("%s\n", task)
                + "Now you have " + totalTasks + " tasks in the list.\n";
    }

    /**
     * Returns error messages.
     *
     * @param message the error message to be outputted
     */
    public String showError(String message) {
        return message + "\n";
    }

    /**
     * Returns a prompt to enter a command.
     */
    public String showUnknownCommand() {
        return "Please enter something :-)\n";
    }

    /**
     * Returns a success message for loading storage.
     *
     * @param filepath the filepath where the storage loaded data from
     */
    public String showSuccessLoadingStorage(String filepath) {
        return "Data has been restored from " + filepath + "\n";
    }

    /**
     * Returns an error message when data file is not found.
     */
    public String showErrorFileNotFound() {
        return "Data file not found, creating a new one\n";
    }

    /**
     * Returns an error message when loading of data failed.
     */
    public String showErrorLoadingFile() {
        return "Error creating new file, quitting program now...\n";
    }

    /**
     * Returns an error message when writing to data file failed.
     */
    public String showErrorWritingFile() {
        return "Error saving to file, quitting program now...\n";
    }

    /**
     * Returns a greeting message.
     */
    public String showGreetings() {
        return "Hello I'm lynn the koala <3\n" + "    What can I do for you?\n";
    }

    /**
     * Returns the exit message.
     */
    public String showExit() {
        return "Bye. Hope to see you again soon!";
    }
}
