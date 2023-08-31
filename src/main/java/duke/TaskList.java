package duke;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TaskList extends ArrayList<Task> {
    ArrayList<Task> tasks;
    public TaskList() {
        tasks = new ArrayList<>();
    }

    @Override
    public boolean add(Task task) {
        tasks.add(task);
        return false;
    }


    public Task get(int j) {
        return tasks.get(j);
    }

    public Task remove(int i) {
        tasks.remove(i);
        return null;
    }

    @Override
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
    @Override
    public int size() {
        return tasks.size();
    }
    @Override
    public boolean contains(Object obj) {
        return tasks.contains(obj);
    }
    @Override
    public void forEach(Consumer<? super Task> action) {
        tasks.forEach(action);
    }
}
