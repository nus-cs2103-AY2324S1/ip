package noac.util;

import noac.task.Task;

import java.util.ArrayList;

/**
 * For interactions with the user such as printing or getting the input.
 */
public class Ui {

    /**
     * Print the welcome message.
     */
    public static String showWelcomeMessage() {

        String welcomeMessage =
                "Hello! I'm NOAC\n" +
                "What can I do for you?\n";

        return welcomeMessage;
    }


    /**
     * Print the bye message.
     */
    public String showByeMessage() {
        String byeMessage =  "Bye. Hope to see you again soon!\n" ;

        return byeMessage;
    }

    /**
     * Prints the error message.
     *
     * @param e The error which message is to be printed.
     */
    public String showErrorMessage(NoacException e) {
        return e.getMessage();
    }


    /**
     * Prints all the tasks in the list.
     *
     * @param taskList The list to be printed.
     */
    public String showList(TaskList taskList) {
        String returnMessage =  "Here are the tasks in your list:\n";

        for (int i = 1; i <= taskList.size(); i++) {
            returnMessage +=  i + "." + taskList.getTask(i-1).toString() + "\n";
        }

        return returnMessage;
    }

    /**
     * Lets the user know the task has been mark/unmark.
     *
     * @param task The task to be mark/unmark.
     * @param isMark Boolean to determine whether to mark or unmark.
     */
    public String showMarkOrUnmark(Task task, boolean isMark) {
        String returnMessage = "";

        if (isMark) {
            returnMessage += "Nice! I've marked this task as done:\n";

        } else {
            System.out.println("     OK, I've marked this task as not done yet:");
        }

        returnMessage += task.toString();
        return returnMessage;
    }


    /**
     * Lets the user know the task has been added.
     *
     * @param task The task that was added.
     * @param listSize The number of task in the list.
     */
    public String showAddTask(Task task, int listSize) {
        String returnMessage = "";

        returnMessage += "Got it. I've added this task:\n";
        returnMessage += task.toString() + "\n";
        returnMessage +=  "Now you have " + listSize + " tasks in the list.\n";

        return returnMessage;
    }


    /**
     * Lets the user know the task has been deleted.
     *
     * @param task The task that was added.
     * @param listSize The number of task in the list.
     */
    public String showDeleteTask(Task task, int listSize) {
        String returnMessage = "";
        returnMessage += "Noted. I've removed this task:\n";
        returnMessage += task.toString() + "\n";

        returnMessage += "Now you have " + listSize + " tasks in the list.\n";

        return returnMessage;
    }


    /**
     * Shows the task on that date.
     *
     * @param tasks The task to be displayed.
     */
    public String showTasksOnDate(ArrayList<Task> tasks) {
        String returnMessage = "";
        returnMessage += "The tasks on this date are:\n";

        for(int i = 0 ; i < tasks.size(); i++) {
            returnMessage += tasks.get(i).toString() + "\n";
        }

        return returnMessage;
    }

    /**
     * Prints all the tasks that matched a word.
     *
     * @param tasks The task to be printed.
     */
    public String showFind(ArrayList<Task> tasks) {
        String returnMessage = "";

        if (tasks.size() == 0) {
            returnMessage += "There are no matching tasks in your list. :(\n";
        } else {
            returnMessage += "Here are the matching tasks in your list:\n";

            for(int i = 0 ; i < tasks.size(); i++) {
                returnMessage += tasks.get(i).toString() + "\n";
            }
        }
        return returnMessage;
    }
}
