package zean;

import java.util.ArrayList;

import zean.task.Task;

/**
 * A class that is used for testing. Provides a storage stub.
 *
 * @author Zhong Han
 */
public class StorageStub extends Storage {
    public StorageStub() {
        super();
    }

    /**
     * Returns an empty ArrayList.
     *
     * @return An empty ArrayList
     */
    @Override
    public ArrayList<Task> load() {
        return new ArrayList<>();
    }

    /**
     * Consumes the task. Does nothing as it acts as a stub.
     *
     * @param task The task to be written to disk.
     */
    @Override
    public void addToDisk(Task task) {
        // does nothing
    }

    /**
     * Does nothing as it acts as a stub.
     *
     * @param tasks The ArrayList containing all the updated tasks.
     */
    @Override
    public void rewriteToDisk(ArrayList<Task> tasks) {
        // does nothing
    }
}
