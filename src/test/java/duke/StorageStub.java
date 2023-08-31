package duke;

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
    public void changeFile(Keyword keyword, int taskNum) {
    }

    @Override
    public List<Task> load() {
        return null;
    }

    @Override
    public void createFile() {
    }

    @Override
    public void appendFile(String text) {
    }
}
