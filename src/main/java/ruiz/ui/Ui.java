package ruiz.ui;

import java.util.ArrayList;

import ruiz.task.Task;

/**
 * Deals with the interactions with the user.
 */
public class Ui {
    /**
     * This method returns the greeting message of the bot.
     *
     * @return greeting message of the bot.
     */
    public String greet() {
        String greet = " Hello! I'm Ruiz\n"
                + " What can I do for you?\n";
        return greet;
    }

    /**
     * This method returns the goodbye message of the bot.
     *
     * @return goodbye message of the bot.
     */
    public String printBye() {
        String bye = "Bye! Comeback soon!\n";
        return bye;
    }

    /**
     * This method returns the list of tasks currently
     *
     * @return list of tasks currently.
     */
    public String getTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "You have no tasks in your list\n";
        }
        String message = "";
        for (int i = 0; i < tasks.size(); i++) {
            message += (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return message;
    }

    /**
     * This method returns a message when the task is unable to be saved.
     *
     * @return message when the task is unable to be saved.
     */
    public String unableToSaveTask() {
        return "Unable to save task";
    }

    /**
     * This method returns the message when the input is in the wrong format.
     *
     * @return message when the input is in the wrong format.
     */
    public String wrongFormat() {
        return "Please input your date and time in the correct format: yyyy-MM-dd HHmm";
    }

    /**
     * This method returns the message that the bot prints when the input cannot be understood.
     *
     * @return error message
     */
    public String botErrorMsg() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    /**
     * Prints an acknowledgement message that a new task has been added.
     *
     * @param task         task that is added.
     * @param taskListSize size of the task list after this task is added.
     * @return acknowledgement message.
     */
    public String addedNewTaskMsg(Task task, int taskListSize) {
        String message = "Got it. I've added this task:\n"
                + task
                + "\nNow you have "
                + taskListSize
                + " in the list.\n";
        return message;
    }

    /**
     * Prints an acknowledgement message that the task has been deleted.
     *
     * @param task         task that is deleted.
     * @param taskListSize size of the task list after the task is deleted.
     * @return acknowledgement message that the task has been deleted.
     */
    public String deletedTask(Task task, int taskListSize) {
        String message = "Noted. I've removed this task:\n"
                + task + "\n" + "Now you have "
                + taskListSize
                + " in the list.\n";
        return message;
    }

    /**
     * Prints an acknowledgement message that the task has been unmarked.
     *
     * @param task task that is marked.
     * @return acknowledgement message that the task has been unmarked.
     */
    public String unmarkTask(Task task) {
        String message = "OK, I've marked this task as not done yet\n"
                + task
                + "\n";
        return message;
    }

    /**
     * Prints an acknowledgement message that the task has been marked.
     *
     * @param task task that is marked.
     * @return acknowledgement message that the task has been marked.
     */
    public String markTask(Task task) {
        String message = "Nice! I've marked this task as done\n"
                + task
                + "\n";
        return message;
    }
}
