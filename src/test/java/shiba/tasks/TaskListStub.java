package shiba.tasks;

import java.util.ArrayList;

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
}
