package chatbuddy.ui;

import chatbuddy.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Ui class handles interactions with the user.
 * It reads inputs from the users and output messages to the user.
 */
public class Ui {
    private final String DIVIDER = "    ____________________________________________________________";
    private Scanner scanner;

    /** Creates an instance of an Ui object. */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /** Prints out a divider. */
    public void showLine() {
        System.out.println(this.DIVIDER);
    }

    /** Prints the welcome message. */
    public void showWelcome() {
        showLine();
        System.out.println("    Hello! I'm Chat Buddy!");
        System.out.println("    What can I do for you?");
        showLine();
    }

    /** Prints the exit message. */
    public void showExit() {
        System.out.println("    Bye. Hope to see you again soon!");
    }

    /** Prints the error message. */
    public void showError(String errorMessage) {
        System.out.println("    " + errorMessage);
    }

    /**
     * Reads the user input.
     *
     * @return The user input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a list of task.
     *
     * @param taskStrings The list of tasks represented by Strings.
     * @param message The message to print before the list.
     */
    public void showTaskList(ArrayList<String> taskStrings, String message) {
        System.out.println("    " + message);
        for (int i = 0; i < taskStrings.size(); i++) {
            String taskString = taskStrings.get(i);
            System.out.println(String.format("    %1s.%2s", i + 1, taskString));
        }
    }

    /**
     * Prints a confirmation message about the addition of a task.
     *
     * @param task The task that was added.
     * @param totalNumOfTasks The number of tasks after the task was added.
     */
    public void showTaskAddition(Task task, int totalNumOfTasks) {
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println(String.format("    Now you have %d tasks in the list.", totalNumOfTasks));
    }

    /**
     * Prints a confirmation message about the deletion of a task.
     *
     * @param task The task that was deleted.
     * @param totalNumOfTasks The number of tasks after the task was deleted.
     */
    public void showTaskDeletion(Task task, int totalNumOfTasks) {
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + task);
        System.out.println(String.format("    Now you have %d tasks in the list.", totalNumOfTasks));
    }

    /**
     * Prints a confirmation message about a task being marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showMarkTask(Task task) {
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + task);
    }

    /**
     * Prints a confirmation message about a task being marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void showUnmarkTask(Task task) {
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + task);
    }
}
