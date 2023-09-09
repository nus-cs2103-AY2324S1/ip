package ui;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;

//CHECKSTYLE.OFF: CustomImportOrder
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
//CHECKSTYLE.ON: CustomImportOrder

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
    public String listout(TaskList tasks) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.len(); i++) {
            stringBuilder.append((i + 1) + "." + tasks.get(i).toString() + "\n");
        }
        return stringBuilder.toString();
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
    public String showErrorMessage(String error) {
        return error;
    }

    /**
     * Exits the application, saving tasks to a file if necessary.
     *
     * @param tasks   The task list to be saved.
     * @param storage The storage object for saving tasks to a file.
     */
    public String exit(TaskList tasks, Storage storage) {
        storage.saveTasksToFile(tasks);
        System.exit(0);
        return "Bye. Till the next time.";
    }

    /**
     * Displays a message indicating the removal of a task and the remaining number of tasks.
     *
     * @param task   The task that was removed.
     * @param remain The remaining number of tasks.
     */
    public String showDeleteMessage(Task task, int remain) {
        return "REMOVED FOR YOU:)\n" + task.toString() + "\n Now you have:" + remain + " tasks left.";
    }

    /**
     * Displays a message indicating the completion of a task.
     *
     * @param task The task that was marked as done.
     */
    public String markMessage(Task task) {
        return ("you're done. DONEEE");
    }

    /**
     * Displays a message indicating the unmarking of a task as done.
     *
     * @param task The task that was marked as undone.
     */
    public String unmarkMessage(Task task) {
        return "Done. Stop being lazy.\n" + task.toString();
    }

    /**
     * Displays a message indicating the addition of an event task.
     *
     * @param task The event task that was added.
     */
    public String eventMessage(Task task) {
        return "ADDED event FOR YOU!!!!\n" + task.toString();
    }

    /**
     * Displays a message indicating the addition of a deadline task.
     *
     * @param task The deadline task that was added.
     */
    public String deadlineMessage(Task task) {
        return ("ADDED deadline FOR YOU!!!!\n" + task.toString());
    }

    /**
     * Displays search results.
     *
     * @param tasks The tasks to search from.
     */
    public String showSearchResults(TaskList tasks) {
        return "HERE ARE YOUR MATCHES:" + listout(tasks);
    }

    /**
     * Displays a message indicating the addition of a todo task.
     *
     * @param task The todo task that was added.
     */
    public String todoMessage(Task task) {
        return ("ADDED todo FOR YOU!!!!\n" + task.toString());
    }
}
