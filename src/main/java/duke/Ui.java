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

    public void showLine() {
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Generates and prints the welcome message upon the start of the application.
     */
    public void showWelcome() {
        System.out.println(
                "____________________________________________________________\n"
                        + "Hello! I'm ET\n"
                        + "What can I do for you?\n"
                        + "____________________________________________________________\n"
        );
    }

    /**
     * Generates and prints the exit message upon the termination of the application.
     */
    public void showExit() {
        System.out.println("____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n"
        );
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
    public void showMarked(Task task) {
        System.out.println("____________________________________________________________\n"
                + "Nice! I've marked this task as done:\n"
                + task + "\n"
                + "____________________________________________________________\n"
        );
    }

    /**
     * Shows the unmarked task
     * @param task task that was marked.
     */
    public void showUnmarked(Task task) {
        System.out.println("____________________________________________________________\n"
                + "OK, I've marked this task as not done yet:\n"
                + task + "\n"
                + "____________________________________________________________\n"
        );
    }

    /**
     * Shows task added to task list.
     * @param task task added to task list
     * @param num number of task in task list.
     */
    public void showTaskAdded(Task task, int num) {
        System.out.println("____________________________________________________________\n"
                + "Got it. I've added this task:\n"
                + task + "\n"
                + "Now you have " + num + " tasks in the list.\n"
                + "____________________________________________________________\n"
        );
    }

    /**
     * Shows the task deleted from task list
     * @param task task deleted from task list
     * @param num number of task in task list
     */
    public void showDeleted(Task task, int num) {
        System.out.println("____________________________________________________________\n"
                + "Noted. I've removed this task:\n"
                + task + "\n"
                + "Now you have " + num + " tasks in the list.\n"
                + "____________________________________________________________\n"
        );
    }

    /**
     * Shows the list of tasks
     * @param tasks list of tasks
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");

        for (int i = 1; i <= tasks.total(); i++) {
            Task t = tasks.get(i - 1);
            System.out.println(i + ". " + t.toString());
        }
    }

    /**
     * Shows the error message of an exception thrown.
     * @param errorMsg error message of the exception thrown.
     */
    public void showError(String errorMsg) {
        System.out.println("____________________________________________________________\n"
                + errorMsg + "\n"
                + "____________________________________________________________\n"
        );
    }

}
