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
    public String showWelcome() {
        showLine();
        String message = "Hello! I'm " + CHATBOT_NAME + "\nWhat can I do for you?";
        String output = showMessage(message);
        showLine();
        return output;
    }

    /**
     * Shows the goodbye message.
     */
    public String showGoodbye() {
        String message = "Bye. Hope to see you again soon!";
        return showMessage(message);
    }

    /**
     * Shows the list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public String showTaskList(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(showInputWithIndentation((i + 1) + "." + tasks.get(i)));
        }
        return sb.toString();
    }

    /**
     * Shows the list of filtered tasks.
     *
     * @param tasks The list of filtered tasks.
     */
    public String showFilteredTaskList(TaskList tasks) {
        String message = "Here are the matching tasks in your list:";
        return showMessage(message) + showTaskList(tasks);
    }

    /**
     * Shows the success message after adding a task.
     *
     * @param task          The task that was added.
     * @param numberOfTasks The number of tasks in the list.
     */
    public String showAddTask(Task task, int numberOfTasks) {
        String message = "Got it. I've added this task:\n  " + task + "\nNow you have " + numberOfTasks
            + (numberOfTasks == 1 ? " task" : " tasks") + " in the list.";
        return showMessage(message);
    }

    /**
     * Shows the success message after deleting a task.
     *
     * @param task          The task that was deleted.
     * @param numberOfTasks The number of tasks in the list.
     */
    public String showDeleteTask(Task task, int numberOfTasks) {
        String message = "Noted. I've removed this task:\n  " + task + "\nNow you have " + numberOfTasks
            + (numberOfTasks == 1 ? " task" : " tasks") + " in the list.";
        return showMessage(message);
    }

    /**
     * Shows the success message after marking a task as done.
     *
     * @param task The task that was marked as done.
     */
    public String showMarkTaskAsDone(Task task) {
        String message = "Nice! I've marked this task as done:\n  " + task;
        return showMessage(message);
    }

    /**
     * Shows the success message after marking a task as not done.
     *
     * @param task The task that was marked as not done.
     */
    public String showUnmarkTaskAsDone(Task task) {
        String message = "OK, I've marked this task as not done yet:\n  " + task;
        return showMessage(message);
    }

    /**
     * Shows a horizontal line to separate the chatbot's response from the user's input.
     *
     * @return The horizontal line.
     */
    public String showLine() {
        return showInputWithIndentation(HORIZONTAL_LINE);
    }

    /**
     * Shows the given input with indentation to indicate that it is the chatbot's response.
     *
     * @param input The input to be shown.
     * @return The input with indentation.
     */
    private String showInputWithIndentation(String input) {
        String output = "    " + input + "\n";
        System.out.print(output);
        return output;
    }

    /**
     * Shows the given input with proper indentation.
     *
     * @param input The input to be shown.
     */
    private String showMessage(String input) {
        String[] lines = input.split("\n");
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            sb.append(showInputWithIndentation(line));
        }
        return sb.toString();
    }

    /**
     * Shows the given error message.
     *
     * @param input The error message to be shown.
     * @return The error message.
     */
    public String showError(String input) {
        return showMessage(input);
    }

    /**
     * Shows the error message when there are problems loading the task list from the file.
     *
     * @return The error message.
     */
    public String showLoadingError() {
        return showError("OOPS!!! I have problems loading your tasks.");
    }
}
