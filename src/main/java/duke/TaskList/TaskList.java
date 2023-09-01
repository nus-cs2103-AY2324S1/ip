package duke.TaskList;

import duke.Task.Task;

import java.util.ArrayList;

/**
 * Combine all tasks into single list.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Create the list for the tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Add a new task to the list.
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Delete a task from the list
     * @param index Index of the task to be deleted.
     */
    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Get task from the list.
     * @param index Index of the task.
     * @return Task that correspond to the index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Get the number of task in the list.
     * @return Size of the list.
     */
    public int getNumberOfTask() {
        return this.tasks.size();
    }
}
