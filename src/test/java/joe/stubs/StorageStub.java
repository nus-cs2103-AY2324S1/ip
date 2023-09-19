package joe.stubs;

import joe.Storage;
import joe.TaskList;
/**
 * A stub implementation of Storage for testing.
 */
public class StorageStub extends Storage {
    public StorageStub() {
        super("test.txt");
    }

    @Override
    public void saveToFile(TaskList tasks) {
        // Do nothing for the stub
    }
}
