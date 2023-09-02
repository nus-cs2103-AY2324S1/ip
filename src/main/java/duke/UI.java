package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

/**
 * The UI class contains methods that generate UI elements
 * such as line spaces
 *
 * @author Zi Xiang
 * @version CS2103T AY23/24 Sem 1
 */
public class UI {
    /** Generates a line spacer */
    private Scanner s;

    public UI() {
        this.s = new Scanner(System.in);
    }

    public void line() {
        System.out.println("\n----------------------------------------");
    }

    /**
     * Displays the loading error
     */
    public void showLoadingError() {
        this.line();
        System.out.println("File was not found on hard drive");
        System.out.println("Attempting to create file...");
        this.line();
    }

    /**
     * Displays welcome message
     */
    public void showWelcome() {
        this.line();
        System.out.println("Hello, I'm Prawn");
        System.out.println("What would you like me to do sire?");
        this.line();
    }

    /**
     * Displays IO error
     */
    public void showIoError() {
        this.line();
        System.out.println("Error in creating file");
        this.line();
    }

    /**
     * Reads the input from the user
     * @return The command from the user
     */
    public String readCommand() {
        return s.nextLine();
    }

    /**
     * Displays unknown command
     */
    public void showUnknownCommand() {
        System.out.println("I do not understand this command");
    }

    /**
     * Displays the Duke exception
     * @param msg The specific duke exception's message
     */
    public void showError(String msg) {
        System.out.println(msg);
    }

    /**
     * Displays the add message
     * @param t Task being added
     * @param size Size of the current TaskList
     */
    public void showAddMessage(Task t, int size) {
        System.out.println("Got it, will add task...");
        System.out.println(t);
        System.out.println("Now, you have " + size + " task(s)");
    }

    /**
     * Displays the mark message
     */
    public void showMarkMessage() {
        System.out.println("Alright, it has been marked");
    }

    /**
     * Displays the unmark message
     */
    public void showUnmarkMessage() {
        System.out.println("Alright, it has been unmarked");
    }

    /**
     * Displays the deleting message
     * @param t The task deleted
     * @param size The current size of the TaskList
     */
    public void showDeleteMessage(Task t, int size) {
        System.out.println("Sigh... fine, removing...");
        System.out.println(t);
        System.out.println("Now, you have " + size + " task(s)");
    }

    /**
     * Displays the out of bound message
     */
    public void showOutOfBounds() {
        System.out.println("Cannot access out of bounds index");
    }

    /**
     * Displays the tasks that are found.
     *
     * @param tasks the tasks to be displayed.
     */
    public void showFoundTask(ArrayList<Task> tasks) {
        System.out.println("Here are the matching task in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Displays the time format error message
     */
    public void showTimeFormatError() {
        System.out.println("The time format is wrong");

    }

}
