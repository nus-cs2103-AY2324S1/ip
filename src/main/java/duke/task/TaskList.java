package duke.task;

import java.util.ArrayList;


public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public void markTask(int index) {
        tasks.get(index).setDone();
    }

    public void unmarkTask(int index) {
        tasks.get(index).setUndone();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public String toStorage() {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.toSaveFormat()).append("\n");
        }
        return sb.toString();
    }
}
