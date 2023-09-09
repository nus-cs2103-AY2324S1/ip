package dre.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dre.exception.DreException;

public class TaskList {

    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    // Delete dre.task from the list by index
    public Task delete(int index) {
        return tasks.remove(index);
    }

    // Mark dre.task as done by index
    public void mark(int index) throws DreException {
        try {
            Task task = tasks.get(index - 1);
            task.done();
        } catch (IndexOutOfBoundsException e) {
            throw new DreException("Invalid dre.task index.");
        }
    }

    // Unmark dre.task by index
    public void unmark(int index) throws DreException {
        try {
            Task task = tasks.get(index - 1);
            task.undo();
        } catch (IndexOutOfBoundsException e) {
            throw new DreException("Invalid dre.task index.");
        }
    }

    public Task getTask(int index) throws DreException {
        try {
            return tasks.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DreException("Invalid dre.task index.");
        }
    }

    public void deleteTask(int index) throws DreException {
        try {
            tasks.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DreException("Invalid dre.task index.");
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public TaskList findTasksByKeyword(String keyword) {
        List<Task> foundTasks = this.tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());
        return new TaskList(foundTasks);
    }

    public int size() {
        return tasks.size();
    }
}
