package gbot;

import tasks.Task;
import java.util.ArrayList;

/**
 * A storage class for the purpose of testing.
 *
 * @author Gallen Ong
 */
public class StorageStub extends Storage {
    /**
     * Initialises a StorageStub object.
     */
    public StorageStub() {
        super();
    }

    /**
     * Returns an empty ArrayList object.
     *
     * @return An empty ArrayList.
     */
    @Override
    public ArrayList<Task> loadFile() {
        return new ArrayList<>();
    }

    /**
     * Consumes the task provided.
     *
     * @param message The task to be added in string format.
     */
    @Override
    public void writeToFile(String message) {
        // no effect
    }

    /**
     * Consumes the task list provided.
     *
     * @param list The list of tasks to update the file.
     */
    @Override
    public void rewriteFile(ArrayList<Task> list) {
        // no effect
    }
}
