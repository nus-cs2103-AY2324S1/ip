package anya.ui;

import static anya.messages.Messages.DIVIDER;
import static anya.messages.Messages.LS;
import static anya.messages.Messages.MESSAGE_ERROR_INIT;
import static anya.messages.Messages.MESSAGE_ERROR_LOADING;
import static anya.messages.Messages.MESSAGE_EXIT;
import static anya.messages.Messages.MESSAGE_GREETING;
import static anya.messages.Messages.MESSAGE_SUCCESS_ADD;
import static anya.messages.Messages.MESSAGE_SUCCESS_DELETE;
import static anya.messages.Messages.MESSAGE_SUCCESS_LOADING;
import static anya.messages.Messages.MESSAGE_SUCCESS_MARK;
import static anya.messages.Messages.MESSAGE_SUCCESS_UNMARK;
import static anya.messages.Messages.MESSAGE_TASK_FOUND;
import static anya.messages.Messages.MESSAGE_TASK_LIST;
import static anya.messages.Messages.MESSAGE_TASK_NOT_FOUND;
import static anya.messages.Messages.MESSAGE_TASK_SIZE;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import anya.task.Task;
import anya.task.TaskList;


/**
 * Represents the User Interface (UI) of the Anya application.
 * The UI handles interactions with the user, displaying messages, and receiving user input.
 */
public class Ui {
    private final Scanner in;
    private final PrintStream out;

    /**
     * Constructs a new `Ui` instance using the default input and output streams (System.in and System.out).
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Constructs a new `Ui` instance with the specified input and output streams.
     *
     * @param in  The input stream to read user input from.
     * @param out The output stream to display messages to the user.
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Displays a message to the user, surrounded by a divider for better readability.
     *
     * @param message The message to be shown.
     */
    public void showToUser(String message) {
        out.println(LS + DIVIDER + LS
                + message.replace("\n", LS)
                + LS + DIVIDER + LS);
    }

    /**
     * Returns the welcome message when the application starts.
     *
     * @return Welcome Message.
     */
    public String showGreetingMessage() {
        return MESSAGE_GREETING;
    }

    /**
     * Returns a message to inform the user that the application is exiting.
     *
     * @return Exit Message
     */
    public String showExitMessage() {
        return MESSAGE_EXIT;
    }

    /**
     * Reads a user command from the input stream, trims leading and trailing whitespace,
     * and returns the cleaned command as a string.
     *
     * @return The user-entered command after trimming whitespace.
     */
    public String readUserCommand() {
        return in.nextLine().trim();
    }

    /**
     * Returns a loading error message when there is an issue loading data.
     *
     * @return Loading Error Message.
     */
    public String showLoadingError() {
        return MESSAGE_ERROR_LOADING;
    }

    /**
     * Returns a loading success message when there is an issue loading data.
     *
     * @return Loading Success Message.
     */
    public String showLoadingSuccess() {
        return MESSAGE_SUCCESS_LOADING;
    }

    /**
     * Returns a success message after adding a task to the task list.
     *
     * @param task  The task that was added.
     * @param tasks The task list containing the added task.
     * @return A success message including information about the added task and the updated task list size.
     */
    public String showTaskAddSuccess(Task task, TaskList tasks) {
        return MESSAGE_SUCCESS_ADD + task + LS
                + MESSAGE_TASK_SIZE.replace("tasks.size()", String.valueOf(tasks.size()));
    }

    /**
     * Generates a success message after marking a task as done.
     *
     * @param task The task that was marked as done.
     * @return A success message indicating that the task has been marked as done.
     */
    public String showTaskMarkSuccess(Task task) {
        return MESSAGE_SUCCESS_MARK + task;
    }

    /**
     * Generates a success message after marking a task as not done.
     *
     * @param task The task that was marked as not done.
     * @return A success message indicating that the task has been marked as not done.
     */
    public String showTaskUnmarkSuccess(Task task) {
        return MESSAGE_SUCCESS_UNMARK + task;
    }

    /**
     * Displays a list of tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public String showTaskList(TaskList tasks) {
        StringBuilder list = new StringBuilder(MESSAGE_TASK_LIST);
        for (int i = 0; i < tasks.size(); i++) {
            list.append(String.format((i + 1) + ". " + tasks.get(i) + "\n"));
        }
        return list.toString();
    }

    /**
     * Returns a success message after deleting a task from the task list.
     *
     * @param task  The task that was deleted.
     * @param tasks The task list containing the deleted task.
     * @return A success message including information about the deleted task and the updated task list size.
     */
    public String showTaskDeleteSuccess(Task task, TaskList tasks) {
        return MESSAGE_SUCCESS_DELETE + task + LS
                + MESSAGE_TASK_SIZE.replace("tasks.size()", String.valueOf(tasks.size()));
    }

    /**
     * Generates a message to display a list of tasks found in the task list.
     *
     * @param tasks The task list containing the found tasks.
     * @return A message displaying the found tasks with their indexes in the list.
     */
    public String showTaskFound(TaskList tasks) {
        StringBuilder list = new StringBuilder(MESSAGE_TASK_FOUND);
        for (int i = 0; i < tasks.size(); i++) {
            list.append(String.format((i + 1) + ". " + tasks.get(i) + "\n"));
        }
        return list.toString();
    }

    /**
     * Generates a message to indicate that no tasks were found.
     *
     * @return A message indicating that no tasks matching the search criteria were found.
     */
    public String showTaskNotFound() {
        return MESSAGE_TASK_NOT_FOUND;
    }

    /**
     * Generates an error message during the application initialization.
     *
     * @return Init Error Message.
     */
    public String showInitError() {
        return MESSAGE_ERROR_INIT;
    }

    /**
     * Generates a help message listing available commands and their syntax.
     *
     * @return A String containing a list of available commands and their syntax.
     */
    public String showHelp() {
        StringBuilder helpMessage = new StringBuilder("Available commands and their syntax:\n");
        helpMessage.append("1. bye - Exit the application.\n");
        helpMessage.append("2. list - List all tasks.\n");
        helpMessage.append("3. mark <taskNumber> - Mark a task as done.\n");
        helpMessage.append("4. unmark <taskNumber> - Mark a task as not done.\n");
        helpMessage.append("5. todo <taskDescription> - Add a new todo task.\n");
        helpMessage.append("6. deadline <taskDescription> /by <deadline> - Add a new deadline task.\n");
        helpMessage.append("7. event <taskDescription> /from <startTime> /to <endTime> - Add a new event task.\n");
        helpMessage.append("8. delete <taskNumber> - Delete a task.\n");
        helpMessage.append("9. find <keyword> - Find tasks containing a keyword.\n");
        helpMessage.append("10. help - Show this help message.\n");
        return helpMessage.toString();
    }
}
