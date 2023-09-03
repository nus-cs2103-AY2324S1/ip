package com.alpha.ui;

import java.util.List;

import com.alpha.tasks.Task;
import com.alpha.tasks.TaskList;

/**
 * The type Ui.
 */
public class Ui {

    /**
     * Instantiates a new Ui.
     */
    public Ui() {
    }

    /**
     * Returns the welcome string.
     *
     * @return the welcome string
     */
    public String welcome() {
        return "Hello! I'm Alpha.\n" + "What can I do for you?\n";
    }

    /**
     * Returns the goodbye string.
     *
     * @return the goodbye string
     */
    public String goodbye() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Returns the list of tasks string.
     *
     * @param tasks the list of tasks
     * @return the list of tasks string
     */
    public String displayTasks(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        int count = 1;
        for (Task task : tasks) {
            String row = count++ + "." + task.toString() + "\n";
            sb.append(row);
        }
        return sb.toString();
    }

    /**
     * Returns the add task string.
     *
     * @param task     the task
     * @param taskList the task list
     * @return the add task string
     */
    public String addTask(Task task, TaskList taskList) {
        return "Got it. I've added this task:\n" + task.toString() + "\n"
                + "Now you have " + taskList.getSize() + " tasks in the list.\n";
    }

    /**
     * Returns the mark task string.
     *
     * @param task the task
     * @return the mark task string
     */
    public String markTask(Task task) {
        return "Nice! I've marked this task as done:\n " + task.toString();
    }

    /**
     * Returns the unmark task string.
     *
     * @param task the task
     * @return the unmark task string
     */
    public String unmarkTask(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task.toString();
    }

    /**
     * Returns the delete task string.
     *
     * @param task     the task
     * @param taskList the task list
     * @return the delete task string
     */
    public String deleteTask(Task task, TaskList taskList) {
        return "Noted. I've removed this task:\n" + task.toString() + "\n"
                + "Now you have " + taskList.getSize() + " tasks in the list.\n";
    }

    /**
     * Returns the loading error string.
     *
     * @return the loading error string
     */
    public String loadingError() {
        return "Error loading. Please try again.\n";
    }
}
