package services.tasklist;

import services.bizerrors.InvalidArgumentException;
import services.bizerrors.ReadFromFileException;
import services.bizerrors.SaveToFileException;
import services.tasklist.tasks.Task;

import java.util.List;

/**
 * Represents a storage that stores the list of tasks.
 * The storage is responsible for reading and writing the list of tasks to a data file.
 */
public interface IStorage {
    /**
     * Saves the given list of tasks to the data file.
     * The tasks are written to the file in string obtained
     * from calling encode() method of each task.
     *
     * @param taskList the list of tasks to be saved.
     * @throws SaveToFileException if there is an error writing to the file.
     * @see Task#encode()
     */
    void save(List<Task> taskList) throws SaveToFileException;

    /**
     * Loads the list of tasks from the data file.
     * The returned task list is empty if the data file is empty.
     *
     * @return the list of tasks loaded from the data file.
     * @throws ReadFromFileException    if there is an error reading from the file, e,g. file not found.
     * @throws InvalidArgumentException if the data file contains invalid data format that cannot be parsed.
     */
    List<Task> load() throws ReadFromFileException, InvalidArgumentException;
}
