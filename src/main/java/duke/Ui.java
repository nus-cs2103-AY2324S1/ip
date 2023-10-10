package duke;

import java.util.ArrayList;

/**
 * The Ui class handles user interface interactions and provides methods for greetings and farewells.
 */
public class Ui {
    private static final String name = "Bartholomew Hamish Montgomery";
    private static final String line = "_______________________________________\n";

    /**
     * Displays a greeting message to the user.
     */
    public String greet() {
        return "I extend to you my utmost felicitations, User! I am " + name
                + "." + "\n" + "What may I do for you?";
    }
    /**
     * Displays a goodbye message to the user.
     */
    public String goodbye() {
        return "Until we meet once more in the near future, I bid you farewell.";
    }
    public String formatText(String text) {
        return line + text + "\n" + line;
    }

    /**
     * Generates a message confirming the addition of a task to the task list.
     *
     * @param task      The task that was added.
     * @param numTasks  The total number of tasks in the list after the addition.
     * @return A confirmation message for adding the task.
     */
    public String getAddTaskMessage(Task task, int numTasks) {
        return "Got it! I've added this task:"
                + "\n" + task.toString() + "\n"
                + "You now have " + numTasks
                + " task(s) in the list";
    }

    /**
     * Generates a message for an invalid date format.
     *
     * @return An error message indicating an invalid date format.
     */
    public String getInvalidDateMessage() {
        return "Invalid Date Format! Follow: YYYY-MM-DD";
    }

    /**
     * Generates a message confirming the removal of a task from the task list.
     *
     * @param tasks          The list of tasks.
     * @param taskIndex      The index of the removed task.
     * @param remainingTasks The remaining number of tasks in the list.
     * @return A confirmation message for removing the task.
     */
    public String getDeleteTaskMessage(ArrayList<Task> tasks, int taskIndex, int remainingTasks) {
        return "Got it! I've removed this task:"
                + "\n" + tasks.get(taskIndex).toString()
                + "\n" + "You now have " + remainingTasks
                + " task(s) in the list";
    }

    /**
     * Generates a message confirming the completion of a task.
     *
     * @param tasks     The list of tasks.
     * @param taskIndex The index of the completed task.
     * @return A confirmation message for marking a task as completed.
     */
    public String getMarkedMessage(ArrayList<Task> tasks, int taskIndex) {
        return "Great job! You've completed the following task:"
                + "\n" + tasks.get(taskIndex).toString();
    }

    /**
     * Generates a message confirming the task is marked as incomplete.
     *
     * @param tasks     The list of tasks.
     * @param taskIndex The index of the task marked as incomplete.
     * @return A confirmation message for marking a task as incomplete.
     */
    public String getUnmarkedMessage(ArrayList<Task> tasks, int taskIndex) {
        return "You've marked the following task as incomplete:"
                + "\n" + tasks.get(taskIndex).toString();
    }

    /**
     * Generates a message confirming the sorting of the task list.
     *
     * @return A confirmation message for sorting the task list.
     */
    public String getSortedMessage() {
        return "Okay, I've sorted your list! \n";
    }
}
