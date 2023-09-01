package duke;

import duke.tasks.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TaskList implements Serializable {
    private static final long serialVersionUID = 5L;

    private final List<Task> tasks;

    private TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this(new ArrayList<>());
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public TaskList filter(Predicate<? super Task> cond) {
        return new TaskList(tasks.stream().filter(cond).collect(Collectors.toList()));
    }

    public void remove(int i) {
        tasks.remove(i);
    }
}
