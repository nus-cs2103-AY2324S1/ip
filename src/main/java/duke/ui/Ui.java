package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.DukeList;
import duke.tasks.Task;

/**
 * The Ui class handles interactions with the user, including displaying messages and receiving input.
 */
public class Ui {
    private Scanner input;

    /**
     * Initializes a new Ui object with a Scanner for user input.
     */
    public Ui() {
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public String showList(DukeList tasks) {
        StringBuilder s = new StringBuilder("Here are the tasks in your list:\n");
        int len = tasks.getSize();
        for (int i = 0; i < len; i++) {
            int num = i + 1;
            Task currTask = tasks.getTask(i);
            s.append(num).append(". ").append(currTask.toString() + "\n");
        }
        return s.toString();
    }

    /**
     * Displays an error message when there is an issue loading past data.
     */
    public String showLoadingError() {
        return "Issues loading past data. Creating a new tasklist from scratch";
    }

    /**
     * Acknowledges the addition of a task to the task list.
     *
     * @param size The current size of the task list.
     * @param task The task that was added.
     */
    public String acknowledgeAdd(int size, Task task) {
        return "Added the following task to the list:\n"
                + size + ". " + task.toString() + "\nYou currently have "
                + size + " tasks in your list.";
    }

    /**
     * Acknowledges the deletion of a task from the task list.
     *
     * @param index The index of the task that was deleted.
     * @param task The task that was deleted.
     */
    public String acknowledgeDelete(int index, Task task) {
        return "Noted. I've removed this task:\n" + index + ". " + task.toString();
    }

    /**
     * Acknowledges the result of a keyword-based task search.
     *
     * @param filteredTasks The list of tasks matching the search criteria.
     */
    public String acknowledgeFind(ArrayList<Task> filteredTasks) {
        if (filteredTasks.size() == 0) {
            return "No tasks with the given keyword can be found";
        } else {
            StringBuilder s = new StringBuilder("Here are the matching tasks in your list:\n");
            int len = filteredTasks.size();
            for (int i = 0; i < len; i++) {
                int num = i + 1;
                Task currTask = filteredTasks.get(i);
                s.append(num).append(". ").append(currTask.toString() + "\n");
            }
            return s.toString();
        }
    }

    /**
     * Acknowledges the marking of a task as done.
     *
     * @param index The index of the task that was marked as done.
     * @param task The task that was marked as done.
     */
    public String acknowledgeMark(int index, Task task) {
        return "Nice! I've marked this task as done:" + "\n" + task.toString();
    }

    /**
     * Acknowledges the marking of a task as not done yet.
     *
     * @param index The index of the task that was marked as not done yet.
     * @param task The task that was marked as not done yet.
     */
    public String acknowledgeUnmark(int index, Task task) {
        return "OK, I've marked this task as not done yet:" + "\n" + task.toString();
    }

    /**
     * Displays a farewell message when the application is exiting.
     */
    public String exit() {
        return "Bye. Hope to see you again soon!";
    }
}
