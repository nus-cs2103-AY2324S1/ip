package duke.util;

import java.util.ArrayList;

import duke.task.Task;

/**
 * duke.util.Ui class that deals with interactions with the user.
 *
 * @author Pearlynn
 */

public class Ui {
    private final String line = "\t____________________________________________________________";

    /**
     * Shows the welcome message.
     */
    public void showWelcome() {
        System.out.println(line);
        System.out.println("\t Hello! I'm Violet");
        System.out.println("\t What can I do for you?");
        System.out.println(line);
    }

    /**
     * Shows the exit message.
     */
    public void showExit() {
        System.out.println("\t Bye. Hope to see you again soon!");
    }

    /**
     * Shows the error message.
     *
     * @param errMsg The error message.
     */
    public void showError(String errMsg) {
        System.out.println("\t " + errMsg);
    }

    /**
     * Shows the divider line.
     */
    public void printLine() {
        System.out.println(line);
    }

    /**
     * Prints the task list.
     *
     * @param list The task list.
     */
    public void printList(ArrayList<Task> list) {
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("\t " + (i + 1) + "." + list.get(i).toString());
        }
    }

    /**
     * Prints the task marked as done.
     *
     * @param task The task marked as done.
     */
    public void printMarkTask(Task task) {
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t\t" + task.toString());
    }

    /**
     * Prints the task marked as undone.
     *
     * @param task The task marked as undone.
     */
    public void printUnmarkTask(Task task) {
        System.out.println("\t OK, I've marked this task as not done yet:");
        System.out.println("\t\t" + task.toString());
    }

    /**
     * Prints the number of tasks in the list.
     *
     * @param num The number of tasks.
     */
    private void printNumTasks(int num) {
        if (num == 1 || num == 0) {
            System.out.println("\t Now you have " + num + " task in the list.");
        } else {
            System.out.println("\t Now you have " + num + " tasks in the list.");
        }
    }

    /**
     * Prints the task added to the list.
     *
     * @param task The task added.
     * @param num The number of tasks.
     */
    public void printAddTask(Task task, int num) {
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t\t" + task.toString());
        printNumTasks(num);
    }

    /**
     * Prints the task deleted from the list.
     *
     * @param task The task deleted.
     * @param num The number of tasks.
     */
    public void printDeleteTask(Task task, int num) {
        System.out.println("\t Noted. I've removed this task:");
        System.out.println("\t\t" + task.toString());
        printNumTasks(num);
    }

    /**
     * Prints the error message when an IndexOutOfBoundsException occurs.
     *
     * @param list The task list.
     */
    public void printIndexOutOfBoundsException(ArrayList<Task> list) {
        if (list.isEmpty()) {
            System.out.println("\t ☹ OOPS!!! You don't have any task in your list. Pls add a task.");
        } else if (list.size() == 1) {
            System.out.println("\t ☹ OOPS!!! You only have one task in your list. Pls select 1.");
        } else {
            System.out.println("\t ☹ OOPS!!! Pls select a task number between 1 and " + list.size());
        }
    }

    /**
     * Prints the error message when a DateTimeParseException occurs.
     */
    public void printDateTimeParseException() {
        System.out.println("\t ☹ OOPS!!! Invalid date format. Pls provide a date in the format yyyy-MM-dd HHmm.");
    }
}
