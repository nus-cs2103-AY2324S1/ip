package duke.util;

import java.util.ArrayList;

import duke.task.Task;

/**
 * UI class that handles interaction with the user.
 */
public class Ui {
    /**
     * Prints complete message when action is carried out.
     * @param message message to be displayed
     */
    public String showComplete(String message) {
        return message;
    }

    /**
     * Returns response message when task has been marked.
     * @param task task that was marked
     * @return mark message
     */
    public String showMarked(Task task) {
        return "Nice! I've Marked this task as done:" + "\n" + task;
    }

    /**
     * Returns response message when task has been unmarked.
     * @param task task that was unmarked
     * @return unmark message
     */
    public String showUnmarked(Task task) {
        return "OK, I've marked this task as not done yet:" + "\n" + task;
    }

    /**
     * Returns an error when fail to load file data onto the screen.
     * @return error message to be displayed
     */
    public String showLoadingError() {
        return "Error loading data! There is no file found with the given filepath!";
    }

    /**
     * Static method to display welcome method.
     * @return welcome message to be displayed
     */
    public static String showWelcome() {
        return "Hello! I'm B055man\n" + " What can I do for you today?";
    }

    /**
     * Returns the response after a task has been added.
     * @param task task that was added
     * @param count current number of tasks in the tasklist
     * @return string containing response message
     */
    public String showTaskAdded(Task task, int count) {
        return "Got it. I've added this task:" + "\n" + "  " + task.toString() + "\n . "
                + "Now you have " + (count) + " tasks in the list.";
    }

    /**
     * Returns the list of tasks to be displayed on the ui.
     * @param tasks Arraylist of string of formatted tasks to be displayed
     */
    public String showTasks(ArrayList<String> tasks) {
        assert tasks != null : "should display no tasks instead of calling this method";
        int count = 1;
        String s = "";
        for (String task : tasks) {
            s += count + ". " + task + "\n";
            count++;
        }
        return s;
    }

    /**
     * Returns goodbye message to be displayed.
     * @return message to be displayed
     */
    public String showGoodbye() {
        return "Bye. Hope to See you again soon!";
    }

    public String showNewFile() {
        return "Its alright, new file was created to store your data.";
    }
}
