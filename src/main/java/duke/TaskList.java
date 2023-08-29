package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int index) {
        tasks.remove(index);
    }

    public String stringifyTasks() {
        return tasks.stream().map(Task::encodeTask).collect(Collectors.joining("\n"));
    }

    public int getSize() {
        return tasks.size();
    }

    /**
     * Finds tasks that match the specified keyword.
     *
     * @param keyword The keyword to search for.
     * @return A task list containing tasks that match the specified keyword.
     */
    public TaskList findTasks(String keyword) {
        return new TaskList(tasks.stream()
                .filter(task -> task.matchDescription(keyword))
                .collect(Collectors.toList()));
    }

}
