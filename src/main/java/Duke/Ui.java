package duke;

import java.util.ArrayList;

/**
 * UI class that contains strings to be printed to the user.
 */
public class Ui {

    /**
     * Greets the user.
     */
    public static void greet() {
        System.out.println("Hello! I'm Gman! \nWhat can I do for you?");
    }

    /**
     * Says goodbye to the user.
     */
    public static void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints out all tasks in taskList.
     *
     * @param taskList taskList to retrieve Tasks from.
     */
    public static void listTasks(TaskList taskList) {
        try {
            if (taskList.getSize() == 0) {
                throw new GmanException("There's nothing to print in the list");
            }
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.getSize(); i++) {
                System.out.println((i + 1) + ". "  + taskList.getTask(i).toString());
            }
        } catch (GmanException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints out the number of tasks in taskList.
     *
     * @param taskList taskList to retrieve tasks from.
     */
    public static void numberOfTasks(TaskList taskList) {
        if (taskList.getSize() == 0) {
            System.out.println("There are no tasks in the list!");
        } else if (taskList.getSize() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
        }
    }

    /**
     * Prints to the user that this task has been added.
     *
     * @param task Task to print the description of.
     */
    public static void addedTask(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
    }

    /**
     * Prints to the user that this task has been removed.
     *
     * @param task Task to print the description of.
     */
    public static void removedTask(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
    }

    /**
     * Prints to the user that this task has been marked as done.
     *
     * @param taskToString Task description.
     */
    public static void mark(String taskToString) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskToString);
    }

    /**
     * Prints to the user that this task has been marked as undone.
     *
     * @param taskToString Task description.
     */
    public static void unmark(String taskToString) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskToString);
    }

    /**
     * Prints to the user which tasks have been found using the keyword provided by the user.
     *
     * @param tasks taskList to find tasks from.
     */
    public static void listTasksFound(ArrayList<Task> tasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }
}
