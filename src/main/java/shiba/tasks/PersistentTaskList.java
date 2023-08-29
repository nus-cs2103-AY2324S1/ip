package shiba.tasks;

import shiba.exceptions.ShibaException;

import java.util.List;

public interface PersistentTaskList {
    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    void addTask(ShibaTask task);

    /**
     * Removes the task at the specified index.
     *
     * @param index Index of the task to remove.
     */
    ShibaTask removeIndex(int index);

    /**
     * Gets the task at the specified index.
     *
     * @param index Index of the task to get.
     * @return The task at the specified index.
     */
    ShibaTask get(int index);

    /**
     * Gets the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    int size();

    /**
     * Saves the current task list state to storage.
     *
     * @throws ShibaException If there is an error saving the tasks.
     */
    void save() throws ShibaException;

    /**
     * Finds tasks containing the specified keyword.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks containing the specified keyword.
     */
    List<ShibaTask> findTasksWithKeyword(String keyword);
}
