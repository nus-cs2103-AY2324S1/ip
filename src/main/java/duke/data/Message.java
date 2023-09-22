package duke.data;

import java.util.ArrayList;

import duke.data.exception.DukeException;
import duke.data.task.Task;

/**
 * The Message class handles user input and display messages.
 * It is used to generate phrases used by Dommi for communication.
 * I Used ChatGPT for the generation of phrases
*/
public class Message {
    /**
     * Constructor to initialize the Message class.
     */
    public Message() {

    }

    /**
     * Displays the welcome message when Dommi is started.
     *
     * @return The welcome message.
     */
    public String showWelcome() {
        return "Greetings! I'm Dommi, your trusty taskmaster, "
                + "here to make your task management journey a breeze! \uD83D\uDE80";
    }

    /**
     * Displays the goodbye message when exiting Dommi.
     *
     * @return The goodbye message.
     */
    public String showBye() {
        return "Adieu! Anticipating your triumphant return!";
    }

    /**
     * Displays an error message provided by the program.
     *
     * @param error The error message being thrown.
     * @return The error message.
     */
    public String showError(String error) {
        return error;
    }

    /**
     * Displays a task as marked.
     *
     * @param task The task that was marked.
     * @return A message indicating that the task has been marked as done.
     */
    public String showMarked(Task task) {
        String response = "Fantastic! Task conquered, marked as done:\n";
        response += task;
        return response;
    }

    /**
     * Displays a task as unmarked.
     *
     * @param task The task that was unmarked.
     * @return A message indicating that the task has been reverted to 'Not done'.
     */
    public String showUnmarked(Task task) {
        String response = "Alrighty then! Task status reverted to 'Not done' for now:\n";
        response += task;
        return response;
    }

    /**
     * Displays a task as added.
     *
     * @param task     The task that was added.
     * @param noTasks  The number of tasks in the list.
     * @return A message indicating that the task has been added to the list.
     */
    public String showTaskAdded(Task task, int noTasks) {
        String response = "Message received! Task added to the list:\n";
        response += task;
        response += "\nNow you have " + noTasks + " tasks in the list.";
        return response;
    }

    /**
     * Displays a task as deleted.
     *
     * @param task     The task that was deleted.
     * @param noTasks  The number of tasks in the list.
     * @return A message indicating that the task has been deleted.
     */
    public String showTaskDeleted(Task task, int noTasks) {
        String response = "Copy that! Task successfully erased:\n";
        response += task;
        response += "\nNow you have " + noTasks + " tasks in the list.";
        return response;
    }

    /**
     * Displays all tasks in the given task list.
     *
     * @param taskList The task list from Duke.
     * @return A formatted string displaying the tasks.
     * @throws DukeException If unable to retrieve task.
     */
    public String showTaskList(TaskList taskList) throws DukeException {
        String response = "";
        if (!taskList.hasTasks()) {
            response = "No tasks have been created.";
        } else {
            for (int i = 0; i < taskList.countTasks(); i++) {
                response += (i + 1) + "." + taskList.getTask(i) + "\n";
            }
        }
        return response;
    }

    /**
     * Displays an error message for an invalid date format.
     *
     * @return The error message for an invalid date format.
     */
    public String showInvalidFormat() {
        return "Oops! Our date needs to be in the sleek yyyy-mm-dd format!";
    }

    /**
     * Displays an error message for an issue with writing to a file.
     *
     * @return The error message for file-writing issues.
     */
    public String showWriteFileError() {
        return "Oops! A gremlin's been tampering with our file-writing equipment!";
    }

    /**
     * Displays an error message for an invalid command.
     *
     * @return The error message for an invalid command.
     */
    public String showInvalidCommand() {
        return " Oops! It seems like an input I can't quite decipher. Please try again!";
    }

    /**
     * Displays a list of tasks as search results.
     *
     * @param results The list of tasks that match the search query.
     * @return A formatted string displaying the search results.
     */
    public String showSearchResults(ArrayList<Task> results) {
        String response = "";
        if (results.isEmpty()) {
            response = "No tasks found.";
        } else {
            for (int i = 0; i < results.size(); i++) {
                response += (i + 1) + "." + results.get(i) + "\n";
            }
        }
        return response;
    }
}
