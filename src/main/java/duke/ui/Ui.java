package duke.ui;

import duke.tasks.Task;

import java.util.ArrayList;
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
        this.showLine();
        System.out.println("Greetings, I am Jarvis. How may I assist you today?");
        this.showLine();
    }

    /** Prints a farewell message. */
    public void leave() {
        this.showLine();
        System.out.println("I shall now take my leave. Farewell!");
        this.showLine();
    }

    /**
     * Prints an acknowledgment message on successful addition to the list.
     * @param size Length of the list.
     * @param task Newly added task.
     */
    public void showAdd(int size, Task task) {
        this.showLine();
        System.out.println("Added the following task to the list.");
        System.out.println(size + ") " + task.toString());
        System.out.println("You currently have " + size + " tasks in your list.");
        this.showLine();
    }

    /**
     * Prints an acknowledgment message on a successful mark.
     * @param index Index of the newly marked task.
     * @param task Newly marked task.
     */
    public void showMark(int index, Task task) {
        this.showLine();
        System.out.println("The following task is marked as complete:");
        System.out.println(index + ") " + task.toString());
        System.out.println("Is there anything else I can assist you with?");
        this.showLine();
    }

    /**
     * Prints an acknowledgment message on a successful deletion of a task.
     * @param index Index of the newly deleted task.
     * @param task Newly deleted task.
     */
    public void showDelete(int index, Task task) {
        this.showLine();
        System.out.println("The following task has been removed:");
        System.out.println(index + ") " + task.toString());
        System.out.println("Is there anything else I can assist you with?");
        this.showLine();
    }

    /**
     * Prints an acknowledgment message on a successful unmark.
     * @param index Index of the newly unmarked task.
     * @param task Newly unmarked task.
     */
    public void showUnmark(int index, Task task) {
        this.showLine();
        System.out.println("The following task has been unmarked:");
        System.out.println(index + ") " + task.toString());
        System.out.println("Is there anything else I can assist you with?");
        this.showLine();
    }

    /**
     * Prints an acknowledgment message on request to display all the tasks.
     * @param tasks List of all tasks.
     */
    public void showList(ArrayList<Task> tasks) {
        this.showLine();
        if (tasks.size() == 0) {
            System.out.println("Your task list is empty! Add a task to view it here.");
        } else {
            System.out.println("Tasks displayed. Your guidance is requested.");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ") " + tasks.get(i).toString());
            }
        }
    }

    /**
     * Prints out the list of tasks which match with the keyword.
     * @param tasks List of Filtered Tasks.
     */
    public void showFind(ArrayList<Task> tasks) {
        this.showLine();
        if (tasks.size() == 0) {
            System.out.println("There are no matching tasks with your entered task!");
        } else {
            System.out.println("The following tasks match with your entered task!");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ") " + tasks.get(i).toString());
            }
        }
        this.showLine();
    }
}
