package duke;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import duke.task.Task;

public class TaskList implements Iterable<Task> {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }
    public void add(Task task) {
        this.tasks.add(task);
    }
    public Task delete(int index) {
        return this.tasks.remove(index - 1);
    }
    public Task mark(int index) {
        Task task = this.tasks.get(index - 1);
        task.markDone();
        return task;
    }
    public Task unmark(int index) {
        Task task = this.tasks.get(index - 1);
        task.markUndone();
        return task;
    }
    public int getListSize() {
        return this.tasks.size();
    }
    @Override
    public Iterator<Task> iterator() {
        return this.tasks.iterator();
    }
}
