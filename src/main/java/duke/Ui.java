package duke;

import java.util.ArrayList;


/**
 * Return strings containing messages to interact with user.
 *
 * @author Qin Yan Er
 */
public class Ui {

    /**
     * Creates a new Ui instance.
     */
    public Ui() {}

    /**
     * Generates the welcome message when program is first started.
     *
     * @return A String showing the welcome message
     */
    public String showIntro() {
        String s = "Hello! I'm duke.Duke.\nWhat can I do for you?";
        return s;
    }

    /**
     * Generates a goodbye message.
     *
     * @return A string showing the goodbye message.
     */
    public String showBye() {
        String s = "Bye. Hope to see you again soon!";
        return s;
    }

    /**
     * Generates a message when a task is removed, indicating the removed task and the updated task count.
     *
     * @param list The list of tasks.
     * @param removedTask The task to be removed.
     * @return A String message indicating the removal and the updated task count.
     */
    public String removeTask(TaskList list, Task removedTask) {
        String s = "Noted. I've removed this task:\n" + removedTask.toString() +
                "\nNow you have " + list.getSize() + " tasks in the list.";
        return s;
    }

    /**
     * Generates a message when the user requests to list all tasks.
     *
     * @param list The list of tasks.
     * @return A String message listing all tasks or indicating that there are no tasks.
     */
    public String printList(TaskList list) {
        StringBuilder stringBuilder = new StringBuilder();

        if (list.getSize() == 0) {
            stringBuilder.append("There are no tasks in your list.");
        } else {
            stringBuilder.append("Here are the tasks in your list:\n");
            for (int i = 0; i < list.getSize(); i++) {
                stringBuilder.append((i + 1) + ". " + list.getTask(i).toString() + "\n");
            }
        }

        return stringBuilder.toString();
    }

    /**
     * Generates a message when the user requests to list matching tasks.
     *
     * @param matchingTasks The list of matching tasks.
     * @return A String message listing matching tasks.
     */
    public String printMatchingTasks(ArrayList<Task> matchingTasks) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the matching tasks in your list:\n");

        for (int i = 0; i < matchingTasks.size(); i++) {
            stringBuilder.append((i + 1) + ". " + matchingTasks.get(i).toString() + "\n");
        }

        return stringBuilder.toString();
    }

    /**
     * Generates a message when a task is marked as done.
     *
     * @param list The list of tasks.
     * @param number The task number that was marked as done.
     * @return A String message indicating the task was marked as done.
     */
    public String mark(TaskList list, int number) {
        String s = "Nice! I've marked this task as done:\n" + list.getTask(number).toString();
        return s;
    }

    /**
     * Generates a message when a task is unmarked.
     *
     * @param list The list of tasks.
     * @param number The task number that was unmarked.
     * @return A String message indicating the task was unmarked.
     */
    public String unMark(TaskList list, int number) {
        String s = "OK, I've marked this task as not done yet:\n" + list.getTask(number).toString();
        return s;
    }

    /**
     * Generates a message when the user wants to add a "todo" to the task list.
     *
     * @param list The list of tasks.
     * @param newTodo The new todo task to be added.
     * @return A String message indicating the addition of the new todo task.
     */
    public String addTodo(TaskList list, Todo newTodo) {
        String s = "Got it. I've added this task:\n" + newTodo.toString() +
                "\nNow you have " + list.getSize() + " tasks in the list.";
        return s;
    }

    /**
     * Generates a message when the user wants to add a "deadline" to the task list.
     *
     * @param list The list of tasks.
     * @param newDeadline The new deadline task to be added.
     * @return A String message indicating the addition of the new deadline task.
     */
    public String addDeadline(TaskList list, Deadline newDeadline) {
        String s = "Got it. I've added this task:\n" + newDeadline.toString() +
                "\nNow you have " + list.getSize() + " tasks in the list.";
        return s;
    }

    /**
     * Generates a message when the user wants to add an "event" to the task list.
     *
     * @param list The list of tasks.
     * @param newEvent The new event task to be added.
     * @return A String message indicating the addition of the new event task.
     */
    public String addEvent(TaskList list, Event newEvent) {
        String s = "Got it. I've added this task:\n" + newEvent.toString() +
                "\nNow you have " + list.getSize() + " tasks in the list.";
        return s;
    }

}
