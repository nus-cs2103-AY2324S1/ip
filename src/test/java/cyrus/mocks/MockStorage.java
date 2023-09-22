package cyrus.mocks;

import java.util.ArrayList;
import java.util.List;

import cyrus.storage.IStorage;
import cyrus.tasks.Task;

/**
 * Mocks a {@code IStorage} to load an empty list of predefined tasks.
 */
public class MockStorage implements IStorage {
    @Override
    public List<Task> load() {
        return new ArrayList<>();
    }

    @Override
    public void save(List<Task> tasks) {
        return;
    }
}
