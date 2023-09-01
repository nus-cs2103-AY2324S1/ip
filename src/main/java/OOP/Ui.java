package OOP;

import Tasks.Task;

import java.util.Scanner;

public class Ui {
    /** The Scanner object used to read user input */
    Scanner scanner;

    /**
     * Constructs a new ui instance.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Returns the next command from the user as a String.
     *
     * @return String containing the user's next command.
     */
    public String getUserCommand() {
        return scanner.nextLine();
    }
    public void printDivider() {
        System.out.println('\t' + "_________________________________________");
    }

    /**
     * Prints out a simple greeting to the user before any command is processed.
     *
     * @param name The name of the chatbot.
     */
    public void printGreeting(String name) {
        printDivider();
        System.out.println("\tHello! I'm " + name + "!");
        System.out.println("\tWhat can I do for you?");
        printDivider();
    }

    /**
     * Prints out a message summarising the tasks when a new task is added.
     *
     * @param task The task that was added.
     * @param tasks The TaskList instance used by Duke.
     */
    public void printTaskAddedMessage(Task task, TaskList tasks) {
        System.out.println("\t Got it. I've added this task:");
        System.out.println(String.format("\t\t %s", task.toString()));
        System.out.println(String.format("\tNow you have %d tasks in the list.", tasks.getSize()));
    }

    /**
     * Prints out a message to indicate successful deletion of a task.
     *
     * @param task The task that was removed.
     */
    public void printTaskDeletedMessage(Task task) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t" + task.toString());
    }

    /**
     * Prints out a message to indicate successful marking of a task as done.
     *
     * @param task The task that was marked as done.
     */
    public void printTaskMarkedMessage(Task task) {
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" + task.toString());
    }
    /**
     * Prints out a message to indicate successful marking of a task as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void printTaskUnmarkedMessage(Task task) {
        System.out.println("\tOk, I've marked this task as not done yet:");
        System.out.println("\t\t" + task.toString());
    }

    /**
     * Prints out a simple message to show a loading error.
     */
    public void showLoadingError() {
        System.out.println("Loading error...");
    }

    /**
     * Prints out an error message when something goes wrong.
     *
     * @param message The error message to be printed, as a string.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /** Prints a simple error message when user command does not match any available command */
    public void printNotSureMessage() {
        System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Prints out the task in the usual format seen in the CLI.
     *
     * @param id The index of the task to be printed, within the TaskList.
     * @param task The task to be displayed.
     */

    public void printTask(int id, Task task) {
        System.out.println(String.format("\t%d.%s", id + 1, task.toString()));
    }

    /**
     * Prints out a simple farewell message.
     */
    public void bidFarewell() {
        System.out.println("\tBye. Hope to see you again soon!");
    }
}
