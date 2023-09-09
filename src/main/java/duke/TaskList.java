package duke;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Creates a new TaskList instance with the provided list of tasks.
     *
     * @param list The initial list of tasks for the TaskList.
     * @throws DukeException If the provided list of tasks is null.
     */
    public TaskList(ArrayList<Task> list) throws DukeException {
        if (list == null) {
            throw new DukeException("Empty taskList");
        } else {
            this.list = list;
        }
    }

    /**
     * Creates an empty TaskList instance.
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    };

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added to the TaskList.
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Deletes a task from the TaskList at the specified index.
     *
     * @param index The index of the task to be deleted.
     * @return The task that was removed from the TaskList.
     */
    public Task delete(int index) {
        Task removedTask = list.remove(index);
        return removedTask;
    }

    /**
     * Retrieves a task from the TaskList at the specified index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return list.get(index);
    }

    /**
     * Gets the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Finds tasks in the task list that contain the specified keyword in their descriptions.
     *
     * @param keyword The keyword to search for within task descriptions.
     * @return An ArrayList of tasks that contain the keyword in their descriptions.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : list) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }

}
