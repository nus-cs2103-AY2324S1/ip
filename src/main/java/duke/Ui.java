package duke;

import java.util.Scanner;

import duke.task.Task;

/**
 * The Ui class handles user interface interactions and messages.
 */
public class Ui {

    /**
     * Displays an error message for loading the storage file.
     */
    public void showLoadingError() {
        System.out.println("Error loading storage file");
    }

    /**
     * Displays the welcome message when the program starts.
     */
    public void showWelcome() {
        String logo = "Hello! I am Bob\n"
                + "What can I do for you?\n";
        System.out.println(logo);
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
     */
    public void showLine() {
        System.out.println("_______________________________");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Displays a goodbye message when the program ends.
     */
    public void showBye() {
        String bye = "Bye! Hope to see you again soon!\n";
        System.out.println(bye);
    }

    /**
     * Displays a given string message.
     *
     * @param str The string message to be displayed.
     */
    public void display(String str) {
        System.out.println(str);
    }

    /**
     * Prints the string representation of a DukeList.
     *
     * @param list The DukeList to be printed.
     */
    public void printList(DukeList list) {
        System.out.println(list.toString());
    }

    /**
     * Displays a message indicating a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void markDone(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + "\t" + task.toString());
    }

    /**
     * Displays a message indicating a task has been unmarked (not done).
     *
     * @param task The task that was unmarked.
     */
    public void unmark(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n" + "\t" + task.toString());
    }

    /**
     * Displays a message indicating a task has been added to the list.
     *
     * @param task The task that was added.
     * @param size The updated size of the task list.
     */
    public void addToList(Task task, int size) {
        System.out.println("Got it. I've added this task:\n\t" + task.toString()
                + "\nNow you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message indicating a task has been deleted from the list.
     *
     * @param task The task that was deleted.
     * @param size The updated size of the task list.
     */
    public void delete(Task task, int size) {
        System.out.println("Noted. I've removed this task:\n\t"
                + task.toString() + "\n Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message indicating that the list of matching tasks is about to be shown.
     */
    public void matchingTasks() {
        System.out.println("Here are the matching tasks in your list:");
    }

}
