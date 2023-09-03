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
    public static void printError() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Prints when a new task is added.
     *
     * @param task of type Task
     * @param taskList an array of tasks
     */
    public static void printAddTask(Task task, TaskList taskList) {
        System.out.println("Got it. I've added this task:\n" + task + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Prints to the input list when the list is empty.
     */
    public static void printEmptyList() {
        System.out.println("There is currently no items in the list");
    }

    /**
     * Prints to an input list when list is not empty.
     *
     * @param taskList an array of tasks
     */
    public static void printList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            String task = String.valueOf(taskList.getTask(i));
            System.out.println(i + ". " + task);
        }
    }

    /**
     * Prints when the user marks a task done.
     *
     * @param task of type Task
     */

    public static void printDone(Task task) {
        System.out.println("Nice! I've marked this task as done:" + "\n" + task);
    }

    /**
     * Prints when the user unmarks a task.
     *
     * @param task of type Task
     */
    public static void printNotDone(Task task) {
        System.out.println("OK, I've marked this task as not done yet:" + "\n" + task);
    }

    /**
     * Prints when an IndexOutOfBounds exception is thrown.
     */
    public static void outOfBounds() {
        System.out.println("IndexOutOfBounds");
    }

    /**
     * Prints when a NumberFormatException is thrown.
     */

    public static void numberFormat() {
        System.out.println("NumberFormatException");
    }

    /**
     * Prints when a task gets deleted.
     *
     * @param deleted of type String that represents the deleted task
     * @param taskList an array of tasks
     */
    public static void removeTask(String deleted, TaskList taskList) {
        System.out.println("Noted. I've removed this task:\n" + deleted + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Prins when todo description is empty.
     */
    public static void toDoExcept() {
        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
    }

    /**
     * Prints when the date format for deadline is invalid.
     */
    public static void dateFormatExcept() {
        System.out.println("invalid date format");
    }

    /**
     * Prints when the deadline input is wrongly formatted
     */
    public static void deadlineExcept() {
        System.out.println("Wrong Deadline Format!");
    }

    /**
     * Prints when the event input is wrongly formatted
     */

    public static void eventExcept() {
        System.out.println("Wrong Event Format!");
    }

    /**
     * Prints the tasks containing the target string when there is a valid find command.
     *
     * @param newList a new TaskList that contains the matching tasks
     */
    public static void printMatching(TaskList newList) {
        if (newList.size() == 0) {
            System.out.println("no matching task found:(");
            return;
        }
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 1; i <= newList.size(); i++) {
            String task = String.valueOf(newList.getTask(i));
            System.out.println(i + ". " + task);
        }
    }

    /**
     * Empty find command description.
     */
    public static void matchExcept() {
        System.out.println("description of a find command cannot be empty");
    }
}
