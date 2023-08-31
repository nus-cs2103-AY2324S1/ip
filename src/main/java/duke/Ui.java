package duke;

import java.util.Scanner;

/**
 * Represents the user interface of the Duke application.
 * This class handles all interactions with the user.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructs a new Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads a command from the user.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        this.printLine();
        System.out.println("Hello! I'm Max");
        System.out.println("What can I do for you?");
        this.printLine();
    }

    /**
     * Prints a line for UI formatting.
     */
    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void printBye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Displays a message indicating a task has been added.
     *
     * @param task The task that was added.
     * @param size The current number of tasks.
     */
    public void printAdded(Task task, int size) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
        printLine();
    }

    /**
     * Displays a message indicating a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param size The current number of tasks.
     */
    public void printDeleted(Task task, int size) {
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
        printLine();
    }

    /**
     * Displays a message indicating a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void printDone(Task task) {
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        printLine();
    }

    /**
     * Displays a message indicating a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void printUndone(Task task) {
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        printLine();
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void printList(TaskList tasks) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        tasks.printList();
        printLine();
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Displays a message indicating there was an error loading tasks from the file.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks from file");
    }
}
