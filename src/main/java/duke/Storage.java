package duke;

import duke.exceptions.InsufficientArgumentsException;
import duke.exceptions.StorageCreationException;
import duke.tasks.Task;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Represents the generic interface for the storage of the application.
 */
public interface Storage {
    /**
     * Reads the tasks from storage.
     *
     * @throws InsufficientArgumentsException If there are not enough arguments to create tasks.
     * @throws DateTimeParseException         If the date in storage is formatted wrongly.
     * @throws StorageCreationException       If the storage and directory creation failed.
     * @throws IOException                    If the storage creation failed.
     */
    List<Task> readStorage() throws InsufficientArgumentsException, DateTimeParseException,
            StorageCreationException, IOException;

    /**
     * Rewrites the storage from a list of tasks.
     *
     * @param tasks The list of tasks to be written into storage.
     * @throws IOException If writing to the failed.
     */
    void updateStorage(List<Task> tasks) throws IOException;
}
