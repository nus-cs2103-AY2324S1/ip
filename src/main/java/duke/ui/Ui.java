package duke.ui;

import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * The Ui class for the user interaction of the program
 */
public class Ui {
    private static String SPACE = "    ";
    private static String DASH = "____________________________________________________________";
    Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints the intro to user
     */
    public void printIntro() {
        String intro = SPACE + DASH + "\n"
                        + SPACE + "Hello! I'm Not A ChatBot\n"
                        + SPACE + "What can I do for you?\n"
                        + SPACE + DASH;
        System.out.println(intro);
    }

    /**
     * Prints the good bye message to user
     */
    public void printEnd() {
        String end = SPACE + DASH + "\n"
                    + SPACE + "Bye. Hope to see you again soon!\n"
                    + SPACE + DASH;
        System.out.println(end);
    }

    /**
     * Prints if exception occur
     * 
     * @param e the exception
     */
    public void printException(Exception e) {
        System.out.println(SPACE + DASH);
        System.out.println(SPACE + e.getMessage());
        System.out.println(SPACE + DASH);
    }

    /**
     * Prints the list of tasks
     * 
     * @param tasks the list of tasks
     */
    public void printTasks(TaskList tasks) {
        System.out.println(SPACE + DASH);
        System.out.println("    Here are the tasks in your list:");
        System.out.print(tasks);
        System.out.println(SPACE + DASH);
    }

    /**
     * Prints the output of marking task
     * 
     * @param task the task marked
     */
    public void printMarkTask(Task task) {
        System.out.println(SPACE + DASH);
        System.out.println(SPACE + "Nice! I've marked this task as done:");
        System.out.println(SPACE + task);
        System.out.println(SPACE + DASH);
    }

    /**
     * Prints the output of unmarking task
     * 
     * @param task the task unmarked
     */
    public void printUnmarkTask(Task task) {
        System.out.println(SPACE + DASH);
        System.out.println(SPACE + "OK, I've unmarked this task as not done yet:");
        System.out.println(SPACE + task);
        System.out.println(SPACE + DASH);
    }

    /**
     * Prints the output of adding task
     * 
     * @param task the new task
     * @param size the number of tasks
     */
    public void printAddTask(Task task, int size) {
        System.out.println(SPACE + DASH);
        System.out.println(SPACE + "Got it. I've added this task:");
        System.out.println(SPACE + task);
        System.out.println(SPACE + "Now you have " + size + " tasks in the list.");
        System.out.println(SPACE + DASH);
    }

    /**
     * Prints the output of removing task
     * 
     * @param task the task removed
     * @param size the number of tasks
     */
    public void printRemoveTask(Task task, int size) {
        System.out.println(SPACE + DASH);
        System.out.println("    Noted. I've removed this task:");
        System.out.println("    " + task);
        System.out.println("    Now you have " + size + " tasks in the list.");
        System.out.println(SPACE + DASH);
    }

    public void printFindTask(TaskList tasks) {
        System.out.println(SPACE + DASH);
        if (tasks.isEmpty()) {
            System.out.println("Cannot find any tasks with this keyword");
        } else {
            System.out.println(tasks);
        }
        System.out.println(SPACE + DASH);
    }
}
