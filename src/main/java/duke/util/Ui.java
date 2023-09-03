package duke.util;

import java.util.Scanner;

import duke.task.Task;

/**
 * UI class that handles interaction with the user.
 */
public class Ui {
    private static final String LINE = "---------------------"; //Line to be drawn on the screen

    /**
     * Scans the next input given by the user.
     * @return String containing the instructions by user
     */
    public String read() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Prints line onto the console
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Prints complete message when action is carried out.
     * @param message complete message to be displayed
     */
    public void showComplete(String message) {
        System.out.println(message);
    }

    /**
     * Prints error message when exception is thrown.
     * @param errorMessage message of the exception
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Prints message when task has been marked.
     * @param task task that was marked
     */
    public void showMarked(Task task) {
        System.out.println("Nice! I've Marked this task as done:");
        System.out.println(task);
    }

    /**
     * Prints message when task has been unmarked.
     * @param task task that was unmarked
     */
    public void showUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    /**
     * Prints an error loading file data onto the screen.
     */
    public void showLoadingError() {
        System.out.println("Error loading data! There is no file found with the given filepath!");
    }

    /**
     * Prints the welcome message
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println(" Hello! I'm Somebodyhaha\n"
                + " What can I do for you?");
    }

    /**
     * Prints the result after a task has been added.
     * @param task task that was added
     * @param count current number of tasks in the tasklist
     */
    public void showTaskAdded(Task task, int count) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + (count) + " tasks in the list.");
    }

    /**
     * Displays goodbye message.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to See you again soon!");
    }
}
