package duke.ui;

import duke.exception.DukeException;
import duke.task.Task;
import duke.list.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Ui class is responsible for handling the standard input and output of the Duke Program.
 * It handles the printing of prompts and responses and takes in input commands from standard input
 */
public class Ui {

    private Scanner sc;

    /**
     * Constructs a new instance of the Ui class
     * Initialises an internal Scanner object to read input from standard input
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     *  Returns a welcome message from the FUNNY application.
     *  Introduces the application and invites users to interact with it
     *
     * @return Welcome Message
     */
    public String showWelcome() {
        return "Hello! I'm FUNNY.\nWhat can I do for you?";
    }

    /**
     *  Read a command input from the user.
     *
     * @return A string containing the user's input command.
     */
    public String readCommand() {
        return this.sc.nextLine().trim();
    }

    /**
     * Returns an error message indicating a loading issue with the tasklist.
     * Indicates that a brand new tasklist is initialised.
     * @return Loading Error Message
     */
    public String showLoadingError() {
        return "Your tasklist is empty.";
    }

    /**
     * Returns a message notifying the user that loading of previous task list was successful
     * @return Loading Success Message
     */
    public String showLoadingSuccess() {
        return "You have an existing tasklist.";
    }

    /**
     * Returns a message confirming the addition of a new task.
     * This method prints a formatted message to the console after successfully
     * adding a task to the tasklist. It includes information about
     * the added task and the updated total number of tasks in the list.
     *
     * @param task The task that was added.
     * @param taskList The FunnyList containing the tasks.
     * @return Message containing the details on the addition of task
     */
    public String showTaskAdded(Task task, TaskList taskList) {
        return "Got it. I've added this task:\n"
                + "\t" + task + "\n"
                + "Now you have " + String.valueOf(taskList.size()) + " tasks in the list";
    }

    /**
     * Returns an error message indicating that a wrong command was entered.
     * Indicates that a brand new tasklist is initialised.
     *
     * @return Error Message (specifically for wrong commands)
     */
    public String showInvalidInput() {
        return showError(new DukeException("I'm sorry, but I don't know what that means :-("));
    }

    /**
     * Returns an error message based on the provided DukeException.
     *
     * @param e The DukeException containing the error details.
     * @return Error Message
     */
    public String showError(DukeException e) {
        return e.toString();
    }

    /**
     * Returns a message confirming the task to be marked as completed.
     * It includes information about the marked task.
     *
     * @param task The task to be marked as completed.
     * @return Message containing the details on the completion of the task
     */
    public String showMarkMessage(Task task) {
        return "Nice! I've marked this task as done:\n\t" + task;
    }

    /**
     * Returns a message confirming the task to be unmarked as incomplete.
     * It includes information about the unmarked task.
     *
     * @param task The task to be marked as incomplete.
     * @return Message containing the details on the undoing of the task
     */
    public String showUnmarkMessage(Task task) {
        return "Ok, I've marked this task as not done yet:\n\t" + task;
    }

    /**
     * Returns the items in a FunnyList.
     * This method iterates through the provided FunnyList and returns each item
     * formatted as a numbered list.
     *
     * @param taskList The FunnyList containing the tasks to be displayed.
     * @return A String containing the details of each items in the list
     */
    public String showItems(TaskList taskList) {
        StringBuilder listString = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            listString.append((i + 1) + ". " + taskList.get(i) + "\n");
        }
        return listString.toString();
    }

    /**
     * Returns a message confirming the task to be unmarked as incomplete.
     * It includes information about the unmarked task.
     *
     * @param task The task to be marked as incomplete.
     * @return Message containing the details on the deletion of the task
     */
    public String showDeleteMessage(Task task, TaskList taskList) {
        return "Noted. I've removed this task:\n"
                + "\t" + task + "\n"
                + "Now you have " + taskList.size() + " tasks in the list";
    }

    public String showMatchingTasks(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            return "There are no matching tasks found based on you search.";
        } else {
            StringBuilder listString = new StringBuilder();

            listString.append("Here are the matching tasks in your list\n");
            for (int i = 0; i < tasks.size(); i++) {
                listString.append("" + (i + 1) + ". " + tasks.get(i) + "\n");
            }
            return listString.toString();
        }
    }

    public String showRescheduleMessage(Task task) {
        return "Noted. I've rescheduled the deadline for this task as shown:\n"
                + "\t" + task;
    }

    /**
     * Returns a goodbye message confirming the termination of the program.
     *
     * @return Goodbye Message
     */
    public String showGoodbyeMessage() {
        return "Bye. Hope to see you again soon! Closing the application in 3 seconds...";
    }

    /**
     * Prints a decorative line to the console.
     */
    public void printLine() {
        System.out.print("\t");
        for (int i = 0; i < 80; i++) {
            System.out.print("─");
        }
        System.out.println();
    }
}
