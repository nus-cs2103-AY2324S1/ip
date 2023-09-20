package noac.util;

import noac.task.Task;

import java.util.ArrayList;

/**
 * For interactions with the user such as printing or getting the input.
 */
public class Ui {

    /**
     * Print the welcome message.
     *
     * @return The message to be displayed to the user.
     */
    public static String showWelcomeMessage() {
        String returnMessage =
                "Hello! I'm NOAC\n" +
                "What can I do for you?\n";

        return returnMessage;
    }

    /**
     * Print the bye message.
     *
     * @return The message to be displayed to the user.
     */
    public String showByeMessage() {
        String returnMessage =  "Bye. Hope to see you again soon!\n" ;

        return returnMessage;
    }

    /**
     * Prints the error message.
     *
     * @param e The error which message is to be printed.
     * @return The message to be displayed to the user.
     */
    public String showErrorMessage(NoacException e) {
        return e.getMessage();
    }


    /**
     * Prints all the tasks in the list.
     *
     * @param taskList The list to be printed.
     * @return The message to be displayed to the user.
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
     * @return The message to be displayed to the user.
     */
    public String showMarkOrUnmark(Task task, boolean isMark) {
        String returnMessage = "";

        if (isMark) {
            returnMessage += "Nice! I've marked this task as done:\n";

        } else {
            returnMessage += "OK, I've marked this task as not done yet:\n";
        }

        returnMessage += task.toString();
        return returnMessage;
    }


    /**
     * Lets the user know the task has been added.
     *
     * @param task The task that was added.
     * @param listSize The number of task in the list.
     * @return The message to be displayed to the user.
     */
    public String showAddTask(Task task, int listSize) {
        String returnMessage =  "Got it. I've added this task:\n";
        returnMessage += task.toString() + "\n";
        returnMessage +=  "Now you have " + listSize + " tasks in the list.\n";

        return returnMessage;
    }


    /**
     * Lets the user know the task has been deleted.
     *
     * @param task The task that was added.
     * @param listSize The number of task in the list.
     * @return The message to be displayed to the user.
     */
    public String showDeleteTask(Task task, int listSize) {
        String returnMessage = "Noted. I've removed this task:\n";
        returnMessage += task.toString() + "\n";

        returnMessage += "Now you have " + listSize + " tasks in the list.\n";

        return returnMessage;
    }


    /**
     * Shows the task on that date.
     *
     * @param tasks The task to be displayed.
     * @return The message to be displayed to the user.
     */
    public String showTasksOnDate(ArrayList<Task> tasks) {
        String returnMessage =  "The tasks on this date are:\n";

        for(int i = 0 ; i < tasks.size(); i++) {
            returnMessage += tasks.get(i).toString() + "\n";
        }

        return returnMessage;
    }

    /**
     * Prints all the tasks that matched a word.
     *
     * @param tasks The task to be printed.
     * @return The message to be displayed to the user.
     */
    public String showFind(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            return "There are no matching tasks in your list. :(";
        }

        String returnMessage = "Here are the matching tasks in your list:\n";

        for(int i = 0 ; i < tasks.size(); i++) {
            returnMessage += tasks.get(i).toString() + "\n";
        }

        return returnMessage;
    }

    /**
     * Prints the task that has been tagged.
     *
     * @param task The task to be printed.
     * @param tag The tag to be printed.
     * @return The message to be displayed to the user.
     */
    public String showTagTask(Task task, String tag) {
        String returnMessage = task.toString() + " has been tagged with " + tag;

        return returnMessage;
    }
}
