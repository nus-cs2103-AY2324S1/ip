package joe.stubs;

import joe.TaskList;
import joe.tasks.Task;
/**
 * A stub implementation of TaskList for testing.
 */
public class TaskListStub extends TaskList {
    private int size;

    public TaskListStub(int size) {
        this.size = size;
    }

    @Override
    public void add(Task task) {
        this.size++;
    }

    @Override
    public void remove(int idx) {
        this.size--;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        return "TaskListStub";
    }

    @Override
    public Task get(int idx) {
        return new TaskStub();
    }
}
