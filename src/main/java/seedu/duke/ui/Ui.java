package seedu.duke.ui;

import java.util.ArrayList;

import seedu.duke.exceptions.InvalidTaskIndexException;
import seedu.duke.tasklist.TaskList;
import seedu.duke.tasks.Task;

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
     * @return String of the welcome message
     */
    public String welcomeMessage() {
        return ("Hello! I'm Lemon!" + System.lineSeparator() + "What can I do for you?");
    }

    /**
     * Displays an error message when there is an issue loading the storage file with tasks.
     * @return String of the error message.
     */
    public String showLoadingError() {
        return("Error loading storage file!");
    }

    /**
     * Displays a message after marking a task as done.
     *
     * @param toMarkTask The task that was marked as done.
     * @param tasks The TaskList object containing the task list.
     * @return String of the message to display indicating task is completed.
     */
    public String markPrint(String toMarkTask, TaskList tasks) {
        String output = "";
        output += ("Nice! I've marked this task as done: " + System.lineSeparator() + toMarkTask + "\n");
        output += ("Now you have " + tasks.getTasksSize() + " tasks in the list.\n");
        return output;
    }

    /**
     * Displays a message after unmarking a task as undone.
     *
     * @param toUnmarkTask The task that was marked as undone.
     * @param tasks The TaskList object containing the task list.
     *
     */
    public String unmarkPrint(String toUnmarkTask, TaskList tasks) {
        String output = "";
        output += ("OK, I've marked this task as not done yet: " + System.lineSeparator() + toUnmarkTask + "\n");
        output += ("Now you have " + tasks.getTasksSize() + " tasks in the list.\n");
        return output;
    }

    /**
     * Displays a message after deleting a task.
     *
     * @param toDeleteTask The task that was deleted.
     * @param tasks The TaskList object containing the task list without the deleted task.
     */
    public String deletePrint(String toDeleteTask, TaskList tasks) {
        String output = "";
        output += ("Noted. I've removed this task:" + System.lineSeparator() + toDeleteTask + System.lineSeparator());
        output += ("Now you have " + tasks.getTasksSize() + " tasks in the list.\n");
        return output;
    }

    /**
     * Displays a message after adding a task.
     *
     * @param toAddTask The task that was added.
     * @param tasks The TaskList object containing the task list with the added task.
     */
    public String addTasks(String toAddTask, TaskList tasks) {
        String output = "";
        output += ("Got it. I've added this task: " + toAddTask);
        output += System.lineSeparator();
        output += ("Now you have " + tasks.getTasksSize() + " tasks in the list." + System.lineSeparator());
        return output;
    }

    /**
     * Lists all tasks in the task list.
     *
     * @param tasks The TaskList object containing the task list.
     * @return String of all the tasks in the TaskList.
     */
    public String listAll(TaskList tasks) throws InvalidTaskIndexException {
        String output = "";
        try {
            for (int i = 0; i < tasks.getTasksSize(); i++) {
                output += (i + 1 + ". " + tasks.getTask(i).toString() + System.lineSeparator());
            }
            output += System.lineSeparator();
            return output;
        } catch (InvalidTaskIndexException e) {
            throw new InvalidTaskIndexException("Invalid task index");
        }
    }

    /**
     * Lists all tasks that matches the keyword in the task list.
     *
     * @param tasks The TaskList object containing the task list.
     * @return String of the list of tasks which matches the keyword.
     */
    public String listMatching(ArrayList<Task> tasks) {
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            output += (i + 1 + ". " + tasks.get(i).toString() + System.lineSeparator());
        }
        output += System.lineSeparator();
        return output;
    }

    /**
     * Displays a farewell message when the user exits the application.
     */
    public String bye() {
        return ("Bye. Hope to see you again soon!");
    }

}
