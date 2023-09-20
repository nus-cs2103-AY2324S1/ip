package duke.data;

import java.util.ArrayList;

import duke.data.exception.DukeException;
import duke.data.task.Task;

/**
 * The Ui class handles user input and display messages.
 * Used ChatGPT for the generation of phrases used by Duke
 */
public class Message {
    /** Constructor to initialize Ui */
    public Message() {

    }

    /** Displays welcome message */
    public String showWelcome() {
        return "Greetings! I'm Dommi, your trusty taskmaster, "
                + "here to make your task management journey a breeze! \uD83D\uDE80";
    }

    /** Displays goodbye message */
    public String showBye() {
        return "Adieu! Anticipating your triumphant return!";
    }

    /** Displays loading error */
    public String showLoadingError() {
        return ".txt file not found! Creating .txt file ...";
    }

    /**
     * Displays error provided by the program.
     *
     * @param error the error message being thrown.
     */
    public String showError(String error) {
        return error;
    }

    /**
     * Displays task as marked.
     *
     * @param task the task that was marked.
     */
    public String showMarked(Task task) {
        String response = "Fantastic! Task conquered, marked as done:\n";
        response += task;
        return response;
    }

    /**
     * Displays task as unmarked.
     *
     * @param task the task that was unmarked.
     */
    public String showUnmarked(Task task) {
        String response = "Alrighty then! Task status reverted to 'Not done' for now:\n";
        response += task;
        return response;
    }

    /**
     * Displays task as added.
     *
     * @param task the task that was added
     * @param noTasks number of tasks in arraylist.
     */
    public String showTaskAdded(Task task, int noTasks) {
        String response = "Message received! Task added to the list:\n";
        response += task;
        response += "\nNow you have " + noTasks + " tasks in the list.";
        return response;
    }

    /**
     * Displays task as deleted.
     *
     * @param task the task that was deleted
     * @param noTasks number of tasks in arraylist.
     */
    public String showTaskDeleted(Task task, int noTasks) {
        String response = "Copy that! Task successfully erased:\n";
        response += task;
        response += "\nNow you have " + noTasks + " tasks in the list.";
        return response;
    }

    /**
     * Displays all tasks in taskList
     *
     * @param taskList taskList from Duke.
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

    /** Displays invalid format error */
    public String showInvalidFormat() {
        return "Oops! Our date needs to be in the sleek yyyy-mm-dd format!";
    }

    /** Displays writing to file error */
    public String showWriteFileError() {
        return "Oops! A gremlin's been tampering with our file-writing equipment!";
    }

    /** Displays invalid command error */
    public String showInvalidCommand() {
        return " Oops! It seems like an input I can't quite decipher. Please try again!";
    }

    /** Displays all the tasks in the array list in the parameter */
    public String showSearchResults(ArrayList<Task> results) {
        String response = "";
        for (int i = 0; i < results.size(); i++) {
            response += (i + 1) + "." + results.get(i) + "\n";
        }
        return response;
    }
}
