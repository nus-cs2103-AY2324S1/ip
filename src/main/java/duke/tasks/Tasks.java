package duke.tasks;

import java.util.ArrayList;
import java.util.List;

public class Tasks {
    private final List<Task> tasks = new ArrayList<>();

    public int size() {
        return this.tasks.size();
    }

    public Task get(int idx) {
        return this.tasks.get(idx);
    }

    public Task remove(int idx) {
        return this.tasks.remove(idx);
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task getTaskByIndex(int id) {
        if (id > this.tasks.size() || id <= 0) {
            return null;
        }

        return this.tasks.get(id - 1);
    }

    public List<Task> getAll() {
        return this.tasks;
    }
}
