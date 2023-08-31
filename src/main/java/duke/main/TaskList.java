package duke.main;

import java.util.ArrayList;

import duke.task.Task;

public class TaskList {
    private ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void add(Task task) {
        tasks.add(task);
    }
    public void delete(int index) { tasks.remove(index); }

    public int size() {
        return tasks.size();
    }

    public void markDone(int index) {
        tasks.get(index).markDone();
    }
    public void markNotDone(int index) {
        tasks.get(index).markNotDone();
    }

}
