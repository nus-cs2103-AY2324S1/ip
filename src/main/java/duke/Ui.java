package duke;

import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * Represents the user interface of the Duke chat-bot.
 * Handles the input and output of the chat-bot.
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________\n";
    private final Scanner scanner;

    /**
     * Creates a Ui object.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the input from the user using the default System.in Scanner.
     *
     * @return The input from the user.
     */
    public String readInput() {
        return this.scanner.nextLine();
    }

    private void outputMessage(String message) {
        System.out.println(HORIZONTAL_LINE + message + HORIZONTAL_LINE);
    }

    /**
     * Shows the welcome message when the Duke chat-bot starts.
     */
    public void showWelcomeMessage() {
        outputMessage(" Hello! I'm Pixel\n What can I do for you?\n");
    }

    /**
     * Shows the message when the Duke chat-bot loads the tasks from the file.
     */
    public void showLoadedTasksMessage() {
        outputMessage(" Loaded tasks from database!\n");
    }

    /**
     * Shows the goodbye message when the Duke chat-bot exits.
     * Also closes the default System.in Scanner.
     */
    public void closeAndGoodbyeMessage() {
        outputMessage(" Bye. Hope to see you again soon!\n");
        scanner.close();
    }

    /**
     * Shows the error message when the Duke chat-bot encounters an error, specifically a DukeException.
     *
     * @param e The DukeException.
     */
    public void showErrorMessage(DukeException e) {
        outputMessage(e.getMessage());
    }

    /**
     * Shows the list of tasks when the Duke chat-bot lists the tasks.
     *
     * @param tasks The list of tasks to be shown.
     */
    public void listTasks(TaskList tasks) {
        if (tasks.getSize() == 0) {
            outputMessage(" There are no tasks in your list!\n");
        } else {
            StringBuilder tasksString = new StringBuilder();
            for (int i = 0; i < tasks.getSize(); i++) {
                tasksString.append(String.format("  %d. %s\n", i + 1, tasks.getTask(i).toString()));
            }
            outputMessage(String.format(" Here are the tasks in your list:\n%s", tasksString));
        }
    }

    /**
     * Shows the message when the Duke chat-bot marks a task as done.
     *
     * @param task The task to be marked as done.
     */
    public void showDoneMessage(Task task) {
        outputMessage(String.format(" Nice! I've marked this task as done:\n  %s\n", task));
    }

    /**
     * Shows the message when the Duke chat-bot marks a task as not done yet.
     *
     * @param task The task to be marked as not done yet.
     */
    public void showUndoneMessage(Task task) {
        outputMessage(String.format(" OK, I've marked this task as not done yet:\n  %s\n", task));
    }

    /**
     * Shows the message when the Duke chat-bot adds a task.
     *
     * @param taskSize The size of the list of tasks after adding.
     * @param task     The task to be added.
     */
    public void showAddMessage(int taskSize, Task task) {
        outputMessage(String.format(
                " Got it. I've added this task:\n  %s\n Now you have %d task%s in the list.\n",
                task, taskSize, taskSize == 1 ? "" : "s"));
    }

    /**
     * Shows the message when the Duke chat-bot deletes a task.
     *
     * @param taskSize The size of the list of tasks after deletion.
     * @param task     The task to be deleted.
     */
    public void showDeleteMessage(int taskSize, Task task) {
        outputMessage(String.format(
                " Noted. I've removed this task:\n  %s\n Now you have %d task%s in the list.\n",
                task, taskSize, taskSize == 1 ? "" : "s"));
    }

    /**
     * Shows the message when the Duke chat-bot finds matching tasks.
     */
    public void showNoMatchingTasksMessage() {
        outputMessage(" There are no matching tasks in your list!\n");
    }

    /**
     * Shows the message when the Duke chat-bot finds matching tasks.
     *
     * @param matchingTasks The list of matching tasks.
     */
    public void showMatchingTasksMessage(TaskList matchingTasks) {
        StringBuilder tasksString = new StringBuilder();
        for (int i = 0; i < matchingTasks.getSize(); i++) {
            tasksString.append(String.format("  %d. %s\n", i + 1, matchingTasks.getTask(i).toString()));
        }
        outputMessage(String.format(" Here are the matching tasks in your list:\n%s", tasksString));
    }
}
