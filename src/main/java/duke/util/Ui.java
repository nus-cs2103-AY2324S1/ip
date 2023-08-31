package duke.util;

import duke.task.Task;

import java.util.Scanner;

/**
 * Represents user interface of the chatbot.
 */
public class Ui {
    private Scanner sc;
    private static final String HORIZONTAL_LINE = "____________________________________________________________\n";

    public Ui () {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns the next line of user input from the user interface.
     *
     * @return Next line of user input.
     */
    public String nextCommand() {
        if (!sc.hasNextLine()) {
            return "";
        }
        return sc.nextLine();
    }

    /**
     * Prints the entry message of the chatbot.
     */
    public void printEntryMessage() {
        String entryMessage = HORIZONTAL_LINE
                + "Hello! I'm Chad \n"
                + "What can I do for you? \n"
                + HORIZONTAL_LINE;
        System.out.println(entryMessage);
    }

    /**
     * Prints the exit message of the chatbot.
     */
    public void printExitMessage() {
        String exitMessage = HORIZONTAL_LINE
                + "Bye. Hope to see you again soon!\n"
                + HORIZONTAL_LINE;
        System.out.println(exitMessage);
    }

    /**
     * Prints all the String representation of Tasks in a given TaskList.
     *
     * @param list The given TaskList.
     */
    public void printList(TaskList list) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println(list.toString());
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints a message to indicate that a given task has been successfully added.
     *
     * @param task The given Task to be added to the target TaskList.
     * @param size The size of the target TaskList.
     */
    public void printAdd(Task task, int size) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints a message to indicate that a given task has been successfully deleted.
     *
     * @param task The given Task to be deleted from the target TaskList.
     * @param size The size of the target TaskList.
     */
    public void printDelete(Task task, int size) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints a message to indicate that a given task has been successfully marked.
     *
     * @param task The given Task to be marked as completed.
     */
    public void printMark(Task task) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints a message to indicate that a given task has been successfully unmarked.
     *
     * @param task The given Task to be marked as uncompleted.
     */
    public void printUnmark(Task task) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints the error message from a checked exception thrown.
     *
     * @param e The Exception thrown from the execution of a method.
     */
    public void printError(Exception e) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println("Oops! we encountered an error");
        System.out.println(e.getMessage());
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints the error message when the parser cannot find a matching command.
     */
    public void printCommandNotFound() {
        System.out.print(HORIZONTAL_LINE);
        System.out.println("Oops! I'm sorry, but I don't know what that means :-(");
        System.out.println(HORIZONTAL_LINE);
    }
}
