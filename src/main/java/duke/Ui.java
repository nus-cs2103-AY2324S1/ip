package duke;

import duke.task.Task;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents the user interface for the Duke application.
 *
 * This class handles printing of user-facing messages, including greetings, errors,
 * and task-related information. Messages are consistently formatted with indentation and horizontal lines.
 */
public class Ui {

    private static final String INDENT = "    ";

    /**
     * Prints the greeting message.
     */
    public void printGreeting() {
        printHorizontalLine();
        printIndented("Hello! I'm Davidson");
        printIndented("What can I do for you?");
        printHorizontalLine();
    }

    /**
     * Prints the exit message.
     */
    public void printExit() {
        printHorizontalLine();
        printIndented("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    /**
     * Prints a horizontal line for UI formatting.
     */
    public void printHorizontalLine() {
        System.out.print(INDENT);
        for (int i = 0; i < 60; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Prints the given message with consistent indentation.
     *
     * @param message The message to be printed.
     */
    public void printIndented(String message) {
        System.out.println(INDENT + message);
    }

    /**
     * Prints the tasks in the user's list.
     *
     * @param tasks The list of tasks to be printed.
     */
    public void printList(List<Task> tasks) {
        printHorizontalLine();
        printIndented("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            printIndented((i + 1) + "." + tasks.get(i));
        }
        printHorizontalLine();
    }

    /**
     * Prints the information about a task that has been added.
     *
     * @param task The task that was added.
     * @param size The current number of tasks in the list.
     */
    public void printTaskAdded(Task task, int size) {
        printHorizontalLine();
        printIndented("Got it. I've added this task:");
        printIndented("  " + task.toFileString());
        printIndented("Now you have " + size + " tasks in the list.");
        printHorizontalLine();
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        printIndented(message);
    }

    /**
     * Prints the tasks that fall on a specific date.
     *
     * @param tasksOnDate The list of tasks on the given date.
     * @param date The date for which tasks are to be displayed.
     */
    public void printTasksOnDate(List<Task> tasksOnDate, LocalDate date) {
        printHorizontalLine();
        if (tasksOnDate.isEmpty()) {
            printIndented("No tasks on " + date);
            return;
        }
        printIndented("Here are the tasks on " + date + ":");
        for (Task task : tasksOnDate) {
            printIndented(task.toFileString());
        }
        printHorizontalLine();
    }

    /**
     * Prints the information about a task that has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void printMarkedAsDone(Task task) {
        printHorizontalLine();
        printIndented("Nice! I've marked this task as done:");
        printIndented("  " + task);
        printHorizontalLine();
    }

    /**
     * Prints the information about a task that has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void printMarkedAsNotDone(Task task) {
        printHorizontalLine();
        printIndented("Okay! I've marked this task as not done:");
        printIndented("  " + task);
        printHorizontalLine();
    }

    /**
     * Prints the information about a task that has been deleted.
     *
     * @param removedTask The task that was removed.
     * @param size The current number of tasks in the list after removal.
     */
    public void printTaskDeleted(Task removedTask, int size) {
        printHorizontalLine();
        printIndented("Noted. I've removed this task:");
        printIndented("  " + removedTask);
        printIndented("Now you have " + size + " tasks in the list.");
        printHorizontalLine();
    }

    public void printFindResults(List<Task> tasks) {
        printHorizontalLine();
        printIndented("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            printIndented((i + 1) + "." + tasks.get(i));
        }
        printHorizontalLine();
    }
}