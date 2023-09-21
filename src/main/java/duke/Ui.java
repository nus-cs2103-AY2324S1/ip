package duke;

import java.util.Scanner;

import duke.task.Task;

/**
 * The Ui class handles user interface interactions and messages.
 */
public class Ui {

    /**
     * Displays the welcome message when the program starts.
     *
     * @return The welcome message.
     */
    public String showWelcome() {
        return "Hello! I am Bob\n"
                + "What can I do for you?\n";
    }

    /**
     * Reads a command entered by the user.
     *
     * @return The user's input command.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Displays a horizontal line as a separator.
     *
     * @return The horizontal line separator.
     */
    public String showLine() {
        return "_______________________________";
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to be displayed.
     * @return The formatted error message.
     */
    public String showError(String message) {
        return "Error: " + message;
    }

    /**
     * Displays a goodbye message when the program ends.
     *
     * @return The goodbye message.
     */
    public String showBye() {
        return "Bye! Hope to see you again soon!\n";
    }

    /**
     * Displays a given string message.
     *
     * @param str The string message to be displayed.
     * @return The formatted string message.
     */
    public String display(String str) {
        return str;
    }

    /**
     * Prints the string representation of a DukeList.
     *
     * @param list The DukeList to be printed.
     * @return The string representation of the DukeList.
     */
    public String printList(DukeList list) {
        return list.toString();
    }

    /**
     * Displays a message indicating a task has been marked as done.
     *
     * @param task The task that was marked as done.
     * @return The message indicating the task has been marked as done.
     */
    public String markDone(Task task) {
        return "Nice! I've marked this task as done:\n" + "\t" + task.toString();
    }

    /**
     * Displays a message indicating a task has been unmarked (not done).
     *
     * @param task The task that was unmarked.
     * @return The message indicating the task has been unmarked.
     */
    public String unmark(Task task) {
        return "OK, I've marked this task as not done yet:\n" + "\t" + task.toString();
    }

    /**
     * Displays a message indicating a task has been added to the list.
     *
     * @param task The task that was added.
     * @param size The updated size of the task list.
     * @return The message indicating the task has been added.
     */
    public String addToList(Task task, int size) {
        return "Got it. I've added this task:\n\t" + task.toString()
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Displays a message indicating a task has been deleted from the list.
     *
     * @param task The task that was deleted.
     * @param size The updated size of the task list.
     * @return The message indicating the task has been deleted.
     */
    public String delete(Task task, int size) {
        return "Noted. I've removed this task:\n\t"
                + task.toString() + "\n Now you have " + size + " tasks in the list.";
    }

    /**
     * Displays a message indicating that the list of matching tasks is about to be shown.
     *
     * @return The message indicating the list of matching tasks is about to be shown.
     */
    public String matchingTasks() {
        return "Here are the matching tasks in your list:";
    }

    public String tag(Task task) {
        return "Noted. I've tagged this task:\n\t" + task.toString();
    }
}
