package pogo.storage;

import pogo.tasks.Task;

import java.io.IOException;
import java.util.List;

public interface Storage {
    void save(List<Task> tasks);
    List<Task> load() throws IOException;
}
