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
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    private void printWithLines(String s) {
        System.out.println(this.HORIZONTAL_LINE + "\n" + s + "\n" + this.HORIZONTAL_LINE);
    }

    /**
     * Prints Hello message when user starts the application.
     */
    public void printHello() {
        printWithLines("Hello, I'm Je-O" + "\n"
                + "What can I do for you?");
    }

    /**
     * Prints a message indicating success in adding a task to the list.
     *
     * @param task The task added to the list.
     * @param countTasks Number of tasks in the list.
     */
    public void printAddTask(Task task, int countTasks) {
        printWithLines("Got it. I've added this task:" + "\n"
                + task.toString() + "\n"
                + "Now you have " + countTasks + " tasks in the list.");
    }

    /**
     * Prints a message indicating success in deleting a task from the list.
     *
     * @param task The task deleted from the list.
     * @param countTasks Number of tasks in the list.
     */
    public void printDeleteTask(Task task, int countTasks) {
        printWithLines("Noted. I've removed this task:" + "\n"
                + task.toString() + "\n"
                + "Now you have " + countTasks + " tasks in the list.");
    }

    /**
     * Prints all the tasks in the list.
     *
     * @param tasks List of tasks to be printed.
     * @param countTasks Number of tasks in the list.
     */
    public void printTaskList(TaskList tasks, int countTasks) {
        System.out.println(HORIZONTAL_LINE);
        for (int i = 0; i < countTasks; i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints a message indicating success in marking a task as done.
     *
     * @param task The task marked as done.
     */
    public void printMarkAsDone(Task task) {
        printWithLines("Nice! I've marked this task as done:" + "\n" + task);
    }

    /**
     * Prints a message indicating success in marking a task as not done.
     *
     * @param task The task marked as not done.
     */
    public void printUnmarkAsDone(Task task) {
        printWithLines("OK, I've marked this task as not done yet:" + "\n" + task);
    }

    /**
     * Prints Bye message when user has done using the application.
     */
    public void printBye() {
        printWithLines("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the tasks that matched a certain keyword.
     *
     * @param findTasks A list of tasks that matched a certain keyword.
     */
    public void printFindTasks(TaskList findTasks) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < findTasks.getCountTasks(); i++) {
            System.out.println((i + 1) + ". " + findTasks.getTask(i));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints an error message from the DukeException.
     *
     * @param e DukeException which error message is to be printed.
     */
    public void printErrorMessage(DukeException e) {
        printWithLines(e.toString());
    }
}
