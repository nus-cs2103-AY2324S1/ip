package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    // Fields
    private ArrayList<Task> tasks;

    // Constructors
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    // Methods
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void markTask(int index) {
        tasks.get(index).markDone();
    }

    public void unmarkTask(int index) {
        tasks.get(index).markNotDone();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void delete(int index) {
        tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }
}
