package Remy.Task;

import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
    private ArrayList<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(TaskList tasks) {
        this.tasks = tasks.tasks;
    }

    /**
     * Returns Remy.Remy.Task.Remy.Remy.Task at index i
     * @param i
     * @return Remy.Remy.Task.Remy.Remy.Task at index i
     */
    public Task get(int i) {
        return this.tasks.get(i);
    }

    /**
     * @return length of Remy.Remy.Task.Remy.Remy.Task.TaskList
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Removes Remy.Remy.Task.Remy.Remy.Task at index i
     * @param i
     */
    public void remove(int i) {
        this.tasks.remove(i);
    }

    /**
     * Adds Task to the end of Remy.Remy.Task.Remy.Remy.Task.TaskList.
     * @param task
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    @Override
    public String toString() {
        String taskString = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            taskString += (" " + (i + 1) + ". " + this.tasks.get(i) + "\n");
        }
        return taskString;
    }
}
