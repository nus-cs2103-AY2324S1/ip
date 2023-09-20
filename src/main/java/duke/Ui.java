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
    public String getAddTaskMessage(Task task, int numTasks) {
        return "Got it! I've added this task:"
                + "\n" + task.toString() + "\n"
                + "You now have " + numTasks
                + " task(s) in the list";
    }
    public String getInvalidDateMessage() {
        return "Invalid Date Format! Follow: YYYY-MM-DD";
    }
    public String getDeleteTaskMessage(ArrayList<Task> tasks, int taskIndex, int remainingTasks) {
        return "Got it! I've removed this task:"
                + "\n" + tasks.get(taskIndex).toString()
                + "\n" + "You now have " + remainingTasks
                + " task(s) in the list";
    }
    public String getMarkedMessage(ArrayList<Task> tasks, int taskIndex) {
        return "Great job! You've completed the following task:"
                + "\n" + tasks.get(taskIndex).toString();
    }
    public String getUnmarkedMessage(ArrayList<Task> tasks, int taskIndex) {
        return "You've marked the following task as incomplete:"
                + "\n" + tasks.get(taskIndex).toString();
    }
    public String getSortedMessage() {
        return "Okay, I've sorted your list! \n";
    }
}
