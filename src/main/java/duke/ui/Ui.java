package duke.ui;

import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * The Ui class for the user interaction of the program
 */
public class Ui {
    private static final String SPACE = "    ";
    private static final String DASH = "____________________________________________________________";
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints the intro to user
     * @return the intro message
     */
    public String printIntro() {
        String intro = SPACE + "Hello! I'm Not A ChatBot\n"
                        + SPACE + "What can I do for you?";
        System.out.println(SPACE + DASH);
        System.out.println(intro);
        System.out.println(SPACE + DASH);
        return intro;
    }

    /**
     * Prints the bye message to user
     * @return the bye message
     */
    public String printEnd() {
        String end = SPACE + "Bye. Hope to see you again soon!";
        System.out.println(SPACE + DASH);
        System.out.println(end);
        System.out.println(SPACE + DASH);
        return end;
    }

    /**
     * Prints if exception occur
     *
     * @param e the exception
     * @return the message of the exception
     */
    public String printException(Exception e) {
        String exceptionMessage = SPACE + e.getMessage();
        System.out.println(SPACE + DASH);
        System.out.println(exceptionMessage);
        System.out.println(SPACE + DASH);
        return exceptionMessage;
    }

    /**
     * Prints the list of tasks
     *
     * @param tasks the list of tasks
     * @return the message of the list task command
     */
    public String printTasks(TaskList tasks) {
        String tasksMessage = SPACE + "Here are the tasks in your list:\n"
                            + tasks;
        System.out.println(SPACE + DASH);
        System.out.println(tasksMessage);
        System.out.println(SPACE + DASH);
        return tasksMessage;
    }

    /**
     * Prints the output of marking task
     *
     * @param task the task marked
     * @return the message of the mark task command
     */
    public String printMarkTask(Task task) {
        String markTaskMessage = SPACE + "Nice! I've marked this task as done:\n"
                                + SPACE + task;
        System.out.println(SPACE + DASH);
        System.out.println(markTaskMessage);
        System.out.println(SPACE + DASH);
        return markTaskMessage;
    }

    /**
     * Prints the output of unmarking task
     *
     * @param task the task unmarked
     * @return the message of the unmark task command
     */
    public String printUnmarkTask(Task task) {
        String unmarkTaskMessage = SPACE + "OK, I've unmarked this task as not done yet:\n"
                                + SPACE + task;
        System.out.println(SPACE + DASH);
        System.out.println(unmarkTaskMessage);
        System.out.println(SPACE + DASH);
        return unmarkTaskMessage;
    }

    /**
     * Prints the output of adding task
     *
     * @param task the new task
     * @param size the number of tasks
     * @return the message of the add task command
     */
    public String printAddTask(Task task, int size) {
        String addTaskMessage = SPACE + "Got it. I've added this task:\n"
                                + SPACE + task + "\n"
                                + SPACE + "Now you have " + size + " tasks in the list.";
        System.out.println(SPACE + DASH);
        System.out.println(addTaskMessage);
        System.out.println(SPACE + DASH);
        return addTaskMessage;
    }

    /**
     * Prints the output of removing task
     *
     * @param task the task removed
     * @param size the number of tasks
     * @return the message of the remove task command
     */
    public String printRemoveTask(Task task, int size) {
        String removeTaskMessage = SPACE + "Noted. I've removed this task:\n"
                                    + SPACE + task + "\n"
                                    + SPACE + "Now you have " + size + " tasks in the list.";
        System.out.println(SPACE + DASH);
        System.out.println(removeTaskMessage);
        System.out.println(SPACE + DASH);
        return removeTaskMessage;
    }

    /**
     * Prints the output of find task
     *
     * @param tasks the list of tasks
     * @return the message of the find task command
     */
    public String printFindTask(TaskList tasks) {
        String findTaskMessage;
        if (tasks.isEmpty()) {
            findTaskMessage = "Cannot find any tasks with this keyword";
        } else {
            findTaskMessage = tasks + "\n";
        }
        System.out.println(SPACE + DASH);
        System.out.println(findTaskMessage);
        System.out.println(SPACE + DASH);
        return findTaskMessage;
    }
}
