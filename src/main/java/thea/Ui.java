package thea;

import java.util.Scanner;

/**
 * Represents the UI which deals with all the interactions with user.
 */
public class Ui {
    private final Scanner input;

    /**
     * Constructs a new Ui object.
     */
    public Ui() {
        this.input = new Scanner(System.in);
    }

    /**
     * Greets the user.
     */
    public static String greet() {
        return ("Hello! I'm Thea\nHow can I help you?\n");
    }

    /**
     * Sends exiting message to the user.
     */
    public String exit() {
        return ("I hope I made your day easier with my service. See you again! ><\n");
    }

    /**
     * Notifies user of the task marked.
     *
     * @param task marked task.
     */
    public String taskMarked(Task task) {
        return ("Great job! I've marked this task as done:\n  " + task + "\n");
    }

    /**
     * Notifies user of the task unmarked.
     *
     * @param task unmarked task.
     */
    public String taskUnmarked(Task task) {
        return ("Okay, I've marked this task as not done yet:\n  " + task + "\n");
    }

    /**
     * Prints the current task list.
     *
     * @param tasks current task list.
     */
    public String printList(TaskList tasks) {
        if (!tasks.isEmpty()) {
            String output = "";
            for (int i = 0; i < tasks.size(); i++) {
                output += (i + 1 + ". " + tasks.get(i) + "\n");
            }
            return output;
        } else {
            return ("Yay! You have no tasks in your list.");
        }
    }

    /**
     * Notifies user of the task added.
     *
     * @param task added task.
     * @param tasks new task list.
     */
    public String taskAdded(Task task, TaskList tasks) {
        return ("I have added the following task to your list:\n  "
                + task.toString() + "\nNow you have " + tasks.size()
                + (tasks.size() == 1 ? " task" : " tasks")
                + " in the list. You can do this!");
    }

    /**
     * Shows an error message to the user.
     *
     * @param errorMessage error message to be shown.
     */
    public String showError(String errorMessage) {
        return (errorMessage);
    }

    /**
     * Reads the next line of the user input.
     *
     * @return the next line of input.
     */
    public String readNextLine() {
        return input.nextLine();
    }

    /**
     * Notifies user of the task deleted.
     *
     * @param task deleted task.
     * @param tasks old task list before deletion.
     */
    public String taskDeleted(Task task, TaskList tasks) {
        return ("I have removed the following task to your list:\n  "
                + task.toString() + "\nNow you have " + (tasks.size() - 1)
                + ((tasks.size() - 1) == 1 ? " task" : " tasks")
                + " in the list.");
    }

    /**
     * Shows user the relevant tasks found from a keyword.
     *
     * @param relevantTasks list of the relevant tasks.
     */
    public String relevantTasksFound(TaskList relevantTasks) {
        if (relevantTasks.isEmpty()) {
            return ("No matching task found. Maybe you have finished them?\n");
        } else {
            String output = "Here are the matching tasks on your list:\n";
            for (int i = 0; i < relevantTasks.size(); i++) {
                output += ((i + 1) + ". " + relevantTasks.get(i) + "\n");
            }
            return output;
        }
    }
}
