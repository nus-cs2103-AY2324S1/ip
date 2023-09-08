package duke.ui;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Functions as the user interface for the Duke application.
 *
 * @author Joseph Oliver Lim
 */
public class Ui {

    /**
     * Returns Hello message when user starts the application.
     *
     * @return A String to be shown to the user.
     */
    public String hello() {
        return "Hello, I'm Je-O" + "\n" + "What can I do for you?";
    }

    /**
     * Returns a message indicating success in adding a task to the list.
     *
     * @param task The task added to the list.
     * @param countTasks Number of tasks in the list.
     * @return A String to be shown to the user.
     */
    public String addTask(Task task, int countTasks) {
        return "Got it. I've added this task:" + "\n"
                + task.toString() + "\n"
                + "Now you have " + countTasks + " tasks in the list.";
    }

    /**
     * Returns a message indicating success in deleting a task from the list.
     *
     * @param task The task deleted from the list.
     * @param countTasks Number of tasks in the list.
     * @return A String to be shown to the user.
     */
    public String deleteTask(Task task, int countTasks) {
        return "Noted. I've removed this task:" + "\n"
                + task.toString() + "\n"
                + "Now you have " + countTasks + " tasks in the list.";
    }

    /**
     * Returns all the tasks in the list.
     *
     * @param tasks List of tasks to be printed.
     * @param countTasks Number of tasks in the list.
     * @return A String to be shown to the user.
     */
    public String taskList(TaskList tasks, int countTasks) {
        String output = "";
        for (int i = 0; i < countTasks; i++) {
            output += (i + 1) + ". " + tasks.getTask(i) + "\n";
        }
        return output;
    }

    /**
     * Returns a message indicating success in marking a task as done.
     *
     * @param task The task marked as done.
     * @return A String to be shown to the user.
     */
    public String markAsDone(Task task) {
        return "Nice! I've marked this task as done:" + "\n" + task;
    }

    /**
     * Returns a message indicating success in marking a task as not done.
     *
     * @param task The task marked as not done.
     * @return A String to be shown to the user.
     */
    public String unmarkAsDone(Task task) {
        return "OK, I've marked this task as not done yet:" + "\n" + task;
    }

    /**
     * Returns Bye message when user has done using the application.
     *
     * @return A String to be shown to the user.
     */
    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns the tasks that matched a certain keyword.
     *
     * @param findTasks A list of tasks that matched a certain keyword.
     * @return A String to be shown to the user.
     */
    public String findTasks(TaskList findTasks) {
        String output = "";
        output += "Here are the matching tasks in your list:" + "\n";
        for (int i = 0; i < findTasks.getCountTasks(); i++) {
            output += (i + 1) + ". " + findTasks.getTask(i) + "\n";
        }
        return output;
    }

    /**
     * Returns an error message from the DukeException.
     *
     * @param e DukeException which error message is to be printed.
     * @return A String to be shown to the user.
     */
    public String errorMessage(DukeException e) {
        return e.toString();
    }
}
