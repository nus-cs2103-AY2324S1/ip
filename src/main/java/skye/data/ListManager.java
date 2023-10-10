package skye.data;

import java.io.IOException;

import skye.data.exception.DukeException;
import skye.storage.StorageManager;

/**
 * Represents a controller that manages lists of tasks and venues
 */
public class ListManager {
    private TaskList taskList;
    private VenueList venueList;

    /**
     * Initializes a list controller by passing in the context of the storage manager.
     *
     * @param storageManager StorageManager
     * @throws DukeException Describes the error encountered when executing the command
     * @throws IOException Describes the I/O error encountered in the OS file system
     */
    public ListManager(StorageManager storageManager) throws DukeException, IOException {
        taskList = new TaskList(storageManager.loadTasks());
        venueList = new VenueList(storageManager.loadVenues());
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public VenueList getVenueList() {
        return venueList;
    }
}
