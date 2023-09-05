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
    public void greet() {
        System.out.println("Hello! I'm Thea •ᴗ•\nHow can I help you?");
    }

    /**
     * Sends exiting message to the user.
     */
    public void exit() {
        System.out.println("I hope I made your day easier with my service. See you again! >ᴗ<");
    }

    /**
     * Notifies user of the task marked.
     *
     * @param task marked task.
     */
    public void taskMark(Task task) {
        System.out.printf("Great job! ˊᗜˋ I've marked this task as done:\n  %s\n", task);
    }

    /**
     * Notifies user of the task unmarked.
     *
     * @param task unmarked task.
     */
    public void taskUnmark(Task task) {
        System.out.printf("Okay, I've marked this task as not done yet:\n  %s\n", task);
    }

    /**
     * Prints the current task list.
     *
     * @param tasks current task list.
     */
    public void printList(TaskList tasks) {
        if (!tasks.isEmpty()) {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("%d. %s%n", i + 1, tasks.get(i));
            }
        } else {
            System.out.println("Yay! You have no tasks in your list.");
        }
    }

    /**
     * Notifies user of the task added.
     *
     * @param task added task.
     * @param tasks new task list.
     */
    public void taskAdd(Task task, TaskList tasks) {
        System.out.println("I have added the following task to your list:\n  "
                + task.toString() + "\nNow you have " + tasks.size()
                + (tasks.size() == 1 ? " task" : " tasks")
                + " in the list. You can do this!");
    }

    /**
     * Shows an error message to the user.
     *
     * @param errorMessage error message to be shown.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
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
    public void taskDelete(Task task, TaskList tasks) {
        System.out.println("I have removed the following task to your list:\n  "
                + task.toString() + "\nNow you have " + (tasks.size() - 1)
                + ((tasks.size() - 1) == 1 ? " task" : " tasks")
                + " in the list.");
    }

    /**
     * Shows user the relevant tasks found from a keyword.
     *
     * @param relevantTasks list of the relevant tasks.
     */
    public void relevantTasksFound(TaskList relevantTasks) {
        if (relevantTasks.isEmpty()) {
            System.out.println("No matching task found. Maybe you have finished them?");
        } else {
            System.out.println("Here are the matching tasks on your list:");
            for (int i = 0; i < relevantTasks.size(); i++) {
                System.out.println((i + 1) + ". " + relevantTasks.get(i));
            }
        }
    }
}
