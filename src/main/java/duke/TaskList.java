package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * The TaskList class contains the task list and the related operations.
 *
 * @author Inez Kok
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * The constructor for an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * The constructor for a TaskList.
     *
     * @param tasks The array list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of tasks.
     *
     * @return Returns the array list of tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Returns the specific task in the task list.
     *
     * @param index The zero-based index of the task.
     * @return Returns the task with that index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Marks the specific task in the task list as done.
     *
     * @param index The zero-based index of the task.
     */
    public void markTask(int index) {
        tasks.get(index).markDone();
    }

    /**
     * Marks the specific task in the task list as not done.
     *
     * @param index The zero-based index of the task.
     */
    public void unmarkTask(int index) {
        tasks.get(index).markNotDone();
    }

    /**
     * Adds a task into the task list.
     *
     * @param task The task being added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list.
     *
     * @param index The zero-based index of the task.
     */
    public void delete(int index) {
        tasks.remove(index);
    }

    /**
     * Retrieves the number of tasks in the task list.
     *
     * @return The number of tasks in the task lists.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Finds tasks in the task list containing the keyword.
     *
     */
    public TaskList find(String keyword) {
        TaskList filteredTasks = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(keyword)) {
                filteredTasks.add(tasks.get(i));
            }
        }
        return filteredTasks;
    }
}
