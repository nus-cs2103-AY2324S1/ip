package buddy.utils;

import java.util.ArrayList;
import java.util.Scanner;

import buddy.Task;
import buddy.TaskList;

/**
 * The Ui class deals with the interactions with the user.
 *
 * @author Lim JIn Yin
 */
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
    public String printGreeting() {
        String greeting = String.format("Hello! I'm %s\n", name);
        String inquiry = "What would you like to do?\n";
        return greeting + inquiry;
        // System.out.println("You have the following buddy.tasks:");
    }

    /**
     * Prints the farewell message.
     */
    public String printFarewell() {
        return "Bye. Hope to see you again soon!\n";
    }

    public String printMessage(String message) {
        return message;
    }

    /**
     * Prints the list of tasks.
     *
     * @param list The array list of tasks to be printed.
     */
    public String printList(ArrayList<Task> list) {
        if (list.isEmpty()) {
            return "Sorry, no results found.";
        } else {
            String header = "Here are the tasks in your list:\n";
            String listInString = "";
            for (int i = 0; i < list.size(); i++) {
                int taskIndex = i + 1;
                listInString += (taskIndex + ". " + list.get(i) + "\n");
            }
            return header + listInString;
        }
    }

    /**
     * Prints the message when a task is successfully added to the task list.
     *
     * @param task The task that is successfully added.
     * @param tasks The list of tasks that the task is added to.
     */
    public String printAddSuccessMessage(Task task, TaskList tasks) {
        return "Got it. I've added this task:\n" + task.toString() + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Prints the message when a task is successfully deleted from the task list.
     *
     * @param deletedTask The task that is successfully deleted.
     * @param tasks The list of tasks that the task is deleted from.
     */
    public String printDeleteSuccessMessage(Task deletedTask, TaskList tasks) {
        return "Noted. I've removed this task:\n" + deletedTask.toString()
                + "\nNow you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Prints the message when a task is successfully updated.
     *
     * @param updatedTask The task after it is successfully updated.
     */
    public String printUpdateSuccessMessage(Task updatedTask) {
        return "Noted. I've updated this task to:\n" + updatedTask.toString();
    }

    public String readCommand() {
        return sc.nextLine();
    }
}
