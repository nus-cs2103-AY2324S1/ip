package duke.ui;

import duke.tasks.Task;

import java.util.Scanner;

public class Ui {
    /**
     * The scanner to read user input
     */
    private final Scanner scanner;

    /**
     * Constructor for Ui
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the command from the user
     *
     * @return the command from the user
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Shows the welcome message
     */
    public String showWelcome() {
        return "Hello I'm RyamBot\nWhat can I do for you?\nType \"help\" to see the list of commands";
    }

    /**
     * Shows the exit message
     */
    public String exit() {
        return "I shall now take my leave. Farewell!";
    }

    /**
     * Display the added task
     *
     * @param size the size of the list
     * @param task the task added
     */
    public String showAdd(int size, Task task) {
        return "Got it. I've added this task:" + "\n" + task.toString() + "\n" + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Displays the list of tasks
     *
     * @param size the size of the list
     */
    public String showList(int size) {
        return "Here are the tasks in your list:" + "\n" + size + " tasks in total.";
    }

    /**
     * Display the marked task message
     *
     * @param task the task marked
     */
    public String showMark(Task task) {
        return "Nice! I've marked this task as done:" + "\n" + task.toString();
    }

    /**
     * Display the unmarked task message
     *
     * @param task the task unmarked
     */
    public String showUnmark(int index, Task task) {
        return "Nice! I've unmarked this task as done:" + "\n" + task.toString();
    }

    /**
     * Display the deleted task message
     *
     * @param index the index of the task
     * @param task  the task deleted
     */
    public String showDelete(int index, Task task) {
        return "Noted. I've removed this task:" + "\n" + task.toString();
    }

    /**
     * Display the help message
     */
    public String help() {
        return "Here are the list of commands you can use:\n"
                + "list - Lists all the tasks\n"
                + "todo <task> - Adds a todo task\n"
                + "deadline <task> /by <date in dd/MM/yy HHmm> - Adds a deadline task\n"
                + "event <task> /from <date in dd/MM/yy HHmm> /to <date> - Adds an event task\n"
                + "delete <index> - Deletes the task at the index\n"
                + "mark <index> - Marks the task at the index as done\n"
                + "unmark <index> - Unmarks the task at the index as done\n"
                + "help - Shows the list of commands\n"
                + "bye - Exits the program";
    }

    public String showFilteredList(int length) {
        return "Here are the matching tasks in your list:" + "\n" + length + " tasks in total.";
    }

    /**
     * Returns an acknowledgment message on a successful mark/unmark.
     *
     * @param index  Index of the newly marked/unmarked task.
     * @param task   Newly marked/unmarked task.
     * @param isDone Mark if true, Unmark if false.
     */
    public String showStatus(int index, Task task, boolean isDone) {
        String message;
        if (isDone) {
            message = "The following task is marked as complete:\n";
        } else {
            message = "The following task has been unmarked:\n";
        }
        return message + index + "." + task.toString() + "\n"
                + "Is there anything else I can assist you with?";
    }
}
