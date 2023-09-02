package urchatbot.taskList;

import urchatbot.tasks.Task;

import java.util.ArrayList;
public class TaskList {
    public static ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }
    public void clearTask() {
        tasks.clear();
    }
    public ArrayList<Task> getTasks() {
        return tasks;
    }
    public int getSize() {
        return tasks.size();
    }
}
