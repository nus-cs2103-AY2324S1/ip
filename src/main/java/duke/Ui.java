package duke;

import java.util.ArrayList;


/**
 * Prints messages to interact with user.
 *
 * @author Qin Yan Er
 */
public class Ui {

    /**
     * Creates a new Ui instance.
     */
    public Ui() {}

    /**
     * Returns the welcome message when program just started running.
     *
     * @return a String showing the welcome message
     */
    public String showIntro() {
        String s = "Hello! I'm duke.Duke.\nWhat can I do for you?";
        return s;
    }

    /**
     * Prints when program is unable to load tasks from file.
     */
    public String showLoadingError() {
        String s = "Loading Error";
        return s;
    }

    /**
     * Prints when user exits the program.
     */
    public String showBye() {
        String s = "Bye. Hope to see you again soon!";
        return s;
    }

    /**
     * Prints the task to be removed and how many tasks are left in the list.
     *
     * @param list the list of tasks.
     * @param removedTask the task to be removed.
     */
    public String removeTask(TaskList list, Task removedTask) {
        assert list != null : "Task list cannot be empty.";
        assert removedTask != null : "Removed task cannot be null.";

        String s = "Noted. I've removed this task:\n" + removedTask.toString() +
                "\nNow you have " + list.getSize() + " tasks in the list.";
        return s;
    }

    /**
     * Prints when users asks for the list of tasks.
     *
     * @param list the list of tasks.
     */
    public String printList(TaskList list) {
        assert list != null : "Task list cannot be empty.";

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

    public String printMatchingTasks(ArrayList<Task> matchingTasks) {
        assert matchingTasks != null : "matchingTasks list cannot be empty.";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the matching tasks in your list:\n");

        for (int i = 0; i < matchingTasks.size(); i++) {
            stringBuilder.append((i + 1) + ". " + matchingTasks.get(i).toString() + "\n");
        }

        return stringBuilder.toString();
    }

    /**
     * Prints when user marks a task as done.
     *
     * @param list the list of tasks.
     * @param index the task number to be marked as done.
     */
    public String mark(TaskList list, int index) {
        assert list != null : "Task list cannot be empty.";
        assert index >= 0 && index < list.getSize() : "Task index is out of bounds";

        String s = "Nice! I've marked this task as done:\n" + list.getTask(index).toString();
        return s;
    }

    /**
     * Prints when user unmarks a done task back to not done yet.
     *
     * @param list the list of tasks.
     * @param index the task number to be unmarked.
     */
    public String unMark(TaskList list, int index) {
        assert list != null : "Task list cannot be empty.";
        assert index >= 0 && index < list.getSize() : "Task index is out of bounds";

        String s = "OK, I've marked this task as not done yet:\n" + list.getTask(index).toString();
        return s;
    }

    /**
     * Prints when user wants to add a todo to the task list.
     *
     * @param list the list of tasks.
     * @param newTodo the new todo to be added.
     */
    public String addTodo(TaskList list, Todo newTodo) {
        assert list != null : "Task list cannot be empty.";
        assert newTodo != null : "New todo cannot be null.";

        String s = "Got it. I've added this task:\n" + newTodo.toString() +
                "\nNow you have " + list.getSize() + " tasks in the list.";
        return s;
    }

    /**
     * Prints when user wants to add a deadline to task list.
     *
     * @param list the list of tasks.
     * @param newDeadline the new deadline to be added.
     */
    public String addDeadline(TaskList list, Deadline newDeadline) {
        assert list != null : "Task list cannot be empty.";
        assert newDeadline != null : "New deadline cannot be null.";

        String s = "Got it. I've added this task:\n" + newDeadline.toString() +
                "\nNow you have " + list.getSize() + " tasks in the list.";
        return s;
    }

    /**
     * Prints when user wants to add an event to task list.
     *
     * @param list the list of tasks.
     * @param newEvent the new event to be added.
     */
    public String addEvent(TaskList list, Event newEvent) {
        assert list != null : "Task list cannot be empty.";
        assert newEvent != null : "New event cannot be null.";

        String s = "Got it. I've added this task:\n" + newEvent.toString() +
                "\nNow you have " + list.getSize() + " tasks in the list.";
        return s;
    }

}
