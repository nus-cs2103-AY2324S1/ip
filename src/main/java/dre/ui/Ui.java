package dre.ui;

import java.util.Scanner;
import dre.task.TaskList;
import dre.exception.DreException;
import dre.task.Task;

/**
 * Represents the User Interface responsible for interactions between the user and the application.
 */
public class Ui {

    /**
     * Displays an error message indicating issues with loading tasks from storage.
     *
     * @return loading error message
     */
    public String generateLoadingErrorString() {
        return "Error reading tasks from file.";
    }

    /**
     * Displays a specified error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public String generateErrorString(String message) {
        return message;
    }

    /**
     * Displays the welcome message to the user upon starting the application.
     *
     * @return welcome message
     */
    public static String showWelcome() {
        return "Hello! I'm Dre\n" +
                "What can I do for you?";
    }

    /**
     * Displays the goodbye message to the user upon exiting the application.
     *
     * @return String A goodbye message.
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Generates a representation of the tasks present in the user's task list.
     *
     * @param tasks The task list to be displayed.
     * @return String representation of the tasks.
     * @throws DreException If there is an error while processing the task list.
     */
    public String generateTasksString(TaskList tasks) throws DreException {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 1; i <= tasks.size(); i++) {
            sb.append(i).append(". ").append(tasks.getTask(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Generates a message indicating a task has been unmarked.
     *
     * @param task The task that was unmarked.
     * @return String representation of the unmarked task message.
     */
    public String generateUnmarkedTaskString(Task task) {
        return "Ok! I've marked this task as undone:\n" + task;
    }

    /**
     * Generates a message indicating a task has been deleted.
     *
     * @param task The task that was deleted.
     * @return String representation of the deleted task message.
     */
    public String generateDeletedTaskString(Task task) {
        return "Task deleted:\n" + task;
    }

    /**
     * Generates a message indicating a task has been added.
     *
     * @param task The task that was added.
     * @param totalTasks The total number of tasks in the task list after addition.
     * @return String representation of the added task message.
     */
    public String generateAddedTaskString(Task task, int totalTasks) {
        return "Got it. I've added this task:\n" + task + "\nNow you have " + totalTasks + " tasks in the list.";
    }

    /**
     * Generates a message indicating a task has been marked as done.
     *
     * @param task The task that was marked as done.
     * @return String representation of the marked task message.
     */
    public String generateMarkedTaskString(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Generates a message indicating the tasks that match the search.
     *
     * @param tasks The tasks that match the search description.
     * @return String representation of the tasks that match the search.
     * @throws DreException If there is an error while processing the task list.
     */
    public String generateFoundTasksString(TaskList tasks) throws DreException {
        if (tasks.size() < 1) {
            return "Sorry, no tasks match that description.";
        }
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 1; i <= tasks.size(); i++) {
            sb.append(i).append(". ").append(tasks.getTask(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Generates a message displaying the newly edited task.
     *
     * @param task The edited task.
     * @return String Representation of the edited task.
     */
    public String generateEditSuccessMessage(Task task) {
        return "The following task has been successfully updated:\n" + task;
    }

    /**
     * Reads the user's command input.
     *
     * @return The user's input as a string.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
