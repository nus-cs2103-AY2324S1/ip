package duke;

import duke.task.Task;
import duke.utility.Storage;

/**
 * A stub class extending Storage for testing purposes.
 * This class overrides the saveTask method to do nothing.
 */
public class StorageStub extends Storage {

    /**
     * Creates a new StorageStub with a default file path.
     */
    public StorageStub() {
        super("src/main/data/duke.txt");
    }

    /**
     * Overrides the saveTask method to do nothing.
     * This method is used for testing and does not save tasks.
     *
     * @param task The task to be saved (not saved in this stub implementation).
     */
    @Override
    public void saveTask(Task task) {
        // do nothing
    }
}
