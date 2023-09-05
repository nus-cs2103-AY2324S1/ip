package duke.util;

import java.util.ArrayList;

import duke.task.Task;

/**
 * duke.util.Ui class that deals with interactions with the user.
 *
 * @author Pearlynn
 */

public class Ui {
    private static final String LINE = "\t____________________________________________________________";

    /**
     * Shows the welcome message.
     */
    public void showWelcome() {
        System.out.println(LINE);
        System.out.println("\t Hello! I'm Violet");
        System.out.println("\t What can I do for you?");
        System.out.println(LINE);
        //return line + "\n\t Hello! I'm Violet" + "\n\t What can I do for you?\n" + line;
    }

    /**
     * Returns the exit message.
     *
     * @return The exit message.
     */
    public String showExit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns the task list.
     *
     * @param list The array list of tasks.
     * @return The task list.
     */
    public String printList(ArrayList<Task> list) {
        StringBuilder tasks = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            tasks.append("\n ").append(i + 1).append(".").append(list.get(i).toString());
        }
        return tasks.toString();
    }

    /**
     * Returns the message when the task marked as done.
     *
     * @param task The task marked as done.
     * @return The marked task message.
     */
    public String printMarkTask(Task task) {
        return "Nice! I've marked this task as done:\n" + task.toString();
    }

    /**
     * Returns the message when the task marked as undone.
     *
     * @param task The task marked as undone.
     * @return The unmarked task message.
     */
    public String printUnmarkTask(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task.toString();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @param num The number of tasks.
     * @return The number of tasks in the list.
     */
    private String printNumTasks(int num) {
        if (num == 1 || num == 0) {
            return "\nNow you have " + num + " task in the list.";
        } else {
            return "\nNow you have " + num + " tasks in the list.";
        }
    }

    /**
     * Returns the task added to the list.
     *
     * @param task The task added.
     * @param num The number of tasks.
     * @return The added task message.
     */
    public String printAddTask(Task task, int num) {
        return "Got it. I've added this task:\n" + task.toString() + printNumTasks(num);
    }

    /**
     * Returns the task deleted from the list.
     *
     * @param task The task deleted.
     * @param num The number of tasks.
     * @return The deleted task message.
     */
    public String printDeleteTask(Task task, int num) {
        return "Noted. I've removed this task:\n" + task.toString() + printNumTasks(num);
    }

    /**
     * Returns the task list containing the tasks with the keyword.
     *
     * @param list The array list containing the tasks with the keyword.
     * @return The task list containing the tasks with the keyword.
     */
    public String printFindTask(ArrayList<Task> list) {
        StringBuilder tasks = new StringBuilder("Here are the matching tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            tasks.append("\n ").append(i + 1).append(".").append(list.get(i).toString());
        }
        return tasks.toString();
    }

    /**
     * Returns the error message when an IndexOutOfBoundsException occurs.
     *
     * @param list The array list of tasks.
     * @return The error message when an IndexOutOfBoundsException occurs.
     */
    public String printIndexOutOfBoundsException(ArrayList<Task> list) {
        if (list.isEmpty()) {
            return "☹ OOPS!!! You don't have any task in your list. Pls add a task.";
        } else if (list.size() == 1) {
            return "☹ OOPS!!! You only have one task in your list. Pls select 1.";
        } else {
            return "☹ OOPS!!! Pls select a task number between 1 and " + list.size();
        }
    }

    /**
     * Returns the error message when a DateTimeParseException occurs.
     *
     * @return The error message when a DateTimeParseException occurs.
     */
    public String printDateTimeParseException() {
        return "☹ OOPS!!! Invalid date format. Pls provide a date in the format yyyy-MM-dd HHmm.";
    }
}
