package cyrus.storage;

import java.util.List;

import cyrus.tasks.Task;

/**
 * Interface to allow different types of storage to be used.
 */
public interface IStorage {
    public List<Task> load();

    public void save(List<Task> tasks);
}
