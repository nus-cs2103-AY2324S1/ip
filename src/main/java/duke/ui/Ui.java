package duke.ui;

import java.util.Scanner;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Handles the interaction with the user.
 */
public class Ui {

    /**
     * Initialises the Ui.
     */
    public Ui() {
    }

    /**
     * Returns message for invalid command.
     * 
     * @return The message for invalid command.
     */
    public String showInvalidCommandMessage() {
        return "Do not test my patience, mortal. Speak clearly.";
    }

    /**
     * Returns the welcome message.
     * 
     * @return The welcome message.
     */
    public String showStartMessage() {
        return "Greetings, puny mortal. This is Kronos, The Lord of Time. \nWhat foolish errand do you seek to accomplish with my immense powers?";
    }

    /**
     * Shows the error message.
     * 
     * @param errorMessage The error message to be printed.
     * @return The error message.
     */
    public String showError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Prints the list of tasks.
     * 
     * @param taskList  The list of tasks to be printed.
     * @param searching Whether the list is for a search (true for search).
     */
    public String listTasks(TaskList taskList, boolean searching) {
        String output = "";
        if (searching) {
            output += "The following tasks match what you seek:\n";
        } else {
            output += "You have somehow found the audacity to conjure up this laughable list of inconsequential endeavours:\n";
        }
        for (int i = 1; i <= taskList.getSize(); i++) {
            output += (i + ". " + taskList.getTask(i - 1) + "\n");
        }
        return output;
    }

    /**
     * Returns the message for when a task is marked as done.
     * 
     * @param task The task that was marked as done.
     */
    public String showMarkTaskMessage(Task task) {
        return "Astonishingly enough, you have managed to triumph over this mind-bogglingly simple task:\n\n"
                + task.toString();
    }

    /**
     * Returns the message for when a task is unmarked.
     * 
     * @param task The task that was unmarked.
     * @return The message for when a task is unmarked.
     */
    public String showUnmarkTaskMessage(Task task) {
        return "You have somehow managed to fail this mind-bogglingly simple task:\n\n" + task.toString();
    }

    /**
     * Returns the message for when a todo task is added.
     * 
     * @param task The todo task that was added.
     * @return The message for when a todo task is added.
     */
    public String showTodoMessage(Task task) {
        return "This task has been reluctantly bestowed upon your ever-growing list:\n\n" + task.toString();
    }

    /**
     * Returns the message for when a deadline task is added.
     * 
     * @param task The deadline task that was added.
     * @return The message for when a deadline task is added.
     */
    public String showDeadlineMessage(Task task) {
        return "With your constant mediocrity, it is entirely unlikely that you will be able to meet this deadline I have just added: \n\n"
                + task.toString();
    }

    /**
     * Returns the message for when an event task is added.
     * 
     * @param task The event task that was added.
     * @return The message for when an event task is added.
     */
    public String showEventMessage(Task task) {
        return "Looks like I will have to slow time down myself if you wish to make it to this event I just added:\n\n + "
                + task.toString();
    }

    /**
     * Returns the message for when a task is deleted.
     * 
     * @param task The task that was deleted.
     * @return The message for when a task is deleted.
     */
    public String showDeleteMessage(Task task) {
        return "One less annoyance to plague your feeble list. This task has been banished:\n\n" + task.toString();
    }

    /**
     * Returns the end message.
     */
    public String showEndMessage() {
        return "Is that all? I have better things to do than to listen to lesser beings. Farewell.";
    }

    /**
     * Returns the message for when the task list size changes.
     * 
     * @param size    The new size of the task list.
     * @param growing Whether a task was added or removed (true for added).
     * @return The message for when the task list size changes.
     */
    public String showTaskListSizeMessage(int size, boolean growing) {
        if (growing) {
            return "Congratulations, your pile of tasks has swelled to a whopping " + size + ".";
        } else {
            return "Your pile of tasks has shrunk to a measly " + size + ".";
        }
    }
}
