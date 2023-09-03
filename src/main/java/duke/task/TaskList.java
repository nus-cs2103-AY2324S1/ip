package duke.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks. It contains the task list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor with no parameter.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructor with tasks.
     *
     * @param tasks Tasks in the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Size of list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Adds task to the list.
     *
     * @param task Task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes task from the list.
     *
     * @param index Index of the task to be removed from the list.
     */
    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        } else {
            throw new IndexOutOfBoundsException("Invalid task index");
        }
    }

    /**
     * Returns a task in the list which is at a particular index.
     *
     * @param index Index of the task to be returned.
     * @return Task in the list at the index indicated.
     */
    public Task getTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        } else {
            throw new IndexOutOfBoundsException("Invalid task index");
        }
    }

    /**
     * Returns tasks in the list.
     *
     * @return Tasks present in the list.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

}
