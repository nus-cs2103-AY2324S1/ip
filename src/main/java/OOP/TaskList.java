package OOP;

import Tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;


    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task deleteTask(int id) {
        return this.tasks.remove(id);
    }

    public void markTask(int id) {
        this.getTask(id).markAsDone();
    }
    public void unmarkTask(int id) {
        this.getTask(id).markAsUndone();
    }



    public int getSize() {
        return this.tasks.size();
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

}
