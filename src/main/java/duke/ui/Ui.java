package duke.ui;

import duke.tasks.Task;

import java.util.Scanner;

/**
 * Represents the Ui Class.
 * Responsible for printing commands.
 *
 * @author Shishir
 */
public class Ui {

    /** Scanner responsible for retrieving user input. */
    private Scanner input;

    /** Constructs the Ui object. */
    public Ui() {
        this.input = new Scanner(System.in);
    }

    /**
     * Returns the user's input command.
     * @return User's input.
     */
    public String readCommand() {
        return this.input.nextLine();
    }

    /** Prints a line. */
    public void showLine() {
        System.out.println("_______________________________________________________________");
    }

    /** Prints a greeting message. */
    public void greet() {
        System.out.println("Greetings, I am J.A.R.V.I.S! How may I assist you today?");
    }

    /** Prints a farewell message. */
    public void leave() {
        System.out.println("I shall now take my leave. Farewell!");
    }

    /**
     * Prints an acknowledgment message on successful addition to the list.
     * @param size Length of the list.
     * @param task Newly added task.
     */
    public void showAdd(int size, Task task) {
        System.out.println("Added the following task to the list.");
        System.out.println(size + ") " + task.toString());
        System.out.println("You currently have " + size + " tasks in your list.");
    }

    /**
     * Prints an acknowledgment message on a successful mark/unmark.
     * @param index Index of the newly marked/unmarked task.
     * @param task Newly marked/unmarked task.
     * @param isMark Mark if true, Unmark if false.
     */
    public void showStatus(int index, Task task, boolean isMark) {
        if (isMark) {
            System.out.println("The following task is marked as complete:");
        } else {
            System.out.println("The following task has been unmarked:");
        }
        System.out.println(index + ") " + task.toString());
        System.out.println("Is there anything else I can assist you with?");
    }

    /**
     * Prints an acknowledgment message on a successful deletion of a task.
     * @param index Index of the newly deleted task.
     * @param task Newly deleted task.
     */
    public void showDelete(int index, Task task) {
        System.out.println("The following task has been removed:");
        System.out.println(index + ") " + task.toString());
        System.out.println("Is there anything else I can assist you with?");
    }

    /**
     * Prints an acknowledgment message on request to display all the tasks.
     * @param size Size of the task list.
     */
    public void showList(int size) {
        if (size == 0) {
            System.out.println("Your task list is empty! Add a task to view it here.");
        } else {
            System.out.println("Tasks displayed. Your guidance is requested.");
        }
    }

    /**
     * Prints out the list of tasks which match with the keyword.
     * @param word Entered keyword.
     */
    public void showFind(String word) {
        System.out.println("Finding tasks that contain the entered keyword (" + word + ")");
    }
}
