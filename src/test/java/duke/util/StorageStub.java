package duke.util;

import java.util.List;

import duke.task.Task;

/**
 * Represents a storage stub.
 * A <code>StorageStub</code> object corresponds to a storage
 * that does not actually write to a file. It is used for testing.
 */
public class StorageStub extends Storage {

    public StorageStub() {
        super("", "");
    }

    @Override
    public List<Task> loadTasks(boolean isLoadDefault, String alterFileName) {
        return null;
    }

    @Override
    public void createTaskFile() {
    }

    @Override
    public void saveTasks(String[] tasks) {
    }
}
