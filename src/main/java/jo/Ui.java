package jo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Platform;
import jo.task.Task;

/**
 * Responsible for handling user interactions and displaying messages in the `Jo` application.
 * It provides methods for reading user input, showing welcome messages and displaying errors.
 */
public class Ui {
    private final Scanner scanner;
    private final ArrayList<String> messageBuffer = new ArrayList<>();

    /**
     * Constructs a new `Ui` object with a `Scanner` for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a command entered by the user.
     *
     * @return The user's input command as a string.
     */
    public String readCommand() {
        System.out.print("Enter a command: ");
        return this.scanner.nextLine();
    }

    /**
     * Displays a welcome message when the application starts.
     */
    public void showWelcome() {
        String message = "Hello! I'm Jo.\n What can I do for you?";
        bufferMessage(message);
    }

    /**
     * Displays a farewell message when the application is about to exit.
     */
    public void sayBye() {
        String message = "Bye. Hope to see you again soon!";
        bufferMessage(message);
        this.scanner.close();
        Platform.exit();
    }

    /**
     * Displays an error message to the user.
     *
     * @param input The error message to display.
     */
    public void showError(String input) {
        String message = "OOPS!!! " + input;
        bufferMessage(message);
    }

    /**
     * Displays a message indicating the result of marking a task as done or undone.
     *
     * @param task   The task that was marked.
     * @param isDone `true` if the task was marked as done, `false` if marked as undone.
     */
    public void markResult(Task task, boolean isDone) {
        String message = "";
        if (isDone) {
            message = "Nice! I've marked this task as done:\n";
        } else {
            message = "OK, I've marked this task as not done yet:\n";
        }
        bufferMessage(message + "\t" + task.toString());
    }

    /**
     * Displays a message indicating the result of adding or removing a task from the list.
     *
     * @param task      The task that was added or removed.
     * @param taskList  The updated task list.
     * @param isAdd     `true` if a task was added, `false` if a task was removed.
     */
    public void modifyListResult(Task task, TaskList taskList, boolean isAdd) {
        String message = "";
        if (isAdd) {
            message = "Got it. I've added this task:\n";
        } else {
            message = "Noted. I've removed this task:\n";
        }
        message += "\t" + task.toString();
        message += String.format("Now you have %d tasks in the list.%n", taskList.getSize());
        bufferMessage(message);
    }

    /**
     * Displays a list of tasks that match a specified deadline date.
     *
     * @param deadline     The deadline date for which tasks are displayed.
     * @param resultList   The list of tasks that match the deadline.
     */
    public void findDeadlineResult(LocalDate deadline, ArrayList<Task> resultList) {
        String message = "Here are the tasks that are due on "
                + deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ":\n";
        if (!resultList.isEmpty()) {
            for (Task t : resultList) {
                message += "\t" + t;
            }
        } else {
            message += "\tYay, you have no task due on this day!";
        }
        bufferMessage(message);
    }

    /**
     * Displays the results of a task search to the user.
     *
     * @param resultList An ArrayList of tasks that match the search criteria.
     */
    public void findKeywordResult(ArrayList<Task> resultList) {
        String message = "";
        if (!resultList.isEmpty()) {
            message += "Here are the matching task(s) in your list: \n";
            for (Task t : resultList) {
                message += "\t" + t;
            }
        } else {
            message += "No matching tasks found.";
        }
        bufferMessage(message);
    }

    /**
     * Displays the list of tasks in the task list.
     *
     * @param taskList The task list to display.
     */
    public void printList(TaskList taskList) {
        String message = "Here are the tasks in your list:\n";

        for (int i = 0; i < taskList.getSize(); i++) {
            Task t = taskList.getTask(i);
            message += "\t" + (i + 1) + ". " + t.toString();
        }

        bufferMessage(message);
    }

    /**
     * Adds the message to a buffer
     * @param message A string message
     */
    private void bufferMessage(String message) {
        System.out.println(message);
        messageBuffer.add(message);
    }

    /**
     * Returns the messages stored in the buffer and clears the buffer
     * @return Messages in the buffer
     */
    public String flushBuffer() {
        String combinedMessage = String.join("\n", messageBuffer);
        messageBuffer.clear();
        return combinedMessage;
    }
}
