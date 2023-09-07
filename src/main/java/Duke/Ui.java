package duke;

import java.util.ArrayList;

/**
 * UI class that contains strings to be printed to the user.
 */
public class Ui {

    /**
     * Returns a greeting.
     * @return a greeting.
     */
    public static String greet() {
        return ("Hello! I'm Gman! \nWhat can I do for you?");
    }

    /**
     * Returns a goodbye message.
     * @return a goodbye message.
     */
    public static String goodbye() {
        return ("Bye. Hope to see you again soon!");
    }

    /**
     * Returns a list of all tasks in a String.
     * @param taskList the Task List containing all tasks.
     * @return a list of all tasks in a String.
     */
    public static String listTasks(TaskList taskList) {
        if (taskList.getSize() == 0) {
            showError(new GmanException("There's nothing to print in the list"));
        }
        String listOfTasks = ("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.getSize(); i++) {
            listOfTasks += ((i + 1) + ". "  + taskList.getTask(i).toString() + "\n");
        }
        return listOfTasks;
    }

    /**
     * Returns a String stating how many tasks there are in the current tasklist.
     * @param taskList the Task List containing all tasks.
     * @return a String stating how many tasks there are in the current tasklist.
     */
    public static String numberOfTasks(TaskList taskList) {
        if (taskList.getSize() == 0) {
            return ("There are no tasks in the list!");
        } else if (taskList.getSize() == 1) {
            return ("Now you have 1 task in the list.");
        } else {
            return ("Now you have " + taskList.getSize() + " tasks in the list.");
        }
    }

    /**
     * Returns a message stating the task has been added and the task description.
     * @param task The task to be added.
     * @return a message stating the task has been added and the task description.
     */
    public static String addedTask(Task task) {
        String message = "Got it. I've added this task:\n" + task.toString();
        return message;
    }

    /**
     * Returns a message stating the task has been removed and the task description.
     * @param task The task to be removed
     * @return a message stating the task has been removed and the task description.
     */
    public static String removedTask(Task task) {
        String message = "Noted. I've removed this task:\n" + task.toString();
        return message;
    }

    /**
     * Returns a message stating the task has been marked and the task description.
     * @param taskToString The task description.
     * @return a message stating the task has been marked and the task description.
     */
    public static String mark(String taskToString) {
        String message = "Nice! I've marked this task as done:\n" + taskToString;
        return message;
    }

    /**
     * Returns a message stating the task has been unmarked and the task description.
     * @param taskToString The task description.
     * @return a message stating the task has been unmarked and the task description.
     */
    public static String unmark(String taskToString) {
        String message = "OK, I've marked this task as not done yet:\n" + taskToString;
        return message;
    }

    /**
     * Prints to the user which tasks have been found using the keyword provided by the user.
     *
     * @param tasks taskList to find tasks from.
     */
    public static String listTasksFound(ArrayList<Task> tasks) {
        String message  = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            message += (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return message;
    }

    public static String showError(GmanException e) {
        return e.getMessage();
    }
}
