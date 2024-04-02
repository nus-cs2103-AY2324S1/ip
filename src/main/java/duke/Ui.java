package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the user interface of the Duke application.
 * This class handles all interactions with the user.
 */
public class Ui {
    private Scanner sc;
    private String message;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void addMessage(String message) {
        this.message += message;
    }

    public void clearMessage() {
        this.message = "";
    }

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
    public void printWelcome() {
        this.addMessage("Hello! I'm Max\n");
        this.addMessage("What can I do for you?\n");
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
        this.addMessage("Bye. Hope to see you again soon!\n");

    }

    /**
     * Displays a message indicating a task has been added.
     *
     * @param task The task that was added.
     * @param size The current number of tasks.
     */
    public void printAdded(Task task, int size) {
        this.addMessage("Got it. I've added this task:\n");
        this.addMessage(task.toString() + "\n");
        this.addMessage("Now you have " + size + " tasks in the list.\n");
    }

    /**
     * Displays a message indicating a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param size The current number of tasks.
     */
    public void printDeleted(Task task, int size) {
        this.addMessage("Noted. I've removed this task:\n");
        this.addMessage(task.toString() + "\n");
        this.addMessage("Now you have " + size + " tasks in the list.\n");
    }

    /**
     * Displays a message indicating a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void printDone(Task task) {
        this.addMessage("Nice! I've marked this task as done:\n");
        this.addMessage(task + "\n");
    }

    /**
     * Displays a message indicating a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void printUndone(Task task) {
        this.addMessage("OK, I've marked this task as not done yet:\n");
        this.addMessage(task + "\n");
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void printList(TaskList tasks) {
        this.addMessage("Here are the tasks in your list:\n");
        this.addMessage(tasks.printList());
    }

    public void printFound(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            this.addMessage(tasks.get(i).toString() + "\n");
        }
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        this.addMessage("Error: " + message + "\n");
    }

    /**
     * Displays a message indicating there was an error loading tasks from the file.
     */
    public void showLoadingError() {
        this.addMessage("Error loading tasks from file\n");
    }
}
