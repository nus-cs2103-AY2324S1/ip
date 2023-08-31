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
     */
    public void introMessage() {
        System.out.println("Hello! I'm BaekBot.\nIf you're unsure of the commands available, type /help. " +
                "\nWhat can I do for you?");
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
     */
    public void addTaskMessage(Task task, int count) {
        System.out.println("Got it. I've added this task:\n" + task
                + "\nNow you have " + count + " tasks in the list.");
    }

    /**
     * Prints the task that was deleted from the list.
     *
     * @param task Task that was deleted from the list.
     * @param count Total count of tasks in the list after deletion.
     */
    public void deleteTaskMessage(Task task, int count) {
        System.out.println("Noted. I've removed this task:\n"
                + task + "\nNow you have " + count + " tasks in the list.");
    }

    /**
     * Prints message that notifies user does not have any tasks in the list.
     */
    public void emptyTaskMessage() {
        System.out.println("You don't have any tasks right now.");
    }

    /**
     * Prints farewell message when user exits the bot.
     */
    public void byeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints message containing task when user marks a task as done.
     *
     * @param task Task that was marked done.
     */
    public void markDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Prints message containing task when user marks a task as not done.
     *
     * @param task Task that was marked not done.
     */
    public void unmarkDoneMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet: \n" + task);
    }

    /**
     * Prints message when there is an error loading data from the savefile.
     */
    public void loadingErrorMessage() {
        System.out.println("There was an issue loading your savefile!");
    }

    /**
     * Prints message when user inputs a command that the bot does not understand.
     */
    public void noCommandMessage() {
        System.out.println("Sorry, I don't understand what it means :(");
    }

    /**
     * Prints message when user inputs an empty command.
     */
    public void emptyCommandMessage() {
        System.out.println("Don't give me an empty command!");
    }

    /**
     * Prints message containing all commands that bot recognizes.
     */
    public void helpMessage() {
        System.out.println("To view the list of tasks, type list. \nTo add a todo, type todo." +
                "\nTo add a deadline, type deadline with /by.\nTo add a event, type event with /from and /to." +
                "\nTo mark/unmark tasks, type mark/unmark followed by the index." +
                "\nTo delete a task, type delete followed by the index." +
                "\nTo exit, type bye.");
    }

    /**
     * Closes the scanner to stop reading input from user.
     */
    public void closeScanner() {
        scanner.close();
    }
}
