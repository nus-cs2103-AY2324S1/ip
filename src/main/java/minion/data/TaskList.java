package minion.data;

import minion.data.exception.IllegalValueException;
import minion.data.task.Task;

import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    private void checkIndex(int taskIdx) throws IllegalValueException {
        if(taskIdx < 0 || taskIdx >= tasks.size()) {
            throw new IllegalValueException("☹ OOPS!!! Please enter a valid task number.");
        }
    }

    public Task markTask(int taskIdx) throws IllegalValueException {
        checkIndex(taskIdx);
        Task currTask = tasks.get(taskIdx);
        currTask.markDone();
        return currTask;
    }

    public Task unmarkTask(int taskIdx) throws IllegalValueException {
        checkIndex(taskIdx);
        Task currTask = tasks.get(taskIdx);
        currTask.markUndone();
        return currTask;
    }

    public Task deleteTask(int taskIdx) throws IllegalValueException {
        checkIndex(taskIdx);
        Task currTask = tasks.get(taskIdx);
        tasks.remove(taskIdx);
        return currTask;
    }

    public int size() {
        return tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:");
        for(int i = 0; i < tasks.size(); i++) {
            sb.append("\n\t");
            sb.append(i + 1);
            sb.append(".");
            sb.append(tasks.get(i).toString());
        }
        return sb.toString();
    }

    public String toStringStorage() {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.toStringStorage());
            sb.append("\n");
        }
        return sb.toString();
    }
}
