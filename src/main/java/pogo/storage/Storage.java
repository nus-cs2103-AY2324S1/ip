package pogo.storage;

import java.io.IOException;
import java.util.List;

import pogo.tasks.Task;

/**
 * Storage interface for loading and saving tasks.
 */
public interface Storage {
    /**
     * Saves the given list of tasks.
     *
     * @param tasks the list of tasks to save.
     */
    void save(List<Task> tasks);

    /**
     * Loads the list of tasks.
     *
     * @return the list of tasks.
     * @throws IOException if there is an error loading the list of tasks.
     */
    List<Task> load() throws IOException;
}
