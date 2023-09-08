package duke;

import java.util.Scanner;

/**
 * UI of the ChatBot
 */
public class Ui {

    /**
     * Reads a line from the input.
     *
     * @return
     */
    public static String scanInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    /**
     * Prints to standard output a greeting message.
     */
    public static void printGreetings() {
        System.out.println("Hello! I'm lippy the wombat\n" + "What can I do for you?");
    }

    /**
     * Prints to an input bye.
     */
    public static void printBYE() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints when the input is not recognised
     */
    public static String printError() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    /**
     * Prints when a new task is added.
     *
     * @param task     of type Task
     * @param taskList an array of tasks
     */
    public static String printAddTask(Task task, TaskList taskList) {
        return "Got it. I've added this task:\n" + task + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Prints to the input list when the list is empty.
     */
    public static String printEmptyList() {
        return "There is currently no items in the list";
    }

    /**
     * Prints to an input list when list is not empty.
     *
     * @param taskList an array of tasks
     */
    public static String printList(TaskList taskList) {
        String tasks = "";
        for (int i = 1; i <= taskList.size(); i++) {
            String task = String.valueOf(taskList.getTask(i));
            String indivTask = i + ". " + task + "\n";
            tasks += indivTask;
        }
        return "Here are the tasks in your list: \n" + tasks;
    }

    /**
     * Prints when the user marks a task done.
     *
     * @param task of type Task
     */

    public static String printDone(Task task) {
        return "Nice! I've marked this task as done:" + "\n" + task;
    }

    /**
     * Prints when the user unmarks a task.
     *
     * @param task of type Task
     */
    public static String printNotDone(Task task) {
        return "OK, I've marked this task as not done yet:" + "\n" + task;
    }

    /**
     * Prints when an IndexOutOfBounds exception is thrown.
     */
    public static String outOfBounds() {
        return "IndexOutOfBounds";
    }

    /**
     * Prints when a NumberFormatException is thrown.
     */

    public static String numberFormat() {
        return "NumberFormatException";
    }

    /**
     * Prints when a task gets deleted.
     *
     * @param deleted  of type String that represents the deleted task
     * @param taskList an array of tasks
     */
    public static String removeTask(String deleted, TaskList taskList) {
        return "Noted. I've removed this task:\n" + deleted + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Prins when todo description is empty.
     */
    public static String toDoExcept() {
        return "☹ OOPS!!! The description of a todo cannot be empty.";
    }

    /**
     * Prints when the date format for deadline is invalid.
     */
    public static String dateFormatExcept() {
        return "invalid date format";
    }

    /**
     * Prints when the deadline input is wrongly formatted
     */
    public static String deadlineExcept() {
        return "Wrong Deadline Format!";
    }

    /**
     * Prints when the event input is wrongly formatted
     */

    public static String eventExcept() {
        return "Wrong Event Format!";
    }

    /**
     * Prints the tasks containing the target string when there is a valid find command.
     *
     * @param newList a new TaskList that contains the matching tasks
     */
    public static String printMatching(TaskList newList) {
        if (newList.size() == 0) {
            return "no matching task found:(";
        } else {
            String tasks = "";
            for (int i = 1; i <= newList.size(); i++) {
                String task = String.valueOf(newList.getTask(i));
                String indivTask = i + ". " + task + "\n";
                tasks += indivTask;
            }
            return "Here are the matching tasks in your list:" + tasks;
        }
    }

    /**
     * Empty find command description.
     */
    public static String matchExcept() {
        return "description of a find command cannot be empty";
    }

    public static String markExcept() {
        return "Sorry mark description cannot be empty!";
    }
}
