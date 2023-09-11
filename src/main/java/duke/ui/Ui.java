package duke.ui;

import java.util.List;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.util.TaskList;



/**
 * Encapsulates the user interface of the application.
 * It provides methods to print various messages to the user.
 */
public class Ui {
    private static final String LOGO = ",------.,--.              ,--.  \n"
            + "|  .---\'|  |,-.,--.,--. ,-|  |  \n"
            + "|  `--, |     /|  ||  |' .-. |   \n"
            + "|  `---.|  \\\\  \\\\  ''  '\\\\ `-\'   \n"
            + "`------'`--'`--'`----'  `---' \n";
    private static final String LINE = "-".repeat(60);

    /**
     * Prints the welcome message to the user.
     */
    public void printWelcomeMessage() {
        System.out.println(LINE);
        System.out.println("Hello! I'm \n");
        System.out.println(LOGO);
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Returns the welcome message to the user.
     *
     * @return The welcome message as a String.
     */
    public String displayWelcomeMessage() {
        return "Hello! I'm \n" + LOGO + "What can I do for you?";
    }

    /**
     * Prints the farewell message to the user.
     */
    public void printFarewellMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * Returns the farewell message to the user.
     *
     * @return The farewell message as a String.
     */
    public String displayFarewellMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints confirmation when user successfully adds task to the list of tasks.
     *
     * @param task The Newly added task
     * @param tasks The updated list of tasks
     */
    public void printAddedTaskConfirmation(Task task, TaskList tasks) {
        assert task != null : "Task should not be null";
        assert tasks != null : "List of tasks should not be null";
        System.out.println(LINE);
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Returns confirmation when user successfully adds task to the list of tasks.
     *
     * @param task The Newly added task
     * @param tasks The updated list of tasks
     * @return The confirmation message as a String.
     */
    public String displayAddedTaskConfirmation(Task task, TaskList tasks) {
        assert task != null : "Task should not be null";
        assert tasks != null : "List of tasks should not be null";
        return "Got it. I've added this task: \n" + "  " + task + "\n" + "Now you have " + tasks.size()
                + " tasks in the list.";
    }

    /**
     * Prints confirmation when user successfully deletes task from the list of tasks.
     *
     * @param task The deleted task
     * @param tasks The updated list of tasks
     */
    public void printDeletedTaskConfirmation(Task task, TaskList tasks) {
        assert task != null : "Task should not be null";
        assert tasks != null : "List of tasks should not be null";
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task: ");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Returns confirmation when user successfully deletes task from the list of tasks.
     *
     * @param task The deleted task
     * @param tasks The updated list of tasks
     * @return The confirmation message as a String.
     */
    public String displayDeletedTaskConfirmation(Task task, TaskList tasks) {
        assert task != null : "Task should not be null";
        assert tasks != null : "List of tasks should not be null";
        return "Noted. I've removed this task: \n" + "  " + task + "\n" + "Now you have " + tasks.size()
                + " tasks in the list.";
    }

    /**
     * Prints confirmation when user successfully marks task as done.
     *
     * @param task The marked task
     */
    public void printMarkedTaskConfirmation(Task task) {
        assert task != null : "Task should not be null";
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  " + task);
        System.out.println(LINE);
    }

    public String displayMarkedTaskConfirmation(Task task) {
        assert task != null : "Task should not be null";
        return "Nice! I've marked this task as done: \n" + "  " + task;
    }

    /**
     * Prints the error message to the user.
     *
     * @param e The exception that was thrown.
     */
    public void printErrorMessage(DukeException e) {
        System.out.println(LINE);
        System.out.println("OOPS!!! " + e.getMessage());
        System.out.println(LINE);
    }

    /**
     * Returns the error message to the user.
     *
     * @param e The exception that was thrown.
     * @return The error message as a String.
     */
    public String displayErrorMessage(DukeException e) {
        return "OOPS!!! " + e.getMessage();
    }

    /**
     * Prints the list of tasks to the user.
     *
     * @param tasks The list of tasks.
     */
    public void printList(List<Task> tasks) {
        assert tasks != null : "List of tasks should not be null";
        System.out.println(LINE);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println(LINE);
    }

    /**
     * Returns the list of tasks to the user.
     *
     * @param tasks
     * @return The list of tasks as a String.
     */
    public String displayList(List<Task> tasks) {
        assert tasks != null : "List of tasks should not be null";
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            result += (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return result;
    }

    /**
     * Prints the header string before the displayed search result.
     */
    public void printFindMessage() {
        System.out.println(LINE);
        System.out.println("Here are the matching tasks in your list:");
    }

    /**
     * Returns a header string before the displayed search result.
     *
     * @return The header of search result as a String.
     */
    public String displayFindMessage() {
        return "Here are the matching tasks in your list:";
    }
}
