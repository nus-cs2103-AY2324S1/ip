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
        String intro = SPACE + DASH + "\n"
                        + SPACE + "Hello! I'm Not A ChatBot\n"
                        + SPACE + "What can I do for you?\n"
                        + SPACE + DASH;
        System.out.println(intro);
        return intro;
    }

    /**
     * Prints the bye message to user
     * @return the bye message
     */
    public String printEnd() {
        String end = SPACE + DASH + "\n"
                    + SPACE + "Bye. Hope to see you again soon!\n"
                    + SPACE + DASH;
        System.out.println(end);
        return end;
    }

    /**
     * Prints if exception occur
     *
     * @param e the exception
     * @return the message of the exception
     */
    public String printException(Exception e) {
        String exceptionMessage = SPACE + DASH + "\n"
                                + SPACE + e.getMessage() + "\n"
                                + SPACE + DASH;
        System.out.println(exceptionMessage);
        return exceptionMessage;
    }

    /**
     * Prints the list of tasks
     *
     * @param tasks the list of tasks
     * @return the message of the list task command
     */
    public String printTasks(TaskList tasks) {
        String tasksMessage = SPACE + DASH + "\n"
                            + SPACE + "Here are the tasks in your list:\n"
                            + tasks + "\n"
                            + SPACE + DASH;
        System.out.println(tasksMessage);
        return tasksMessage;
    }

    /**
     * Prints the output of marking task
     *
     * @param task the task marked
     * @return the message of the mark task command
     */
    public String printMarkTask(Task task) {
        String markTaskMessage = SPACE + DASH + "\n"
                                + SPACE + "Nice! I've marked this task as done:\n"
                                + SPACE + task + "\n"
                                + SPACE + DASH;
        System.out.println(markTaskMessage);
        return markTaskMessage;
    }

    /**
     * Prints the output of unmarking task
     *
     * @param task the task unmarked
     * @return the message of the unmark task command
     */
    public String printUnmarkTask(Task task) {
        String unmarkTaskMessage = SPACE + DASH + "\n"
                                + SPACE + "OK, I've unmarked this task as not done yet:\n"
                                + SPACE + task + "\n"
                                + SPACE + DASH;
        System.out.println(unmarkTaskMessage);
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
        String addTaskMessage = SPACE + DASH + "\n"
                                + SPACE + "Got it. I've added this task:\n"
                                + SPACE + task + "\n"
                                + SPACE + "Now you have " + size + " tasks in the list.\n"
                                + SPACE + DASH;
        System.out.println(addTaskMessage);
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
        String removeTaskMessage = SPACE + DASH + "\n"
                                    + SPACE + "Noted. I've removed this task:\n"
                                    + SPACE + task + "\n"
                                    + SPACE + "Now you have " + size + " tasks in the list.\n"
                                    + SPACE + DASH;
        System.out.println(removeTaskMessage);
        return removeTaskMessage;
    }

    /**
     * Prints the output of find task
     *
     * @param tasks the list of tasks
     * @return the message of the find task command
     */
    public String printFindTask(TaskList tasks) {
        String findTaskMessage = SPACE + DASH + "\n";
        if (tasks.isEmpty()) {
            findTaskMessage += "Cannot find any tasks with this keyword";
        } else {
            findTaskMessage += tasks + "\n";
        }
        findTaskMessage += SPACE + DASH;
        System.out.println(findTaskMessage);
        return findTaskMessage;
    }
}
