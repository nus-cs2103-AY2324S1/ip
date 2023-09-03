package ui;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * The `Ui` class handles user interface interactions and provides methods for displaying messages
 * and receiving input from the user.
 */
public class Ui {
    private final Scanner in;
    private final PrintStream out;

    /**
     * Constructs a `Ui` object using the default input and output streams (System.in and System.out).
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Constructs a `Ui` object using the specified input and output streams.
     *
     * @param in  The input stream for reading user input.
     * @param out The output stream for displaying messages to the user.
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcomeMessage() {
        String logo = "____________________________________________________________\n"
                + "YO! The name's Bond, James Bond.  \n"
                + "What can I do for you? \n"
                + "____________________________________________________________\n";
        out.println(logo);
    }

    /**
     * Displays an error message indicating a failed loading operation.
     */
    public void showLoadingError() {
        out.println("Failed to load.");
    }

    /**
     * Displays a list of tasks to the user.
     *
     * @param tasks The task list to be displayed.
     */
    public void listout(TaskList tasks) {
        for (int i = 0; i < tasks.len(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }

    /**
     * Checks if there is more user input available.
     *
     * @return `true` if there is more input available, `false` otherwise.
     */
    public boolean isThereNext() {
        return this.in.hasNextLine();
    }

    /**
     * Reads a line of input from the user.
     *
     * @return The user's input as a string.
     */
    public String readLine() {
        return this.in.nextLine();
    }

    /**
     * Displays an error message to the user.
     *
     * @param error The error message to be displayed.
     */
    public void showErrorMessage(String error) {
        out.println(error);
    }

    /**
     * Exits the application, saving tasks to a file if necessary.
     *
     * @param tasks   The task list to be saved.
     * @param storage The storage object for saving tasks to a file.
     */
    public void exit(TaskList tasks, Storage storage) {
        out.println("Bye. Till the next time.");
        storage.saveTasksToFile(tasks);
        System.exit(0);
    }

    /**
     * Displays a message indicating the removal of a task and the remaining number of tasks.
     *
     * @param task   The task that was removed.
     * @param remain The remaining number of tasks.
     */
    public void showDeleteMessage(Task task, int remain) {
        out.println("REMOVED FOR YOU:)\n" + task.toString() + "\n Now you have:" + remain + " tasks left.");
    }

    /**
     * Displays a message indicating the completion of a task.
     *
     * @param task The task that was marked as done.
     */
    public void markMessage(Task task) {
        out.println("you're done. DONEEE");
    }

    /**
     * Displays a message indicating the unmarking of a task as done.
     *
     * @param task The task that was marked as undone.
     */
    public void unmarkMessage(Task task) {
        out.println("Done. Stop being lazy.\n" + task.toString());
    }

    /**
     * Displays a message indicating the addition of an event task.
     *
     * @param task The event task that was added.
     */
    public void eventMessage(Task task) {
        out.println("ADDED event FOR YOU!!!!\n" + task.toString());
    }

    /**
     * Displays a message indicating the addition of a deadline task.
     *
     * @param task The deadline task that was added.
     */
    public void DeadlineMessage(Task task) {
        out.println("ADDED deadline FOR YOU!!!!\n" + task.toString());
    }


    /**
     * Displays a message indicating the addition of a todo task.
     *
     * @param tasks The todo task that was added.
     */
    public void showSearchResults(TaskList tasks) {
        out.println("HERE ARE YOUR MATCHES:\n");
        listout(tasks);
    }

    public void todoMessage(Task task) {
        out.println("ADDED todo FOR YOU!!!!\n" + task.toString());
    }
}
