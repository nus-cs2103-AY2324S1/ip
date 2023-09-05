package duke;

import duke.exceptions.InsufficientArgumentsException;
import duke.exceptions.StorageCreationException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a stub class for storage.
 */
public class StorageStub implements Storage {

    public List<Task> storage;

    public StorageStub() {
        LocalDateTime dateTimeMidnight = LocalDateTime.of(2023, 9, 5, 0, 0);
        LocalDateTime dateTimeNoon = LocalDateTime.of(2023, 9, 5, 12, 0);

        this.storage = new ArrayList<>(Arrays.asList(
                new ToDo("Go for health checkup"),
                new Deadline("CS2103T quiz", dateTimeNoon),
                new Event("CS2103T meeting", dateTimeMidnight, dateTimeNoon)
        ));
    }

    /**
     * Retrieves a list of tasks stored within this storage stub.
     *
     * @return The list of task stored in this stub.
     * @throws InsufficientArgumentsException Never thrown by stub implementation.
     * @throws DateTimeParseException         Never thrown by stub implementation.
     * @throws StorageCreationException       Never thrown by stub implementation.
     * @throws IOException                    Never thrown by stub implementation.
     */
    @Override
    public List<Task> readStorage() throws InsufficientArgumentsException, DateTimeParseException,
            StorageCreationException, IOException {
        return this.storage;
    }

    /**
     * Updates the storage stub in the storage instance.
     *
     * @throws IOException Never thrown by stub implementation.
     */
    @Override
    public void updateStorage(List<Task> tasks) throws IOException {
        this.storage.clear();
        this.storage.addAll(tasks);
    }

}