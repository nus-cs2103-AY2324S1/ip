package duke;

import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the object that displays output from the chatbot.
 */
public class UI {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays the current tasks list.
     * @param list The current tasks list
     * @return The response string
     */
    public String displayList(TaskList list) {
        if (list.isEmpty()) {
            return "There are no tasks in the list";
        } else {
            return list.toString();
        }
    }

    /**
     * Displays what task is added to the list.
     * @param task The task to be added
     * @param totalSize The total size of the list
     * @return The response string
     */
    public String displayAddToList(Task task, int totalSize) {
        String response = "";
        response += "This task is added to the list\n";
        response += task.toString();
        response += String.format("\nYou now have %d tasks in your list%n", totalSize);
        return response;
    }

    /**
     * Displays what task is removed from the list.
     * @param task The task to be removed
     * @param totalSize The total size of the list
     * @return The response string
     */
    public String displayRemoveFromList(Task task, int totalSize) {
        String response = "";
        response += "This task is deleted to the list\n";
        response += task.toString();
        response += String.format("\nYou now have %d tasks in your list%n", totalSize);
        return response;
    }

    /**
     * Displays what task is marked as done.
     * @param task The task to be marked as done
     * @return The response string
     */
    public String displayDoneTask(Task task) {
        String response = "";
        response += "This task is marked as done\n";
        response += task.toString();
        return response;
    }

    /**
     * Displays what task is marked as not done.
     * @param task The task to be marked as not done
     * @return The response string
     */
    public String displayNotDoneTask(Task task) {
        String response = "";
        response += "This task is marked as not done\n";
        response += task.toString();
        return response;
    }

    /**
     * Displays a specified list of tasks.
     * @param tasks The string representing the specified list of tasks
     * @return The response string
     */
    public String displayTasks(String tasks) {
        return tasks.toString();
    }

    /**
     * Displays the DukeException
     * @param exception The DukeException to be displayed
     * @return The response string
     */
    public String displayException(DukeException exception) {
        return exception.getMessage();
    }
}
