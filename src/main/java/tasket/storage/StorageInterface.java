package tasket.storage;

import java.util.ArrayList;

import tasket.data.TaskList;
import tasket.exception.TasketException;
import tasket.task.Task;

/**
 * An interface for storage operations.
 */
public interface StorageInterface {

    /**
     * Loads the task file and convert it to task list.
     *
     * @return A list of saved tasks.
     * @throws TasketException If the file cannot be found.
     */
    public ArrayList<Task> load() throws TasketException;

    /**
     * Appends a new task to the task list.
     *
     * @param taskSave The task to be saved in save format.
     * @throws TasketException If IO error occurs.
     */
    public void append(String taskSave) throws TasketException;

    /**
     * Rewrites the save file.
     *
     * @param taskList The task list to get the tasks from.
     * @throws TasketException If IO error occurs.
     */
    public void rewriteSaveFile(TaskList taskList) throws TasketException;
}
