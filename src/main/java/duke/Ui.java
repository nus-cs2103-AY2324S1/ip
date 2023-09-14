package duke;

import java.util.Scanner;

/**
 * Text UI of the application.
 */
public class Ui {

    /** Scanner to read input */
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String showLine() {
        return ("____________________________________________________________\n");
    }

    /**
     * Generates and prints the welcome message upon the start of the application.
     */
    public String showWelcome() {
        return ("Hello! I'm ET\n"
                + "What can I do for you?\n");
    }

    /**
     * Generates and prints the exit message upon the termination of the application.
     */
    public String showExit() {
        return ("Bye. Hope to see you again soon!\n");
    }

    /**
     * Reads the next command from user.
     * @return description of the command
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Shows the marked task.
     * @param task task that was marked.
     */
    public String showMarked(Task task) {
        return ("Nice! I've marked this task as done:\n"
                + task + "\n");
    }

    /**
     * Shows the unmarked task
     * @param task task that was marked.
     */
    public String showUnmarked(Task task) {
        return ("OK, I've marked this task as not done yet:\n"
                + task + "\n");
    }

    /**
     * Shows task added to task list.
     * @param task task added to task list
     * @param num number of task in task list.
     */
    public String showTaskAdded(Task task, int num) {
        return ( "Got it. I've added this task:\n"
                + task + "\n"
                + "Now you have " + num + " tasks in the list.\n");
    }

    /**
     * Shows the task deleted from task list
     * @param task task deleted from task list
     * @param num number of task in task list
     */
    public String showDeleted(Task task, int num) {
        return ("Noted. I've removed this task:\n"
                + task + "\n"
                + "Now you have " + num + " tasks in the list.\n");
    }

    /**
     * Shows the list of tasks
     * @param tasks list of tasks
     */
    public String showTaskList(TaskList tasks) {
        String response = "Here are the tasks in your list:\n";

        for (int i = 1; i <= tasks.total(); i++) {
            Task t = tasks.get(i - 1);
            String des = i + ". " + t.toString() + "\n";
            response = response.concat(des);
        }

        return response;
    }

    /**
     * Shows the list of tasks that match the keyword.
     * @param tasks tasks that match the keyword.
     */
    public String showMatchingTasks(TaskList tasks) {
        String response = ("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.total(); i++) {
            response = response.concat(tasks.get(i).toString() + "\n");
        }

        return response;
    }

    /**
     * Shows the error message of an exception thrown.
     * @param errorMsg error message of the exception thrown.
     */
    public String showError(String errorMsg) {
        return (errorMsg + "\n");
    }

}
