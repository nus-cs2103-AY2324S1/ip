package emiya.ui;

import emiya.task.Task;
import emiya.task.TaskList;

/**
 * A class containing messages that should be displayed to the user.
 */
public class Ui {

    public static final String WELCOME_MESSAGE = "Hello! I'm Emiya\n"
            + "What can I do for you?\n";

    public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!\n";

    public static final String UBW = "Unknown to death nor known to life\n";

    public static final String DEAD = "People die if they are killed!\n";


    public Ui() {

    }

    /**
     * Returns a string that indicates that a task has been added to the task list.
     * Used when the task list only contains one task.
     * @param task The task to be displayed.
     * @param taskList The task list containing all tasks.
     * @return A String that indicates that a task has been added to the task list.
     */
    public String addedSingularMessage(Task task, TaskList taskList) {
        return "Sure! I have added this task to the list:\n" + task + "\n"
                + "Now you have " + taskList.size() + " task in your list!\n";
    }

    /**
     * Returns a string that indicates that a task has been added to the task list.
     * Used when the task list contains anything other than one task.
     * @param task The task to be displayed.
     * @param taskList The task list containing all tasks.
     * @return A String that indicates that a task has been added to the task list.
     */
    public String addedPluralMessage(Task task, TaskList taskList) {
        return "Sure! I have added this task to the list:\n" + task + "\n"
                + "Now you have " + taskList.size() + " tasks in your list!\n";
    }
    /**
     * Returns a string that indicates that a task has been deleted from the task list.
     * Used when the task list only contains one task.
     * @param task The task to be displayed.
     * @param taskList The task list containing all tasks.
     * @return A String that indicates that a task has been added to the task list.
     */
    public String deletedSingularMessage(Task task, TaskList taskList) {
        return "Sure, I shall now delete the following task:\n" + task + "\n"
                + "Now you have " + taskList.size() + " task in your list!\n";
    }

    /**
     * Returns a string that indicates that a task has been deleted from the task list.
     * Used when the task list contains anything other than one task.
     * @param task The task to be displayed.
     * @param taskList The task list containing all tasks.
     * @return A String that indicates that a task has been added to the task list.
     */
    public String deletedPluralMessage(Task task, TaskList taskList) {
        return "Sure, I shall now delete the following task:\n" + task + "\n"
                + "Now you have " + taskList.size() + " tasks in your list!\n";
    }

    /**
     * Returns a string that indicates that a task has been marked.
     * @param position The position the task that was marked is at.
     * @param taskList The task list containing all tasks.
     * @return A String that indicates that a task has been marked.
     */
    public String markedMessage(int position, TaskList taskList) {
        return "Nice job! I have marked this task as done:\n" + taskList.get(position - 1) + "\n";
    }

    /**
     * Returns a string that indicates that a task has been unmarked.
     * @param position The position the task that was marked is at.
     * @param taskList The task list containing all tasks.
     * @return A String that indicates that a task has been unmarked.
     */
    public String unmarkedMessage(int position, TaskList taskList) {
        return "Oof, alright I have set this task as unmarked:\n" + taskList.get(position - 1) + "\n";
    }


}
