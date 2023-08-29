package zean;

import java.util.ArrayList;

import zean.task.Task;

public class StorageStub extends Storage {
    public StorageStub() {
        super();
    }

    @Override
    public ArrayList<Task> load() {
        return new ArrayList<>();
    }

    @Override
    public void addToDisk(Task task) {
        // does nothing
    }

    @Override
    public void rewriteToDisk(ArrayList<Task> tasks) {
        // does nothing
    }
}
