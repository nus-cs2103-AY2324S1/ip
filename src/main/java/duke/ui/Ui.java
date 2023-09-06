package duke.ui;

import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the user interface of the application.
 * Contains methods to handle input and output.
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String CHATBOT_NAME = "Duke";

    private final Scanner sc; // The scanner to read user input.

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads the next line of user input.
     *
     * @return The next line of user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Closes the scanner.
     */
    public void close() {
        sc.close();
    }

    /**
     * Shows the welcome message.
     */
    public void showWelcome() {
        showLine();
        String message = "Hello! I'm " + CHATBOT_NAME + "\nWhat can I do for you?";
        showMessage(message);
        showLine();
    }

    /**
     * Shows the goodbye message.
     */
    public void showGoodbye() {
        String message = "Bye. Hope to see you again soon!";
        showMessage(message);
    }

    /**
     * Shows the list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public void showTaskList(TaskList tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            showInputWithIndentation((i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Shows the list of filtered tasks.
     *
     * @param tasks The list of filtered tasks.
     */
    public void showFilteredTaskList(TaskList tasks) {
        String message = "Here are the matching tasks in your list:";
        showMessage(message);
        showTaskList(tasks);
    }

    /**
     * Shows the success message after adding a task.
     *
     * @param task          The task that was added.
     * @param numberOfTasks The number of tasks in the list.
     */
    public void showAddTask(Task task, int numberOfTasks) {
        String message = "Got it. I've added this task:\n  " + task + "\nNow you have " + numberOfTasks
            + (numberOfTasks == 1 ? " task" : " tasks") + " in the list.";
        showMessage(message);
    }

    /**
     * Shows the success message after deleting a task.
     *
     * @param task          The task that was deleted.
     * @param numberOfTasks The number of tasks in the list.
     */
    public void showDeleteTask(Task task, int numberOfTasks) {
        String message = "Noted. I've removed this task:\n  " + task + "\nNow you have " + numberOfTasks
            + (numberOfTasks == 1 ? " task" : " tasks") + " in the list.";
        showMessage(message);
    }

    /**
     * Shows the success message after marking a task as done.
     *
     * @param task The task that was marked as done.
     */
    public void showMarkTaskAsDone(Task task) {
        String message = "Nice! I've marked this task as done:\n  " + task;
        showMessage(message);
    }

    /**
     * Shows the success message after marking a task as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void showUnmarkTaskAsDone(Task task) {
        String message = "OK, I've marked this task as not done yet:\n  " + task;
        showMessage(message);
    }

    /**
     * Shows a horizontal line to separate the chatbot's response from the user's input.
     */
    public void showLine() {
        showInputWithIndentation(HORIZONTAL_LINE);
    }

    /**
     * Shows the given input with indentation to indicate that it is the chatbot's response.
     *
     * @param input The input to be shown.
     */
    private void showInputWithIndentation(String input) {
        System.out.println("    " + input);
    }

    /**
     * Shows the given input with proper indentation.
     *
     * @param input The input to be shown.
     */
    private void showMessage(String input) {
        String[] lines = input.split("\n");
        for (String line : lines) {
            showInputWithIndentation(line);
        }
    }

    /**
     * Shows the given error message.
     *
     * @param input The error message to be shown.
     */
    public void showError(String input) {
        showMessage(input);
    }

    /**
     * Shows the error message when there are problems loading the task list from the file.
     */
    public void showLoadingError() {
        showError("OOPS!!! I have problems loading your tasks.");
    }
}
