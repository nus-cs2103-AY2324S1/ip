package cyrus.storage;

import java.util.List;

import cyrus.tasks.Task;

/**
 * Interface to allow different types of storage to be used.
 */
public interface IStorage {
    /**
     * Loads tasks from storage location into list of {@code Task}.
     *
     * @return list of {@code Task} from storage.
     */
    List<Task> load();

    /**
     * Saves tasks to storage location.
     */
    void save(List<Task> tasks);
}
