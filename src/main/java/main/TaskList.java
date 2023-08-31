package main;

import exception.DialogixException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void markTaskAsDone(int index) throws DialogixException {
        if (index < 0 || index >= tasks.size()) {
            throw new DialogixException("Task index is out of range.");
        }
        tasks.get(index).markAsDone();
    }

    public void deleteTask(int index) throws DialogixException {
        if (index < 0 || index >= tasks.size()) {
            throw new DialogixException("Task index is out of range.");
        }
        tasks.remove(index);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<Task> findTasks(String keyword) {
        return tasks.stream()
            .filter(task -> task.getDescription().contains(keyword))
            .collect(Collectors.toList());
    }
}
