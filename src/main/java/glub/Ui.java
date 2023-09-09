package glub;

import java.util.ArrayList;

import glub.task.Task;
import glub.task.TaskList;

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
    public static String greet() {
        return "Hello! I'm Glub!\n" + "What can I do for you?";
    }

    /**
     * Displays farewell message.
     */
    public static String sayGoodbye() {
        return "Bye! Hope to see you again soon!";
    }

    /**
     * Displays message indicating that a task has been added to the task list.
     * @param taskList Task list which task has been added to.
     */
    public static String printAddMsg(ArrayList<Task> taskList) {
        int size = taskList.size();
        return "Got it. I've added this task:\n"
                + String.format(" \t%s%n", taskList.get(size - 1))
                + String.format("Now you have %d %s in the list.", size, size == 1 ? "task" : "tasks");
    }

    /**
     * Displays message indicating that a task has been deleted from the task list.
     * @param taskList Task list which task has been deleted from.
     * @param deleted Task that has been deleted.
     */
    public static String printDeleteMsg(ArrayList<Task> taskList, Task deleted) {
        int size = taskList.size();
        return "Noted. I've removed this task:\n"
                + String.format("\t%s%n", deleted)
                + String.format("Now you have %d %s in the list.", size, size == 1 ? "task" : "tasks");
    }

    /**
     * Displays all tasks in the task list.
     * @param taskList Task list containing all the tasks.
     */
    public static String printListMsg(TaskList taskList) {
        return "Here are the tasks in your list:\n" + taskList.showList();
    }

    /**
     * Displays message indicating that a task has been marked.
     * @param task Task that has been marked.
     */
    public static String printMarkMsg(Task task) {
        return "Nice! I've marked this task as done:\n" + String.format("\t%s", task);
    }

    /**
     * Displays message indicating that a task has been unmarked.
     * @param task Task that has been unmarked.
     */
    public static String printUnmarkMsg(Task task) {
        return "Ok, I've marked this task as not done yet:\n" + String.format("\t%s", task);
    }

    /**
     * Prints tasks that match to the search string.
     * @param taskList List of tasks to be searched.
     * @param searchString String tasks should match.
     */
    public static String printFindMsg(TaskList taskList, String searchString) {
        String displayedTasks = taskList.findTasks(searchString);
        if (displayedTasks.equals("")) {
            return "Oops, no tasks match your search :-(";
        } else {
            return "Here are the matching tasks in your list:\n" + displayedTasks;
        }
    }

    /**
     * Displays error message.
     * @param msg Message to be displayed.
     */
    public static String printError(String msg) {
        return msg;
    }


}
