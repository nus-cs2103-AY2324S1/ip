package duke.main;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Handles interactions with the user interface, displaying messages
 * and receiving user input.
 */

public class Ui {
    private StringBuilder output;
    Ui() {
        output = new StringBuilder();
    }

    void resetOutput() {
        output.setLength(0);
    }

    String getOutput() {
        return output.toString();
    }

    private void addToOutput(String str) {
        output.append(str);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        addToOutput(message);
    }

    /**
     * Displays a completion message after adding a task.
     *
     * @param task The task that was added.
     * @param size The total number of tasks after adding the new task.
     */
    public void displayCompletionMessage(Task task , int size) {
        addToOutput("\tGot it. I've added this task:");
        addToOutput("\n\t\t" + task);
        addToOutput("\n\tNow you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message along with a task.
     *
     * @param message The message to be displayed.
     * @param task The task to be displayed.
     */
    public void printMessage(String message, Task task) {
        addToOutput("\t" + message + task.toString());
    }

    /**
     * Displays the exit message to the user.
     */
    public void printExit() {
        addToOutput("\tBye. Hope to see you again soon!");
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void printTaskList(ArrayList<Task> tasks, String message) {
        addToOutput(message);
        for (int i = 0; i < tasks.size(); i++) {
            addToOutput("\n" + (i + 1) + ". " + tasks.get(i).toString());
        }
    }

    /**
     * Displays a message after removing a task.
     *
     * @param removedTask The task that was removed.
     * @param size The total number of tasks after removing the task.
     */
    public void printDeleteMessage(Task removedTask, int size) {
        addToOutput("Noted. I've removed this task:\n\t" + removedTask);
        addToOutput("\nNow you have " + size + " tasks in the list.");
    }

}
