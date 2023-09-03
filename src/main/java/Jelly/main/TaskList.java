package Jelly.main;

import Jelly.task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public int size() {
        return taskList.size();
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public void delete(int index) {
        taskList.remove(index);
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public void markAsDone(int index) {
        taskList.get(index).markAsDone();
    }

    public void markAsUndone(int index) {
        taskList.get(index).markAsUndone();
    }
}
