package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Ui class handles interactions with the user through the console.
 * It provides methods for reading user input, greeting the user, and displaying information.
 */
public class Ui {
    /**
     * Displays a greeting message to the user.
     */
    public static void greet() {
        System.out.println("Hello! I'm Bot");
        System.out.println("What can I do for you?");
    }

    /**
     * Displays an exit message to the user.
     */
    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays a list of tasks to the user.
     *
     * @param list The TaskList containing the tasks to be listed.
     */
    public static void listTasks(TaskList list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i));
        }
    }

    /**
     * Displays the details of an added task to the user.
     *
     * @param task The task that was added.
     */
    public static void addTask(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
    }

    /**
     * Displays the count of tasks in the list to the user.
     *
     * @param list The list of tasks to be counted.
     */
    public static void countTasks(ArrayList<Task> list) {
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    /**
     * Displays the details of a marked task to the user.
     *
     * @param task The task that was marked as done.
     */
    public static void markAsDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Displays the details of a deleted task to the user.
     *
     * @param task The task that was deleted.
     */
    public static void deleteTask(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
    }

    /**
     * Displays a list of matching tasks to the user.
     *
     * @param list The list of matching tasks to be displayed.
     */
    public static void findTasks(ArrayList<Task> list) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i));
        }
    }
}
