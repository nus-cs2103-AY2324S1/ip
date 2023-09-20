package oop;

import java.util.Scanner;

import tasks.Task;


/**
 * Ui class handles the text that is outputted in the gui.
 */
public class Ui {
    private static final String BYE_TEXT = "Bye. Hope to see you again soon! I'll close the window now.\n";

    /** The Scanner object used to read user input */
    private Scanner scanner;

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

    /**
     * Gets a simple greeting to the user before any command is processed.
     *
     * @param name The name of the chatbot.
     */
    public String getGreeting(String name) {
        String str1 = "Hello! I'm " + name + "!";
        String str2 = "What can I do for you? Just send me a 'help' command to see all the things I can do!!";
        return str1 + "\n" + str2;
    }

    /**
     * Gets a message summarising the tasks when a new task is added.
     *
     * @param task The task that was added.
     * @param tasks The TaskList instance used by duke.
     * @return These strings concatenated to one another.
     */
    public String getTaskAddedMessage(Task task, TaskList tasks) {
        String str1 = "Got it. I've added this task:";
        String str2 = String.format("%s", task.toString());
        String str3 = String.format("Now you have %d tasks in the list.", tasks.getSize());
        return str1 + "\n" + str2 + "\n" + str3;
    }

    /**
     * Get a message to indicate successful deletion of a task.
     *
     * @param task The task that was removed.
     * @return The concatenated string.
     */
    public String getTaskDeletedMessage(Task task) {
        String str1 = "Noted. I've removed this task:";
        String str2 = task.toString();
        return str1 + "\n" + str2;
    }

    /**
     * Gets a message to indicate successful marking of a task as done.
     *
     * @param task The task that was marked as done.
     * @return The concatenated string.
     */
    public String getTaskMarkedMessage(Task task) {
        String str1 = "Nice! I've marked this task as done:";
        String str2 = "" + task.toString();
        return str1 + "\n" + str2;
    }
    /**
     * Gets a message to indicate successful marking of a task as not done.
     *
     * @param task The task that was marked as not done.
     * @return The concatenated string.
     */
    public String getTaskUnmarkedMessage(Task task) {
        String str1 = "Ok, I've marked this task as not done yet:";
        String str2 = task.toString();
        return str1 + "\n" + str2;
    }

    /**
     * Gets a simple message to show a loading error.
     * @return The same string.
     */
    public String getLoadingErrorMessage() {
        return "Loading error...";
    }

    /**
     * Gets an error message when something goes wrong.
     *
     * @param message The error message to be displayed, as a string.
     * @return The same error string.
     */
    public String getErrorMessage(String message) {
        return message;
    }

    /**
     * Gets a simple error message when user command does not match any available command
     *
     * @return The same message.
     * */
    public String getNotSureMessage() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    /**
     * Gets the task in the usual format seen in the CLI.
     *
     * @param id The index of the task to be displayed, within the TaskList.
     * @param task The task to be displayed.
     *
     * @return The string in the task format.
     */

    public String getTaskString(int id, Task task) {
        return String.format("%d.%s\n", id + 1, task.toString());
    }

    /**
     * Gets a preamble message for the FindTasksCommand.
     * @return The same message
     */
    public String getFindTaskMessage() {
        return "Here are the matching tasks in your list:\n";
    }

    /**
     * Gets a simple farewell message.
     *
     * @return The same message.
     */
    public String bidFarewell() {
        return BYE_TEXT;
    }
}
