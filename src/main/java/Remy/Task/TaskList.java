package Remy.Task;

import java.io.Serializable;
import java.util.ArrayList;

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
     * @param tasks
     */
    public TaskList(TaskList tasks) {
        this.tasks = tasks.tasks;
    }

    /**
     * Returns Task at index i
     * @param i Index of the desired Task.
     * @return Task at index i
     */
    public Task get(int i) {
        return this.tasks.get(i);
    }

    /**
     * @return length of TaskList
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Removes Task at index i
     * @param i Index of Task to be removed.
     */
    public void remove(int i) {
        this.tasks.remove(i);
    }

    /**
     * Adds Task to the end of TaskList.
     * @param task Task to be added to the TaskList.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Returns String representation of the TaskList.
     * @return TaskList in a multi-line String, with each Task on its own line.
     */
    @Override
    public String toString() {
        String taskString = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            taskString += (" " + (i + 1) + ". " + this.tasks.get(i) + "\n");
        }
        return taskString;
    }
}
