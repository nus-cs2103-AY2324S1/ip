package glub;

import glub.task.Task;
import glub.task.TaskList;

import java.util.ArrayList;

/**
 * Ui handles all messages that the user will see.
 */
public class Ui {
    /**
     * Print horizontal line.
     */
    public static void printHorizontalLine() {
        System.out.println("_________________________________________________");
    }

    /**
     * Displays welcome message.
     */
    public static void greet() {
        printHorizontalLine();
        System.out.println("Hello! I'm Glub!");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    /**
     * Displays farewell message.
     */
    public static void sayGoodbye() {
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    /**
     * Displays message indicating that a task has been added to the task list.
     * @param taskList Task list which task has been added to.
     */
    public static void printAddMsg(ArrayList<Task> taskList) {
        int size = taskList.size();
        printHorizontalLine();
        System.out.println("Got it. I've added this task:");
        System.out.printf(" \t%s%n", taskList.get(size - 1));
        System.out.printf("Now you have %d %s in the list.%n", size, size == 1 ? "task" : "tasks");
        printHorizontalLine();
    }

    /**
     * Displays message indicating that a task has been deleted from the task list.
     * @param taskList Task list which task has been deleted from.
     * @param deleted Task that has been deleted.
     */
    public static void printDeleteMsg(ArrayList<Task> taskList, Task deleted) {
        int size = taskList.size();
        printHorizontalLine();
        System.out.println("Noted. I've removed this task:");
        System.out.printf("\t%s%n", deleted);
        System.out.printf("Now you have %d %s in the list.%n", size, size == 1 ? "task" : "tasks");
        printHorizontalLine();
    }

    /**
     * Displays all tasks in the task list.
     * @param taskList Task list containing all the tasks.
     */
    public static void printListMsg(TaskList taskList) {
        printHorizontalLine();
        System.out.println("Here are the tasks in your list:");
        System.out.println(taskList.showList());
        printHorizontalLine();
    }

    /**
     * Displays message indicating that a task has been marked.
     * @param task Task that has been marked.
     */
    public static void printMarkMsg(Task task) {
        printHorizontalLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("\t%s%n", task);
        printHorizontalLine();
    }
    /**
     * Displays message indicating that a task has been unmarked.
     * @param task Task that has been unmarked.
     */
    public static void printUnmarkMsg(Task task) {
        printHorizontalLine();
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.printf("\t%s%n", task);
        printHorizontalLine();
    }

    /**
     * Prints tasks that match to the search string.
     * @param taskList List of tasks to be searched.
     * @param searchString String tasks should match.
     */
    public static void printFindMsg(TaskList taskList, String searchString) {
        printHorizontalLine();
        String displayedTasks = taskList.findTasks(searchString);
        if (displayedTasks.equals("")) {
            System.out.println("Oops, no tasks match your search :-(");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            System.out.println(displayedTasks);
        }
        printHorizontalLine();
    }

    /**
     * Displays error message.
     * @param msg Message to be displayed.
     */
    public static void printError(String msg) {
        System.err.println(msg);
    }


}
