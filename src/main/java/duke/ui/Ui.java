package duke.ui;

import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the user interface of the Duke chatbot.
 */
public class Ui {

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads a command entered by the user.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Displays a horizontal line separator.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void showError(String errorMessage) {
        System.out.println("☹ " + errorMessage);
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }


    /**
     * Displays a message indicating matching tasks found based on the keyword search.
     *
     * @param matchingTasks The list of matching tasks to display.
     */
    public void showMatchingTasks(TaskList matchingTasks) throws DukeException {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println((i + 1) + "." + matchingTasks.get(i));
        }
    }

    /**
     * Displays a message indicating no matching tasks were found based on the keyword search.
     */
    public void showNoMatchingTasks() {
        System.out.println("No matching tasks found.");
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     * @throws DukeException If an error occurs while displaying the tasks.
     */
    public void showTaskList(TaskList tasks) throws DukeException {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Displays a message indicating that a task has been added to the list.
     *
     * @param tasks The list of tasks.
     * @throws DukeException If an error occurs while displaying the message.
     */
    public void showTaskAddedMessage(TaskList tasks) throws DukeException {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param tasks The list of tasks.
     * @param taskIndex The index of the task that has been marked as done.
     * @throws DukeException If an error occurs while displaying the message.
     */
    public void showTaskMarkedAsDone(TaskList tasks, int taskIndex) throws DukeException {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskIndex));
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param tasks The list of tasks.
     * @param taskIndex The index of the task that has been marked as not done.
     * @throws DukeException If an error occurs while displaying the message.
     */
    public void showTaskMarkedAsUndone(TaskList tasks, int taskIndex) throws DukeException {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(taskIndex));
    }

    /**
     * Displays a message indicating that a task has been removed from the list.
     *
     * @param tasks The list of tasks.
     * @param removedTask The task that has been removed from the list.
     */
    public void showTaskRemoved(TaskList tasks, Task removedTask) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(removedTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Displays an error message indicating that tasks could not be loaded from a file.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }
}
