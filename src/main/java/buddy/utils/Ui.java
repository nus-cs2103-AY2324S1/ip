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
        String greeting = String.format("Hello! I'm %s!\n", name);
        String inquiry = "What would you like to do?\n";
        return greeting + inquiry;
        // System.out.println("You have the following buddy.tasks:");
    }

    /**
     * Prints the farewell message.
     */
    public String printFarewell() {
        return "Bye! Hope to see you again soon!\n";
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
            return "Sorry, I couldn't find anything in your list :(";
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
     * Prints the list of tasks.
     *
     * @param list The array list of tasks to be printed.
     */
    public String printFilteredList(ArrayList<Task> list) {
        if (list.isEmpty()) {
            return "Sorry, I couldn't find any matches :(";
        } else {
            String header = "Here are the matching tasks in your list:\n";
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
        return "Yay! I've added this task:\n" + task.toString() + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Prints the message when a task is successfully deleted from the task list.
     *
     * @param deletedTask The task that is successfully deleted.
     * @param tasks The list of tasks that the task is deleted from.
     */
    public String printDeleteSuccessMessage(Task deletedTask, TaskList tasks) {
        return "Yay! I've removed this task:\n" + deletedTask.toString()
                + "\nNow you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Prints the message when a task is successfully marked as done.
     *
     * @param task The task after it is successfully marked as done.
     */
    public String printMarkDoneSuccessMessage(Task task) {
        return "Yay! I've marked this task as done:\n" + task.toString();
    }

    /**
     * Prints the message when a task is successfully marked as not done.
     *
     * @param task The task after it is successfully marked as not done.
     */
    public String printMarkUndoneSuccessMessage(Task task) {
        return "Got it! I've marked this task as not done yet:\n" + task.toString();
    }

    /**
     * Prints the message when a task is successfully updated.
     *
     * @param updatedTask The task after it is successfully updated.
     */
    public String printUpdateSuccessMessage(Task updatedTask) {
        return "Yay! I've updated this task to:\n" + updatedTask.toString();
    }

    /**
     * Prints the message when the user requests for help.
     */
    public String printHelpMessage() {
        String header = "No worries, I'm more than happy to help!\n"
                + "Here's the list of commands to get you started:\n";
        String commandsList = "1. todo <description>\n"
                + "2. deadline <description> /by <deadline date>\n"
                + "3. event <description> /from <start date> /to <end date>\n"
                + "4. delete <task index>\n"
                + "5. mark <task index>\n"
                + "6. unmark <task index>\n"
                + "7. list\n"
                + "8. find <keyword>\n"
                + "9. update <task index> <field to update> <new description>\n"
                + "10. bye";
        return header + commandsList;
    }

    public String readCommand() {
        return sc.nextLine();
    }
}
