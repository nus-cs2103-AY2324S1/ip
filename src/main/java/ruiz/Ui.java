package ruiz;

import java.util.ArrayList;

import ruiz.task.Task;

/**
 * Deals with the interactions with the user.
 */
public class Ui {
    /**
     * This method prints the greeting message of the bot.
     */
    public String greet() {
        String greet = " Hello! I'm Ruiz\n"
                + " What can I do for you?\n";
        return greet;
    }

    /**
     * This method prints the goodbye message of the bot.
     */
    public String printBye() {
        String bye = "Bye! Comeback soon!\n";
        return bye;
    }

    /**
     * This method prints out the list of tasks currently
     */
    public String getTasks(ArrayList<Task> tasks) {
        String message = "";
        for (int i = 0; i < tasks.size(); i++) {
            message += (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return message;
    }

    /**
     * This method prints out the message when the bot is unable to save the task
     */
    public String unableToSaveTask() {
        return "Unable to save task";
    }

    /**
     * This method prints out the message when the input is in the wrong format.
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
     * This method prints a message that it is unable to read the input file.
     */
    public void unableToLoadFile() {
        System.out.println("____________________________________________________________\n"
                + "There is no pre-existing list\n"
                + "____________________________________________________________");
    }

    /**
     * Prints an acknowledgement message that a new task has been added.
     *
     * @param task         task that is added.
     * @param taskListSize size of the task list after this task is added.
     */
    public String addedNewTaskMsg(Task task, int taskListSize) {
        String message = "Got it. I've added this task:\n"
                + task
                + "\nNow you have " + taskListSize + " in the list.\n";
        return message;
    }

    /**
     * Prints an acknowledgement message that the task has been deleted.
     *
     * @param task         task that is deleted.
     * @param taskListSize size of the task list after the task is deleted.
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
     * @param task
     */
    public String markTask(Task task) {
        String message = "Nice! I've marked this task as done\n"
                + task
                + "\n";
        return message;
    }
}
