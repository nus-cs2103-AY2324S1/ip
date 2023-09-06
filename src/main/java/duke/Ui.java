package duke;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import duke.task.Task;

/**
 * Represents the user interface for the Duke application.
 *
 * Handles the creation of user-facing messages, including greetings, task-related information, and errors.
 * The UI displays messages with consistent formatting, including indentation and horizontal lines.
 */
public class Ui {
    private static final String INDENT = "    ";
    private Scanner input = new Scanner(System.in);

    /**
     * Read the input from the user.
     *
     * @return string representation of user's input in the CLI
     */
    public String readInput() {
        return this.input.nextLine();
    }


    /**
     * Close the input scanner.
     */
    public void closeInput() {
        this.input.close();
    }


    /**
     * Returns a greeting message.
     *
     * @return A string containing the greeting message.
     */
    public String displayGreeting() {
        return displayHorizontalLine()
                + displayIndented("Hello! I'm Davidson")
                + displayIndented("What can I do for you?")
                + displayHorizontalLine();
    }

    /**
     * Returns an exit message.
     *
     * @return A string containing the exit message.
     */
    public String displayExit() {
        return displayHorizontalLine()
                + displayIndented("Bye. Hope to see you again soon!")
                + displayHorizontalLine();
    }

    /**
     * Returns a horizontal line for UI formatting.
     *
     * @return A string containing the horizontal line.
     */
    public String displayHorizontalLine() {
        StringBuilder line = new StringBuilder(INDENT);
        for (int i = 0; i < 60; i++) {
            line.append("-");
        }
        return line.append("\n").toString();
    }

    /**
     * Returns a message with consistent indentation.
     *
     * @param message The message to be indented.
     * @return An indented string.
     */
    public String displayIndented(String message) {
        return INDENT + message + "\n";
    }

    /**
     * Returns a list of tasks in the user's list.
     *
     * @param tasks The list of tasks.
     * @return A string representing the list of tasks.
     */
    public String displayList(List<Task> tasks) {
        StringBuilder sb = new StringBuilder(displayHorizontalLine());
        sb.append(displayIndented("Here are the tasks in your list:"));
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(displayIndented((i + 1) + "." + tasks.get(i)));
        }
        sb.append(displayHorizontalLine());
        return sb.toString();
    }

    /**
     * Returns a message indicating a task has been added.
     *
     * @param task The task that was added.
     * @param size The current number of tasks in the list.
     * @return A string containing information about the task addition.
     */
    public String displayTaskAdded(Task task, int size) {
        return displayHorizontalLine()
                + displayIndented("Got it. I've added this task:")
                + displayIndented("  " + task.toFileString())
                + displayIndented("Now you have " + size + " tasks in the list.")
                + displayHorizontalLine();
    }

    /**
     * Returns a formatted message for a given {@code DukeException}.
     *
     * This method retrieves the message from the provided exception and formats it
     * for consistent display using the internal indentation method.
     *
     * @param e The {@code DukeException} whose message is to be displayed.
     * @return A string containing the indented exception message.
     */
    public String displayException(DukeException e) {
        return displayIndented(e.getMessage());
    }

    /**
     * Returns tasks for a specific date.
     *
     * @param tasksOnDate The list of tasks for the given date.
     * @param date The date.
     * @return A string listing the tasks on the specified date.
     */
    public String displayTasksOnDate(List<Task> tasksOnDate, LocalDate date) {
        StringBuilder sb = new StringBuilder(displayHorizontalLine());
        if (tasksOnDate.isEmpty()) {
            sb.append(displayIndented("No tasks on " + date));
        } else {
            sb.append(displayIndented("Here are the tasks on " + date + ":"));
            for (Task task : tasksOnDate) {
                sb.append(displayIndented(task.toFileString()));
            }
        }
        sb.append(displayHorizontalLine());
        return sb.toString();
    }

    /**
     * Returns a message indicating a task has been marked as done.
     *
     * @param task The task that was marked as done.
     * @return A string containing the message.
     */
    public String displayMarkedAsDone(Task task) {
        return displayHorizontalLine()
                + displayIndented("Nice! I've marked this task as done:")
                + displayIndented("  " + task)
                + displayHorizontalLine();
    }

    /**
     * Returns a message indicating a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     * @return A string containing the message.
     */
    public String displayMarkedAsNotDone(Task task) {
        return displayHorizontalLine()
                + displayIndented("Okay! I've marked this task as not done:")
                + displayIndented("  " + task)
                + displayHorizontalLine();
    }

    /**
     * Returns a message indicating a task has been deleted.
     *
     * @param removedTask The task that was removed.
     * @param size The current number of tasks in the list after removal.
     * @return A string containing the deletion message.
     */
    public String displayTaskDeleted(Task removedTask, int size) {
        return displayHorizontalLine()
                + displayIndented("Noted. I've removed this task:")
                + displayIndented("  " + removedTask)
                + displayIndented("Now you have " + size + " tasks in the list.")
                + displayHorizontalLine();
    }

    /**
     * Returns the tasks that match a certain criteria.
     *
     * @param tasks A list of tasks that match the criteria.
     * @return A string listing the matching tasks.
     */
    public String displayFindResults(List<Task> tasks) {
        StringBuilder sb = new StringBuilder(displayHorizontalLine());
        sb.append(displayIndented("Here are the matching tasks in your list:"));
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(displayIndented((i + 1) + "." + tasks.get(i)));
        }
        sb.append(displayHorizontalLine());
        return sb.toString();
    }

    public String displayDukeException(DukeException e) {
        return String.format("%s\n", e);
    }
}
