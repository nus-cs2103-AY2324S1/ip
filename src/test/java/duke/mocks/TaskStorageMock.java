package duke.mocks;

import java.util.ArrayList;
import java.util.List;

import duke.task.Task;
import duke.task.TaskStorage;

/**
 * Mock TaskStorage to aid testing.
 */
public class TaskStorageMock extends TaskStorage {
    private final ArrayList<Task> tasks;

    /**
     * Initialise TaskStorageMock.
     * @param tasks Tasks in this mock storage.
     */
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
