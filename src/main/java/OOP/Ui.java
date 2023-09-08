package OOP;

import Tasks.Task;

import java.util.Scanner;

public class Ui {
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
     * Prints out a message that contains the divider.
     *
     * @return The same message.
     */
    public String printDivider() {
        System.out.println('\t' + "_________________________________________");
        return '\t' + "_________________________________________";
    }

    /**
     * Prints out a simple greeting to the user before any command is processed.
     *
     * @param name The name of the chatbot.
     */
    public String printGreeting(String name) {
        printDivider();
        String str1 = "Hello! I'm " + name + "!";
        String str2 = "What can I do for you?";
        System.out.println(str1);
        System.out.println(str2);
        printDivider();
        return str1 + "\n" + str2;
    }

    /**
     * Prints out a message summarising the tasks when a new task is added.
     *
     * @param task The task that was added.
     * @param tasks The TaskList instance used by Duke.
     * @return These strings concatenated to one another.
     */
    public String printTaskAddedMessage(Task task, TaskList tasks) {
        String str1 = "\t Got it. I've added this task:";
        String str2 = String.format("\t\t %s", task.toString());
        String str3 =String.format("\tNow you have %d tasks in the list.", tasks.getSize());
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);
        return str1 + "\n" + str2 + "\n" + str3;
    }

    /**
     * Prints out a message to indicate successful deletion of a task.
     *
     * @param task The task that was removed.
     * @return The concatenated string.
     */
    public String printTaskDeletedMessage(Task task) {
        String str1 = "\tNoted. I've removed this task:";
        String str2 = "\t\t" + task.toString();
        System.out.println(str1);
        System.out.println(str2);
        return str1 + "\n" + str2;
    }

    /**
     * Prints out a message to indicate successful marking of a task as done.
     *
     * @param task The task that was marked as done.
     * @return The concatenated string.
     */
    public String printTaskMarkedMessage(Task task) {
        String str1 = "\tNice! I've marked this task as done:";
        String str2 = "\t\t" + task.toString();
        System.out.println(str1);
        System.out.println(str2);
        return str1 + "\n" + str2;
    }
    /**
     * Prints out a message to indicate successful marking of a task as not done.
     *
     * @param task The task that was marked as not done.
     * @return The concatenated string.
     */
    public String printTaskUnmarkedMessage(Task task) {
        String str1 = "\tOk, I've marked this task as not done yet:";
        String str2 = "\t\t" + task.toString();
        System.out.println(str1);
        System.out.println(str2);
        return str1 + "\n" + str2;
    }

    /**
     * Prints out a simple message to show a loading error.
     * @return The same string.
     */
    public String showLoadingError() {
        System.out.println("Loading error...");
        return "Loading error...";
    }

    /**
     * Prints out an error message when something goes wrong.
     *
     * @param message The error message to be printed, as a string.
     * @return The same error string.
     */
    public String showError(String message) {
        System.out.println(message);
        return message;
    }

    /**
     * Prints a simple error message when user command does not match any available command
     *
     * @return The same message.
     * */
    public String printNotSureMessage() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    /**
     * Prints out the task in the usual format seen in the CLI.
     *
     * @param id The index of the task to be printed, within the TaskList.
     * @param task The task to be displayed.
     *
     * @return The string in the task format.
     */

    public String printTask(int id, Task task) {
        System.out.println(String.format("\t%d.%s", id + 1, task.toString()));
        return String.format("\t%d.%s\n", id + 1, task.toString());
    }

    /**
     * Print a preamble message for the FindTasksCommand.
     * @return The same message
     */
    public String printFindTaskMessage() {
        System.out.println("Here are the matching tasks in your list:");
        return "Here are the matching tasks in your list:\n";
    }

    /**
     * Prints out a simple farewell message.
     *
     * @return The same message.
     */
    public String bidFarewell() {
        System.out.println("Bye. Hope to see you again soon!");
        return "Bye. Hope to see you again soon! I'll close the window now.\n";
    }
}
