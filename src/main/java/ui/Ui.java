package ui;
import java.util.Scanner;

import tasks.Task;
import tasks.TaskList;

/**
 * Represents the user interface.
 */
public class Ui {

    /** Scanner to handle user input. */
    private Scanner scanner;
    /**
     * Constructor for Ui.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    /**
     * Shows the error message when loading file.
     */
    public void showLoadingError() {
        System.out.println("Error loading file");
    }
    /**
     * Shows the welcome message.
     */
    public void showWelcome() {
        String botName = "Dude";
        System.out.println("Hello! I'm " + botName);
        System.out.println("What can I do for you?");
    }

    /**
     * Shows a line.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads the command from the user.
     * @return String fullCommand
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Shows the error message.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Shows the task added message.
     */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Shows the task deleted message.
     */
    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Shows the schedule on specific date.
     */
    public void showSchedule(String output) {
        if (output.equals("")) {
            System.out.println("There are no tasks on this date.");
        } else {
            System.out.println("Here are the tasks on this date:");
            System.out.println(output);
        }
    }

    /**
     * Shows the list of tasks.
     */
    public void showList(TaskList userTasks) {
        if (userTasks.size() == 0) {
            System.out.println("You have no tasks.");
        } else {
            System.out.println("Here are the tasks in your list:");
            System.out.println(userTasks.toString());
        }
    }

    /**
     * Shows the goodbye message.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Shows the invalid command message.
     */
    public void showInvalidCommandMessage() {
        System.out.println("Command is invalid. Please try again.");
    }

    /**
     * Shows the task marked message.
     */
    public void showTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    /**
     * Shows the task unmarked message.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("Nice! I've marked this task as undone:");
        System.out.println(task.toString());
    }

    /**
     * Shows the tasklist saved message.
     */
    public void showSave() {
        System.out.println("Tasks saved to file.");
    }

    /**
     * Shows the tasklist loaded message.
     */
    public void showLoad() {
        System.out.println("Tasks loaded from file.");
    }

    /**
     * Shows the tasklist.
     * @param output The list of matching tasks.
     */
    public void showFind(String output) {
        System.out.println("Here are the matching tasks in your list:");
        System.out.println(output);
    }
}
