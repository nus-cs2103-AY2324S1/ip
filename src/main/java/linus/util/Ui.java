package linus.util;

import java.util.List;

import linus.task.Task;

/**
 * Represents a Ui.
 * A Ui object deals with interactions with the user.
 */
public class Ui {
    private StringBuilder output = null;

    public Ui() {
        output = new StringBuilder();
    }

    /**
     * Resets the output.
     */
    public void resetOutput() {
        output.setLength(0);
    }

    /**
     * Returns the output.
     *
     * @return The output.
     */
    public String getOutput() {
        return output.toString();
    }

    /**
     * Appends the given string to the output.
     *
     * @param str The string to be appended.
     */
    private void addToOutput(String str) {
        output.append(str);
    }

    /**
     * Prints a success message after a task is successfully added to the list.
     *
     * @param task Task added to list.
     * @param size Current size of list after addition.
     */
    public void printAddSuccessMessage(Task task, int size) {
        addToOutput("Got it. I've added this task:\n");
        addToOutput("\t" + task + "\n");
        addToOutput("Now you have " + size + " tasks in the list.\n");
    }

    /**
     * Prints a success message after a task is deleted from to the list.
     *
     * @param task Task deleted from list.
     * @param size Current size of list after deletion.
     */
    public void printDeleteSuccessMessage(Task task, int size) {
        addToOutput("Noted. I've removed this task:\n");
        addToOutput("\t" + task + "\n");
        addToOutput("Now you have " + size + " tasks in the list.\n");
    }

    /**
     * Prints a success message after a task is marked as done.
     * @param task
     * @param size
     */
    public void printMarkSuccessMessage(Task task, int size) {
        addToOutput("Nice! I've marked this task as done:\n");
        addToOutput("\t" + task + "\n");
    }

    /**
     * Prints a success message after a task is marked as not done yet.
     * @param task
     * @param size
     */
    public void printUnmarkSuccessMessage(Task task, int size) {
        addToOutput("OK, I've marked this task as not done yet:\n");
        addToOutput("\t" + task + "\n");
    }

    /**
     * Prints a success message after a task is found.
     *
     * @param tasks List of tasks found.
     */
    public void printFindSuccessMessage(List<Task> tasks) {
        if (tasks.size() == 0) {
            print("There are no matching tasks in your list.");
        } else {
            printList(tasks, "Here are the matching tasks in your list:\n");
        }
    }

    /**
     * Prints the given list with formatting.
     *
     * @param tasks List of tasks to be printed.
     * @param message The message to be printed before printing the list.
     */
    public void printList(List<Task> tasks, String message) {
        addToOutput(message + "\n");
        for (int i = 0; i < tasks.size(); i++) {
            int oneBasedIndex = i + 1;
            String formattedOutput = String.format("%d. %s\n", oneBasedIndex, tasks.get(i));
            addToOutput(formattedOutput);
        }
    }

    /**
     * Prints the welcome message.
     *
     */
    public String printWelcomeMessage() {
        return "Hello from Linus!! \n "
                + "What can I do for you uwu \n"
                + "Type 'help' to see the list of commands.";
    }

    /**
     * Prints the exit message.
     *
     */
    public void printExitMessage() {
        addToOutput("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the help message.
     *
     */
    public void printHelpMessage() {
        addToOutput("Here are the list of commands:\n");
        addToOutput("todo <description>\n");
        addToOutput("deadline <description> /by <date>\n");
        addToOutput("event <description> /from <date> /to <date> \n");
        addToOutput("delete <index>\n");
        addToOutput("mark <index>\n");
        addToOutput("unmark <index>\n");
        addToOutput("find <keyword>\n");
        addToOutput("list\n");
        addToOutput("help\n");
        addToOutput("bye\n");
    }

    /**
     * Prints the loading error message.
     *
     */
    public void showLoadingError() {
        addToOutput("The file system experienced an unexpected error.");
    }

    /**
     * Prints a given message with formatting.
     *
     * @param message The message to be printed.
     */
    public void print(String message) {
        addToOutput(message);
    }

}
