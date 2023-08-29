package main.java.tasklist;

import main.java.Task;
import main.java.storage.Storage;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }
    public void loadData(Storage storage) {

    }
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public int numOfTasks() {
        return this.taskList.size();
    }
    public Task getTask(int i) {
        return this.taskList.get(i);
    }
    public void removeTask(Task task) {
        this.taskList.remove(task);
    }

}
