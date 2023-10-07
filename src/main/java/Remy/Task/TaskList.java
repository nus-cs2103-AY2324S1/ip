package remy.task;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A TaskList that stores Tasks created by the user.
 */
public class TaskList implements Serializable {
    private ArrayList<Task> tasks;

    /**
     * Creates new TaskList with no Tasks inside.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Creates a TaskList using an existing TaskList.
     *
     * @param tasks Existing TaskList to retrieve Tasks from.
     */
    public TaskList(TaskList tasks) {
        this.tasks = tasks.tasks;
    }

    /**
     * Returns Task at index i.
     *
     * @param i Index of the desired Task.
     * @return Task at index i
     */
    public Task get(int i) {
        return this.tasks.get(i);
    }

    /**
     * Returns number of Tasks in the TaskList.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Removes Task at index i.
     *
     * @param i Index of Task to be removed.
     */
    public void remove(int i) {
        this.tasks.remove(i);
    }

    /**
     * Adds Task to the end of TaskList.
     *
     * @param task Task to be added to the TaskList.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Returns String representation of all TaskList Tasks that match the given searchString.
     *
     * @param searchString To compare against Tasks in TaskList.
     */
    public String findMatchingTasks(String searchString) {
        String matchingTasksString = "";
        for (Task task : this.tasks) {
            if (task.getDescription().toLowerCase().contains(searchString)) {
                matchingTasksString += task + "\n";
            }
        }
        return matchingTasksString;
    }

    /**
     * Returns String representation of the TaskList.
     *
     * @return TaskList in a multi-line String, with each Task on its own line.
     */
    @Override
    public String toString() {
        String taskString = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            taskString += (" " + (i + 1) + ". " + this.tasks.get(i) + "\n");
        }

        if (taskString.equals("")) {
            return "You have not created any tasks.";
        }
        return taskString;
    }
}
