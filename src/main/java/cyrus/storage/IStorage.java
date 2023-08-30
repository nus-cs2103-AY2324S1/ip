package cyrus.storage;

import cyrus.tasks.Task;

import java.util.List;

/**
 * Interface to allow different types of storage to be used.
 */
public interface IStorage {
  public List<Task> load();
  public void save(List<Task> tasks);
}
