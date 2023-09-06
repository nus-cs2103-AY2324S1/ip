package duke.components;

import java.util.Scanner;

import duke.tasks.Task;

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
     * Returns the greeting message on initialisation of the bot.
     *
     * @return Greeting message.
     */
    public String greet() { // Greets user on initialisation
        return "Good day to you, I'm ButlerBot.\n"
                + "How may I be of service to you?\n";
    }

    /**
     * Returns the goodbye message on termination of the bot.
     *
     * @return Greeting message.
     */
    public String thank() { // Exits the Bot
        return "Goodbye and have a nice day.";
    }

    /**
     * Scans the next line for user input.
     *
     * @return User input as a String.
     */
    public String scan() {
        return scanner.nextLine();
    }

    /**
     * Prints the error message of error encountered.
     *
     * @param error Message of the error.
     * @return Error message input as a String.
     */
    public String showError(String error) {
        return "Error: " + error;
    }

    /**
     * Prints the newly added task, along with the total number of tasks.
     *
     * @param task Details of the newly added task.
     * @param totalTasks Number of task in total.
     * @return Newly added task and total number of tasks as a String.
     */
    public String showTaskAdded(Task task, int totalTasks) {
        return "Understood, I have added the following task:\n" + task
                + "\nYou now have " + totalTasks + " tasks in the list.";
    }

    /**
     * Print the task that is completed.
     *
     * @param task Details of the task that is completed.
     * @return Completed task as a String.
     */
    public String showTaskCompleted(Task task) {
        return "Congratulations on finishing the task!\n" + "I will now mark it as complete:\n" + task;
    }

    /**
     * Print the task that is unmarked.
     *
     * @param task Details of the task that is unmarked.
     * @return Unmark task and return task as a String.
     */
    public String showTaskUnmarked(Task task) {
        return "No worries. I will now mark the task as incomplete:\n" + task;
    }

    /**
     * Prints the newly deleted task, along with the total number of tasks.
     *
     * @param task Details of the deleted added task.
     * @param totalTasks Number of task in total.
     * @return Deleted task as a String.
     */
    public String showTaskDeleted(Task task, int totalTasks) {
        return "Understood, I will remove the following task from your list:\n" + task
                + "\nYou now have " + totalTasks + " tasks remaining.";
    }

    /**
     * Shows list of tasks.
     *
     * @param taskList Contains list of tasks.
     * @return List of tasks as a String.
     * @throws DukeException Empty list.
     */
    public String showList(TaskList taskList) {
        return taskList.list();
    }

    /**
     * Prints the String representation of a filtered list.
     *
     * @param list String representation of a filtered list.
     * @return Filtered list as a String.
     */
    public String showFilteredList(String list) {
        return list;
    }
}
