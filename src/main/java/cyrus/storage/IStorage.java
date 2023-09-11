package cyrus.storage;

import java.util.List;

import cyrus.tasks.Task;

/**
 * Interface to allow different types of storage to be used.
 */
public interface IStorage {
    List<Task> load();

    void save(List<Task> tasks);
}
