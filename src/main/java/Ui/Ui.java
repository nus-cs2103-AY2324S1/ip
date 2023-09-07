package ui;
import java.util.ArrayList;

import duke.DukeException;
import task.Task;
import tasklist.TaskList;

/**
 * The `Ui` class is responsible for handling the user interface of the BloopBot application.
 * It provides methods to display messages, read user input, and show information about tasks and commands.
 *
 * @author raydenlim
 * @version 0.0.0
 */
public class Ui {

    /**
     * Displays a welcome message to the user when the application starts.
     *
     * @return A welcome message.
     */
    public String showWelcome() {
        return "Hello!!! My name is BloopBloop\n" + "What can I do for you? :)";
    }

    /**
     * Displays a farewell message to the user when they exit the application.
     *
     * @return A farewell message.
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays an error message to the user.
     *
     * @param e The DukeException containing the error message.
     * @return An error message.
     */
    public String showError(DukeException e) {
        String errorMsg = e.getMessage();
        return "Woops, Error: " + errorMsg;
    }

    /**
     * Displays a list of available commands to the user.
     *
     * @return A list of available commands.
     */
    public String showCommands() {
        return "List of Commands: Add, Deadline, Event, Todo, Echo, Mark, Unmark, Delete, Find, Bye\n"
                + "1. Add - Add a task to the list\n"
                + "2. Deadline - Add a task with a deadline\n"
                + "3. Event - Add an event task\n"
                + "4. Todo - Add a todo task\n"
                + "5. Echo - Echo a message\n"
                + "6. Mark - Mark a task as done\n"
                + "7. Unmark - Unmark a task as done\n"
                + "8. Delete - Delete a task\n"
                + "9. Find - Find tasks by keyword\n"
                + "10. Bye - Exit the program";
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param taskList The TaskList containing the tasks to be displayed.
     * @return A formatted string containing the list of tasks or a message indicating no tasks.
     */
    public String showTasks(TaskList taskList) {
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks in your list:\n");
        output.append(taskList.displayTasks());
        return output.toString();
    }

    /**
     * Displays a message to confirm the addition of a task.
     *
     * @param task       The added task.
     * @param totalTasks The total number of tasks in the list.
     * @return A message confirming the addition of the task.
     */
    public String showTaskAdded(Task task, int totalTasks) {
        StringBuilder output = new StringBuilder();
        output.append("Got it. I've added this task:\n");
        output.append("   ").append(task).append("\n");
        output.append("Now you have ").append(totalTasks).append(" tasks in the list.");
        return output.toString();
    }

    /**
     * Displays a message to confirm the deletion of a task.
     *
     * @param task       The deleted task.
     * @param totalTasks The total number of tasks in the list.
     * @return A message confirming the deletion of the task.
     */
    public String showDeletedTask(Task task, int totalTasks) {
        StringBuilder output = new StringBuilder();
        output.append("Noted. I've removed this task:\n");
        output.append("   ").append(task).append("\n");
        output.append("Now you have ").append(totalTasks).append(" tasks in the list.");
        return output.toString();
    }

    /**
     * Displays an echoed message to the user.
     *
     * @param message The message to be echoed.
     * @return The echoed message.
     */
    public String showEcho(String message) {
        return "Echo: " + message;
    }

    /**
     * Displays a message to indicate that a task has been unmarked as done.
     *
     * @param taskToUnmark The task that was unmarked.
     * @return A message indicating the task was unmarked.
     */
    public String showTaskUnmarked(Task taskToUnmark) {
        return "Task unmarked: " + taskToUnmark.getDescription();
    }

    /**
     * Displays a message to indicate that a task has been marked as done.
     *
     * @param taskToUnmark The task that was marked as done.
     * @return A message indicating the task was marked.
     */
    public String showTaskMarked(Task taskToUnmark) {
        return "Task marked: " + taskToUnmark.getDescription();
    }

    /**
     * Displays a list of tasks that match a specified keyword in their descriptions.
     *
     * @param matchingTasks An ArrayList of tasks that match the specified keyword.
     * @return A formatted string containing the matching tasks or a message indicating no matches.
     */
    public String showMatchingTasks(ArrayList<Task> matchingTasks) {
        StringBuilder output = new StringBuilder();
        if (matchingTasks.isEmpty()) {
            output.append("No matching tasks found.");
        } else {
            output.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                output.append((i + 1)).append(".").append(matchingTasks.get(i).toString()).append("\n");
            }
        }
        return output.toString();
    }
}
