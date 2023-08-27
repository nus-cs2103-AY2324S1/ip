package tasks;

import exceptions.ShibaException;
import filehandler.Storage;
import ui.Replier;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<ShibaTask> tasks = new ArrayList<>();
    private final Storage storage;

    public TaskList(String dataPath) {
        storage = new Storage(dataPath);

        try {
            tasks.addAll(storage.readSavedTasks());
        } catch (ShibaException e) {
            Replier.printException(e);
        }
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void addTask(ShibaTask task) {
        tasks.add(task);
    }

    /**
     * Removes the task at the specified index.
     *
     * @param index Index of the task to remove.
     */
    public ShibaTask removeIndex(int index) {
        return tasks.remove(index);
    }

    /**
     * Gets the task at the specified index.
     *
     * @param index Index of the task to get.
     * @return The task at the specified index.
     */
    public ShibaTask get(int index) {
        return tasks.get(index);
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Saves the current task list state to storage.
     *
     * @throws ShibaException If there is an error saving the tasks.
     */
    public void save() throws ShibaException {
        storage.saveTasks(tasks);
    }
}
