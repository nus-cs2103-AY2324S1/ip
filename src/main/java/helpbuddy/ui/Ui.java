package helpbuddy.ui;

import java.util.Scanner;

import helpbuddy.task.Task;
import helpbuddy.task.TaskList;

/**
 * An Ui class that prints the corresponding response by HelpBuddy after user input.
 */
public class Ui {
    private final String horizontalLine = "\t____________________________________________________________";
    private Scanner sc;

    /**
     * Constructs an Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints a standard format for output message by HelpBuddy.
     * @param s String that contains the response from HelpBuddy.
     */
    private void printMessageBlock(String s) {
        System.out.println(horizontalLine + "\n\t" + s + horizontalLine + "\n");
    }

    /**
     * Prints hello message when HelpBuddy is first launched.
     */
    public void printHelloMessage() {
        printMessageBlock("Hello! I'm HelpBuddy.\n" + "\tWhat can I do for you?\n");
    }

    /**
     * Prints bye message when HelpBuddy is closed by user.
     */
    public void printByeMessage() {
        printMessageBlock("Bye. Hope to see you again soon!\n");
    }

    /**
     * Prints a list of Task that user has keyed into HelpBuddy.
     * @param taskList the taskList that stores Task objects.
     */
    public void printListMessage(TaskList taskList) {
        if (taskList.isEmpty()) {
            printMessageBlock("You have no task in your list.\n");
        } else {
            String messageOutput = "Here are the tasks in your list:\n";
            for (int i = 0; i < taskList.getSize(); i++) {
                int taskIndex = i + 1;
                messageOutput += "\t" + taskIndex + ". " + taskList.getTask(i) + "\n";
            }
            printMessageBlock(messageOutput);
        }
    }

    /**
     * Prints the message that a Task object is marked as done.
     * @param task the task to mark as done.
     */
    public void printMarkMessage(Task task) {
        printMessageBlock("Nice! I've marked this task as done:\n\t  " + task + "\n");
    }

    /**
     * Prints the message that a Task object is marked as not done.
     * @param task the task to mark as not done.
     */
    public void printUnmarkMessage(Task task) {
        printMessageBlock("OK, I've marked this task as not done yet:\n\t  " + task + "\n");
    }

    /**
     * Returns a String representation of the size of taskList.
     * @param taskList the taskList that contains Task objects.
     * @return a String message that Ui will output to users to notify the number of Task objects in taskList.
     */
    private String stringifyNumOfTasks(TaskList taskList) {
        return "\n\tNow you have " + taskList.getSize() + " tasks in the list.\n";
    }

    /**
     * Prints task added successfully.
     * @param task the task added.
     * @param taskList the taskList that task is added to.
     */
    public void printAddTaskMessage(Task task, TaskList taskList) {
        printMessageBlock("Got it. I've added this task:\n\t  " + task + stringifyNumOfTasks(taskList));
    }

    /**
     * Prints task deleted successfully.
     * @param task the task deleted.
     * @param taskList the taskList that task is deleted from.
     */
    public void printDeleteTaskMessage(Task task, TaskList taskList) {
        printMessageBlock("Noted. I've removed this task:\n\t  " + task + stringifyNumOfTasks(taskList));
    }

    /**
     * Prints error message.
     * @param s the String of error message.
     */
    public void printErrorMessage(String s) {
        printMessageBlock(s);
    }

    /**
     * Prints a message that directs users to follow the format of dates for Deadline and Event Task inputs.
     */
    public void printDateTimeParseErrorMessage() {
        printErrorMessage("Please enter the start/end time in the format of <DD/MM/YY HH:MM>!\n");
    }

    /**
     * Reads user inputs into HelpBuddy.
     * @return String representation of user input.
     */
    public String readInput() {
        return this.sc.nextLine();
    }

    /**
     * Closes the scanner.
     */
    public void closeScanner() {
        this.sc.close();
    }
}
