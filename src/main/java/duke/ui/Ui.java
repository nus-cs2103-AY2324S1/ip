package duke.ui;

import duke.tasks.Task;

import java.util.List;
import java.util.Scanner;

/**
 * UI of the application
 */
public class Ui {
    private static final String LINE = "    __________________________________________";
    private final Scanner s = new Scanner(System.in);

    /**
     * Reads a line from the standard input.
     *
     * @return the line read from the standard input
     */
    public String readCommand() {
        return s.nextLine();
    }

    /**
     * Prints to standard output success message for setting marked value of a task.
     *
     * @param isMark boolean value whether to mark the task
     * @param task the task to be marked
     */
    public void showSuccessMark(boolean isMark, Task task) {
        this.formatLines(() -> {
            if (isMark) {
                System.out.println("    Nice! I've marked this task as done:");
            } else {
                System.out.println("    OK, I've marked this task as not done yet:");
            }
            System.out.println("      " + task);
        });
    }

    /**
     * Prints to standard output after adding a task to Tasks.
     *
     * @param task the task to be added
     * @param totalTasks the updated total number of tasks
     */
    public void showAddTask(Task task, int totalTasks) {
        this.formatLines(() -> {
            System.out.println("    Got it. I've added this task:");
            System.out.println("      " + task);
            System.out.println("    Now you have " + totalTasks + " tasks in the list.");
        });
    }

    /**
     * Prints to standard output a message with no tasks.
     */
    public void showNoTasks() {
        this.formatLines(() -> System.out.println("    No tasks found!"));
    }

    /**
     * Prints to standard output all tasks.
     *
     * @param tasks the tasks object to be printed
     * @param isFiltered boolean flag for whether the tasks are filtered
     */
    public void showTasks(List<Task> tasks, boolean isFiltered) {
        this.formatLines(() -> {
            String header = isFiltered ? "Here are the matching tasks in your list:" : "Here are the tasks in your list:";
            System.out.println("    " + header);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                System.out.println(String.format("    %s.%s", i + 1, task));
            }
        });
    }

    /**
     * Prints to standard output a success message after deleting a task.
     *
     * @param task the task to be deleted
     * @param totalTasks total number of tasks left after deletion
     */
    public void showSuccessDelete(Task task, int totalTasks) {
        this.formatLines(() -> {
            System.out.println("    Noted. I've removed this task:");
            System.out.println("    " + task);
            System.out.println("    Now you have " + totalTasks + " tasks in the list.");
        });
    }

    /**
     * Prints to standard output error messages.
     *
     * @param message the error message to be outputted
     */
    public void showError(String message) {
        this.formatLines(() -> System.out.println("    " + message));
    }

    /**
     * Prints to standard output a prompt to enter a command.
     */
    public void showUnknownCommand() {
        this.formatLines(() -> System.out.println("    Please enter something :-)"));
    }

    /**
     * Prints to standard output a success message for loading storage.
     *
     * @param filepath the filepath where the storage loaded data from
     */
    public void showSuccessLoadingStorage(String filepath) {
        this.formatLines(() -> System.out.println("    Data has been restored from " + filepath));
    }

    /**
     * Prints to standard output an error message when data file is not found.
     */
    public void showErrorFileNotFound() {
        this.formatLines(() -> System.out.println("    Data file not found, creating a new one"));
    }

    /**
     * Prints to standard output an error message when loading of data failed.
     */
    public void showErrorLoadingFile() {
        this.formatLines(() -> System.out.println("    Error creating new file, quitting program now..."));
    }

    /**
     * Prints to standard output an error message when writing to data file failed.
     */
    public void showErrorWritingFile() {
        this.formatLines(() -> System.out.println("    Error saving to file, quitting program now..."));
    }

    /**
     * Prints to standard output a greeting message.
     */
    public void showGreetings() {
        this.formatLines(() -> {
            System.out.println("    Hello I'm lynn the koala <3");
            System.out.println("    What can I do for you?");
        });
    }

    /**
     * Prints to standard output the exit message.
     */
    public void showExit() {
        this.formatLines(() -> System.out.println("    Bye. Hope to see you again soon!"));
    }

    /**
     * Helper method to format 2 UI lines between all output.
     *
     * @param runnable A runnable function to be ran between the two output lines
     */
    private void formatLines(Runnable runnable) {
        System.out.println(LINE);
        runnable.run();
        System.out.println(LINE);
    }
}
