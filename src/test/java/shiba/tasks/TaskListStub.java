package shiba.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a stub for a persistent task list, which will not perform saving or loading of data from file.
 */
public class TaskListStub implements PersistentTaskList {
    private final ArrayList<ShibaTask> tasks = new ArrayList<>();

    @Override
    public void addTask(ShibaTask task) {
        tasks.add(task);
    }

    @Override
    public ShibaTask removeIndex(int index) {
        return tasks.remove(index);
    }

    @Override
    public ShibaTask get(int index) {
        return tasks.get(index);
    }

    @Override
    public int size() {
        return tasks.size();
    }

    @Override
    public void save() {
        // This stub will not save to file
    }

    @Override
    public List<ShibaTask> findTasksWithKeyword(String keyword) {
        return new ArrayList<>();
    }
}
