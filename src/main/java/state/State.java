package state;

import java.util.ArrayList;
import java.util.List;

import util.Pair;

public final class State {
    private final List<Task> tasks;

    public State() {
        tasks = List.of();
    }

    public State(List<Task> tasks) {
        this.tasks = tasks;
    }

    public boolean hasTasks() {
        return !tasks.isEmpty();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int taskId) {
        return tasks.get(taskId - 1);
    }

    public Pair<State, Task> addTask(Task task) {
        ArrayList<Task> tasks = new ArrayList<>(this.tasks);
        tasks.add(task);
        return new Pair<>(new State(tasks), task);
    }

    public Pair<State, Task> deleteTask(int taskId) {
        ArrayList<Task> tasks = new ArrayList<>(this.tasks);
        Task task = tasks.remove(taskId - 1);
        return new Pair<>(new State(tasks), task);
    }

    public Pair<State, Task> markTask(int taskId) {
        ArrayList<Task> tasks = new ArrayList<>(this.tasks);
        Task task = getTask(taskId);
        if (task == null) {
            return new Pair<>(new State(tasks), null);
        }
        Task markedTask = task.mark();
        tasks.set(taskId - 1, markedTask);
        return new Pair<>(new State(tasks), markedTask);
    }

    public Pair<State, Task> unmarkTask(int taskId) {
        ArrayList<Task> tasks = new ArrayList<>(this.tasks);
        Task task = getTask(taskId);
        if (task == null) {
            return new Pair<>(new State(tasks), null);
        }
        Task unmarkedTask = task.unmark();
        tasks.set(taskId - 1, unmarkedTask);
        return new Pair<>(new State(tasks), unmarkedTask);
    }
}
