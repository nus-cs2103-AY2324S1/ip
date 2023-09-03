package duke.mocks;

import duke.TaskStorage;
import duke.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskStorageMock extends TaskStorage {
    private final ArrayList<Task> tasks;

    public TaskStorageMock(ArrayList<Task> tasks) {
        super();
        this.tasks = tasks;
    }

    @Override
    public ArrayList<Task> loadExistingTasks() {
        return this.tasks;
    }

    @Override
    public void storeTasks(List<Task> tasks) {
    }
}
