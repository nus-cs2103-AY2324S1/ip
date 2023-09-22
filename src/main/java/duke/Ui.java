package duke;

import duke.task.Task;

import java.util.Scanner;

/**
 * Ui is a class that handles all UI interactions with the user, through print
 * statements.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui object with the scanner initialized to scan for
     * user input.
     */
    public Ui() {

        scanner = new Scanner(System.in);
    }

    /**
     * Prints the introduction message when the bot is first launched.
     *
     * @return A String message.
     */
    public String introMessage() {
        return "Hello! I'm BaekBot."
                + "\nWhat can I do for you?";
    }

    /**
     * Returns the user input after reading.
     *
     * @return The user's input in type String.
     */
    public String getInput() {
        return scanner.nextLine();
    }

    /**
     * Prints the task that was added to the list.
     *
     * @param task Task that was added to the list.
     * @param count Total count of tasks in the list after addition.
     * @return A String message.
     */
    public String addTaskMessage(Task task, int count) {
        return "Got it. I've added this task:\n" + task
                + "\nNow you have " + count + " tasks in the list.";
    }

    /**
     * Prints the task that was deleted from the list.
     *
     * @param task Task that was deleted from the list.
     * @param count Total count of tasks in the list after deletion.
     * @return A String message.
     */
    public String deleteTaskMessage(Task task, int count) {
        return "Noted. I've removed this task:\n"
                + task + "\nNow you have " + count + " tasks in the list.";
    }

    /**
     * Prints message that notifies user does not have any tasks in the list.
     *
     * @return A String message.
     */
    public String emptyTaskMessage() {
        return "You don't have any tasks right now.";
    }

    /**
     * Prints farewell message when user exits the bot.
     *
     * @return A String message.
     */
    public String byeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints message containing task when user marks a task as done.
     *
     * @param task Task that was marked done.
     * @return A String message.
     */
    public String markDoneMessage(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Prints message containing task when user marks a task as not done.
     *
     * @param task Task that was marked not done.
     * @return A String message.
     */
    public String unmarkDoneMessage(Task task) {
        return "OK, I've marked this task as not done yet: \n" + task;
    }

    /**
     * Prints message when there is an error loading data from the savefile.
     *
     * @return A String message.
     */
    public String loadingErrorMessage() {
        return "There was an issue loading your savefile!";
    }

    /**
     * Prints message when user inputs a command that the bot does not understand.
     *
     * @return A String message.
     */
    public String noCommandMessage() {
        return "Sorry, I don't understand what it means :(";
    }

    /**
     * Prints message when user inputs an empty command.
     *
     * @return A String message.
     */
    public String emptyCommandMessage() {
        return "Don't give me an empty command!";
    }

    /**
     * Prints message to inform users about matching tasks
     * that will be displayed after.
     *
     * @return A String message.
     */
    public String displayMatchMessage() {
        return "Here are the matching tasks!";
    }

    /**
     * Prints a string representation of a Task in an ordered list.
     *
     * @param count Integer representing the number of task in ordered list.
     * @param task Task to be printed.
     * @return A String message.
     */
    public String displayTaskMessage(int count, Task task) {
        return count + ". " + task;
    }

    /**
     * Prints message when user has no matching task to find based on input.
     *
     * @return A String message.
     */
    public String noMatchMessage() {
        return "There are no matching tasks!";
    }

    /**
     * Closes the scanner to stop reading input from user.
     */
    public void closeScanner() {
        scanner.close();
    }
}
