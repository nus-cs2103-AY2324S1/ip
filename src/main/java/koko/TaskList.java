package koko;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTaskAtIndex(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid task number.");
        }
        return tasks.remove(index);
    }

    public Task markTaskAtIndex(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid task number.");
        }
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    public Task unmarkTaskAtIndex(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid task number.");
        }
        Task task = tasks.get(index);
        task.markAsUndone();
        return task;
    }

    /**
     * Returns a new TaskList containing tasks that match the given keyword.
     *
     * @param keyword The keyword to search for.
     * @return A new TaskList containing tasks that match the given keyword.
     */
    public TaskList findTasksFromKeyword(String keyword) {
        ArrayList<Task> result = tasks.stream()
                .filter(task -> task.getName().contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new));
        return new TaskList(result);
    }

    public int size() {
        return tasks.size();
    }

    public String toStringForUi() {
        return IntStream.range(0, tasks.size())
                .mapToObj(i -> (i + 1) + ". " + tasks.get(i))
                .collect(Collectors.joining("\n"));
    }

    public String toStringForFile() {
        return tasks.stream()
                .map(Task::toFileFormat)
                .collect(Collectors.joining("\n"));
    }

}
