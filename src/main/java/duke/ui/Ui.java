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
    private final String LOGO = ",------.,--.              ,--.  \n"
            + "|  .---\'|  |,-.,--.,--. ,-|  |  \n"
            + "|  `--, |     /|  ||  |' .-. |   \n"
            + "|  `---.|  \\\\  \\\\  ''  '\\\\ `-\'   \n"
            + "`------'`--'`--'`----'  `---' \n";
    private final String LINE = "-".repeat(60);

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
     * Prints the farewell message to the user.
     */
    public void printFarewellMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * Prints confirmation when user successfully adds task to the list of tasks.
     *
     * @param task The Newly added task
     * @param tasks The updated list of tasks
     */
    public void printAddedTaskConfirmation(Task task, TaskList tasks) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Prints confirmation when user successfully deletes task from the list of tasks.
     *
     * @param task The deleted task
     * @param tasks The updated list of tasks
     */
    public void printDeletedTaskConfirmation(Task task, TaskList tasks) {
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task: ");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Prints confirmation when user successfully marks task as done.
     *
     * @param task The marked task
     */
    public void printMarkedTaskConfirmation(Task task) {
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  " + task);
        System.out.println(LINE);
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
     * Prints the list of tasks to the user.
     *
     * @param tasks The list of tasks.
     */
    public void printList(List<Task> tasks) {
        System.out.println(LINE);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println(LINE);
    }

    /**
     * Prints the message to the user when the find command is executed.
     */
    public void printFindMessage() {
        System.out.println(LINE);
        System.out.println("Here are the matching tasks in your list:");
    }
}
