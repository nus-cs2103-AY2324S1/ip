package buddy.utils;

import java.util.ArrayList;
import java.util.Scanner;

import buddy.Task;
import buddy.TaskList;

<<<<<<< HEAD
/**
 * The Ui class deals with the interactions with the user.
 *
 * @author Lim JIn Yin
 */
=======
>>>>>>> branch-A-CodingStandard
public class Ui {
    private final String name = "Task Buddy";
    private Scanner sc;

    /**
     * The constructor for a Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints the greeting message.
     */
    public void printGreeting() {
        String greeting = String.format("Hello! I'm %s\n", name);
        String inquiry = "What would you like to do?\n";
        System.out.println(greeting + inquiry);
        // System.out.println("You have the following buddy.tasks:");
    }

    /**
     * Prints the farewell message.
     */
    public void printFarewell() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints the list of tasks.
     *
     * @param list The array list of tasks.
     */
    public void printList(ArrayList<Task> list) {
        if (list.isEmpty()) {
            System.out.println("There are currently no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, list.get(i));
            }
        }
    }

    /**
     * Prints the message when a task is successfully added to the task list.
     *
     * @param task The task that is successfully added.
     * @param tasks The list of tasks that the task is added to.
     */
    public void printAddSuccessMessage(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task:\n"
                + task.toString());
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
    }

    /**
     * Prints the message when a task is successfully deleted from the task list.
     *
     * @param deletedTask The task that is successfully deleted.
     * @param tasks The list of tasks that the task is deleted from.
     */
    public void printDeleteSuccessMessage(Task deletedTask, TaskList tasks) {
        System.out.println("Noted. I've removed this task:\n" + deletedTask.toString());
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
    }

    public String readCommand() {
        return sc.nextLine();
    }
}
