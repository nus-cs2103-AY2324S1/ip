package duke.data;

import java.util.ArrayList;

import duke.data.task.Task;

/**
 * The Ui class handles user input and display messages.
 */
public class Message {
    /** Constructor to initialize Ui */
    public Message() {

    }

    /** Displays welcome message */
    public String showWelcome() {
        String response = "Welcome! I'm Dommi and I'm here to assist you with your task management!";
        response += "\nEnter 'help' for available commands!";
        return response;
    }

    /** Displays goodbye message */
    public String showBye() {
        return "Goodbye! Hope to see you again soon!";
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
        String response = "Nice! I've marked this task as done:\n";
        response += task;
        return response;
    }

    /**
     * Displays task as unmarked.
     *
     * @param task the task that was unmarked.
     */
    public String showUnmarked(Task task) {
        String response = "OK, I've marked this task as not done yet:\n";
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
        String response = "Got it. I've added this task:\n";
        response += task;
        response += "\nNow you have" + noTasks + " tasks in the list.";
        return response;
    }

    /**
     * Displays task as deleted.
     *
     * @param task the task that was deleted
     * @param noTasks number of tasks in arraylist.
     */
    public String showTaskDeleted(Task task, int noTasks) {
        String response = "Noted. I've removed this task:\n";
        response += task;
        response += "\nNow you have " + noTasks + " tasks in the list.";
        return response;
    }

    /**
     * Displays all tasks in taskList
     *
     * @param taskList taskList from Duke.
     */
    public String showTaskList(TaskList taskList) {
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
        return "☹ OOPS!!! Date has to be in yyyy-mm-dd format!";
    }

    /** Displays writing to file error */
    public String showWriteFileError() {
        return "☹ OOPS!!! Error writing to file!";
    }

    /** Displays invalid command error */
    public String showInvalidCommand() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
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
