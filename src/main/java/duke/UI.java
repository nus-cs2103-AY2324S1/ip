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
    public String showLoadingError() {
        return ("File was not found on hard drive\nAttempting to create file...v");
    }

    /**
     * Displays welcome message
     */
    public String showWelcome() {
        return ("Hello, I'm Prawn\nWhat would you like me to do sire? ");
    }

    /**
     * Displays IO error
     */
    public String showIoError() {
        return ("Error in creating file");
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
    public String showUnknownCommand() {
        return ("I do not understand this command");
    }

    /**
     * Displays the Duke exception
     * @param msg The specific duke exception's message
     */
    public String showError(String msg) {
        return (msg);
    }

    /**
     * Displays the add message
     * @param t Task being added
     * @param size Size of the current TaskList
     */
    public String showAddMessage(Task t, int size) {
        return ("Got it, will add task...\n" + t + "\n" + "Now, you have " + size + " task(s)");
    }

    /**
     * Displays the mark message
     */
    public String showMarkMessage(Task t) {
        return ("Alright, it has been marked\n" + t);
    }

    /**
     * Displays the unmark message
     */
    public String showUnmarkMessage(Task t) {
        return ("Alright, it has been unmarked\n" + t);
    }

    /**
     * Displays the deleting message
     * @param t The task deleted
     * @param size The current size of the TaskList
     */
    public String showDeleteMessage(Task t, int size) {
        return ("Sigh... fine, removing...\n" + t + "\n" + "Now, you have " + size + " task(s)");
    }

    /**
     * Displays the out of bound message
     */
    public String showOutOfBounds() {
        return ("Cannot access out of bounds index");
    }

    /**
     * Displays the tasks that are found.
     *
     * @param tasks the tasks to be displayed.
     */
    public String showFoundTask(ArrayList<Task> tasks) {
        String temp = "";
        temp += "Here are the matching task in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            temp += ((i + 1) + ". " + tasks.get(i) + "\n");
        }
        return temp;
    }

    /**
     * Displays the time format error message
     */
    public String showTimeFormatError() {
        return ("The time format is wrong");

    }

}
