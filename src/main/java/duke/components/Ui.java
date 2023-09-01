package duke.components;

import duke.tasks.Task;

import java.util.Scanner;

/**
 * Ui class which handles the inputs from the user and prints corresponding messages.
 */
public class Ui {
    /**
     * scanner contains the instance of a Scanner.
     */
    private Scanner scanner;

    /**
     * Constructor for the Ui class.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints the greeting message on initialisation of the bot.
     */
    public void greet() { // Greets user on initialisation
        System.out.println("Good day to you, I'm ButlerBot.\n" +
                "How may I be of service to you?\n");
    }

    /**
     * Prints the goodbye message on termination of the bot.
     */
    public void thank() { // Exits the Bot
        System.out.println("Goodbye and have a nice day.");
    }

    /**
     * Scans the next line for user input.
     */
    public String scan() {
        return scanner.nextLine();
    }

    /**
     * Prints the error message of error encountered.
     *
     * @param error Message of the error.
     */
    public void showError(String error) {
        System.err.println("Error: " + error);
    }

    /**
     * Prints the newly added task, along with the total number of tasks.
     *
     * @param task Details of the newly added task.
     * @param totalTasks Number of task in total.
     */
    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println("Understood, I have added the following task:");
        System.out.println(task);
        System.out.println("You now have " + totalTasks + " tasks in the list.");
    }

    /**
     * Print the task that is completed.
     *
     * @param task Details of the task that is completed.
     */
    public void showTaskCompleted(Task task) {
        System.out.println("Congratulations on finishing the task!");
        System.out.println("I will now mark it as complete:");
        System.out.println(task);
    }

    /**
     * Print the task that is unmarked.
     *
     * @param task Details of the task that is unmarked.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("No worries. I will now mark the task as incomplete:");
        System.out.println(task);
    }

    /**
     * Prints the newly deleted task, along with the total number of tasks.
     *
     * @param task Details of the deleted added task.
     * @param totalTasks Number of task in total.
     */
    public void showTaskDeleted(Task task, int totalTasks) {
        System.out.println("Understood, I will remove the following task from your list:");
        System.out.println(task);
        System.out.println("You now have " + totalTasks + " tasks remaining.");
    }

    /**
     * Shows list of tasks.
     *
     * @param taskList Contains list of tasks.
     * @throws DukeException Empty list.
     */
    public void showList(TaskList taskList) {
        System.out.println(taskList.list());
    }
}
