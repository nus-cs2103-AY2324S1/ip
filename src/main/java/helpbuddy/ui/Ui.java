package helpbuddy.ui;

import java.util.ArrayList;

import helpbuddy.task.Task;
import helpbuddy.task.TaskList;

/**
 * An Ui class that prints the corresponding response by HelpBuddy after user input.
 */
public class Ui {

    /**
     * Prints bye message when HelpBuddy is closed by user.
     * @return a String of HelpBuddy's farewell message.
     */
    public String printByeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints a list of Task that user has keyed into HelpBuddy.
     * @param taskList the taskList that stores Task objects.
     * @return a String message of HelpBuddy's reply after being asked to list all Tasks.
     */
    public String printListMessage(TaskList taskList) {
        if (taskList.isEmpty()) {
            return "You have no task in your list.";
        } else {
            String messageOutput = "Here are the tasks in your list:\n";
            for (int i = 0; i < taskList.getSize(); i++) {
                int taskIndex = i + 1;
                messageOutput += taskIndex + ". " + taskList.getTask(i) + "\n";
            }
            return messageOutput;
        }
    }

    /**
     * Prints the message that a Task object is marked as done.
     * @param task the task to mark as done.
     * @return a String message of HelpBuddy's reply after being asked to mark a Task.
     */
    public String printMarkMessage(Task task) {
        return "Nice! I've marked this task as done:\n\t" + task;
    }

    /**
     * Prints the message that a Task object is marked as not done.
     * @param task the task to mark as not done.
     * @return a String message of HelpBuddy's reply after being asked to mark a Task.
     */
    public String printUnmarkMessage(Task task) {
        return "OK, I've marked this task as not done yet:\n\t" + task;
    }

    /**
     * Returns a String representation of the size of taskList.
     * @param taskList the taskList that contains Task objects.
     * @return a String message that Ui will output to users to notify the number of Task objects in taskList.
     */
    private String stringifyNumOfTasks(TaskList taskList) {
        return "\nNow you have " + taskList.getSize() + " tasks in the list.";
    }

    /**
     * Prints task added successfully.
     * @param task the task added.
     * @param taskList the taskList that task is added to.
     * @return a String message of HelpBuddy's reply after task is added successfully.
     */
    public String printAddTaskMessage(Task task, TaskList taskList) {
        return "Got it. I've added this task:\n" + task + stringifyNumOfTasks(taskList);
    }

    /**
     * Prints task deleted successfully.
     * @param task the task deleted.
     * @param taskList the taskList that task is deleted from.
     * @return a String message of HelpBuddy's reply after deleting task.
     */
    public String printDeleteTaskMessage(Task task, TaskList taskList) {
        return "Noted. I've removed this task:\n" + task + stringifyNumOfTasks(taskList);
    }

    /**
     * Prints the taskList.
     * @param taskList the taskList that contains Task objects user is finding.
     */
    public String printFindTaskMessage(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            return "There is no such task in your list.";
        } else {
            String messageOutput = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < taskList.size(); i++) {
                int taskIndex = i + 1;
                messageOutput += taskIndex + ". " + taskList.get(i) + "\n";
            }
            return messageOutput;
        }
    }

    /**
     * Returns a String that directs users to follow the format of dates for Deadline and Event Task inputs.
     * @return a String containing date time parse error message.
     */
    public String printDateTimeParseErrorMessage() {
        return "Please enter the start/end time in the format of <DD/MM/YY HH:MM>!";
    }

}
