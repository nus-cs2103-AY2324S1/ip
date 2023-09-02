package seedu.duke.ui;

import seedu.duke.Exceptions.InvalidTaskIndexException;
import seedu.duke.Exceptions.KeywordNotFoundException;
import seedu.duke.Tasks.Task;
import seedu.duke.Tasks.Task;
import seedu.duke.tasklist.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Ui class is responsible for handling user interface interactions and displaying messages to the user.
 */
public class Ui {

    /**
     * Constructs a new Ui object.
     */

    public Ui() {

    }

    /**
     * Displays a welcome message when the application starts.
     */
    public void welcomeMessage() {
        System.out.println("Hello! I'm Lemon!" + "\nWhat can I do for you?");
    }

    /**
     * Displays an error message when there is an issue loading the storage file with tasks.
     */
    public void showLoadingError() {
        System.out.println("Error loading storage file!");
    }

    /**
     * Displays a message after marking a task as done.
     *
     * @param toMarkTask The task that was marked as done.
     * @param tasks      The TaskList object containing the task list.
     */
    public void markPrint(String toMarkTask, TaskList tasks) {
        System.out.println("Nice! I've marked this task as done:\n " + toMarkTask + "\n");
        System.out.println("Now you have " + tasks.getTasksSize() + " tasks in the list.\n");
    }

    /**
     * Displays a message after unmarking a task as undone.
     *
     * @param toUnmarkTask The task that was marked as undone.
     * @param tasks        The TaskList object containing the task list.
     */
    public void unmarkPrint(String toUnmarkTask, TaskList tasks) {
        System.out.println("OK, I've marked this task as not done yet:\n " + toUnmarkTask + "\n");
        System.out.println("Now you have " + tasks.getTasksSize() + " tasks in the list.\n");
    }

    /**
     * Displays a message after deleting a task.
     *
     * @param toDeleteTask The task that was deleted.
     * @param tasks        The TaskList object containing the task list without the deleted task.
     */
    public void deletePrint(String toDeleteTask, TaskList tasks) {
        System.out.println("Noted. I've removed this task:\n" + toDeleteTask);
        System.out.println("Now you have " + tasks.getTasksSize() + " tasks in the list.\n");
    }

    /**
     * Displays a message after adding a task.
     *
     * @param toAddTask The task that was added.
     * @param tasks     The TaskList object containing the task list with the added task.
     */
    public void addTasks(String toAddTask, TaskList tasks) {
        System.out.println("Got it. I've added this task: " + toAddTask);
        System.out.println("Now you have " + tasks.getTasksSize() + " tasks in the list.\n");
    }

    /**
     * Lists all tasks in the task list.
     *
     * @param tasks The TaskList object containing the task list.
     */
    public void listAll(TaskList tasks) throws InvalidTaskIndexException {
        try {
            for (int i = 0; i < tasks.getTasksSize(); i++) {
                System.out.println(i + 1 + ". " + tasks.getTask(i).toString());
            }
            System.out.println("\n");
        } catch (InvalidTaskIndexException e) {
            throw new InvalidTaskIndexException("Invalid task index");
        }
    }

    public void listMatching(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i).toString());
        }
        System.out.println("\n");
    }

    /**
     * Displays a farewell message when the user exits the application.
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

}
