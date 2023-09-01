package duke.main;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void remove(Task task) {
        tasks.remove(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Task getTaskAtIndex(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    /**
     * Filters and returns a list of tasks meeting the given predicate.
     *
     * @param predicate The predicate specified by the user.
     * @return A list of tasks meeting the given predicate.
     */
    public List<Task> filterTasks(Predicate<? super Task> predicate) {
        return this.tasks.stream().filter(predicate).collect(Collectors.<Task>toList());
    }
}