package sae.util;

import sae.task.Task;

import java.util.ArrayList;

/**
 * The Ui class handles user interface interactions and messages.
 */
public class Ui {

    /**
     * Greets the user and displays a welcome message.
     *
     * @return A welcome message to the user.
     */
    public String greetUser() {
        System.out.println("Hello! I'm Sae\nWhat can I do for you?");
        return "Hello! I'm Sae\nWhat can I do for you?";
    }

    /**
     * Bids farewell to the user.
     *
     * @return A farewell message to the user.
     */
    public String bidGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays a message for unknown user input.
     */
    public void unknownInput() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Generates a message for adding a task.
     *
     * @param task      The task to be added.
     * @param taskcount The total number of tasks after adding.
     * @return A message confirming the addition of the task.
     */
    public String addTaskMessage(Task task, int taskcount) {
        return "Got it. I've added this task:\n" + "  " + task.toString() +
                "\nNow you have " + taskcount + " task(s) in the list.";
    }

    /**
     * Generates a message for deleting a task.
     *
     * @param task      The task to be deleted.
     * @param taskcount The total number of tasks after deletion.
     * @return A message confirming the deletion of the task.
     */
    public String deleteTaskMessage(Task task, int taskcount) {
        return "Noted. I've removed this task:\n"
                + "  " + task.toString() + "\nNow you have " + taskcount + " tasks in the list.";
    }

    /**
     * Generates a message for marking a task as done.
     *
     * @param task The task marked as done.
     * @return A message confirming the task has been marked as done.
     */
    public String markAsDoneMessage(Task task) {
        return "Nice! I've marked this task as done:\n" + task.toString();
    }

    /**
     * Generates a message for unmarking a task as done.
     *
     * @param task The task to be unmarked as done.
     * @return A message confirming the task has been unmarked as done.
     */
    public String unMarkAsDoneMessage(Task task) {
        return "I have unmarked this task as done:\n" + task.toString();
    }

    /**
     * Generates a message for a group delete operation.
     *
     * @param start The start index of the deleted tasks range.
     * @param end   The end index of the deleted tasks range.
     * @param tasks The list of deleted tasks.
     * @return A message confirming the group delete operation.
     */
    public String groupDeleteMessage(int start, int end, ArrayList<Task> tasks) {
        String frontString = "Task " + (start + 1) + " to Task "
                + (end + 1) + " has been deleted.\n The deleted tasks are :\n";
        StringBuilder backString = new StringBuilder();
        for (Task task : tasks) {
            backString.append(task.toString()).append("\n");
        }
        return frontString + backString.toString();
    }
}
