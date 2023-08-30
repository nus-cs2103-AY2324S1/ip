package cyrus.storage;

import cyrus.tasks.Task;

import java.util.List;

public interface IStorage {
  public List<Task> load();

  public void save(List<Task> tasks);
}
