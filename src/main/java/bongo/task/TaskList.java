package bongo.task;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> loadedTasks) {
        this.tasks = loadedTasks;
    }


    public Task getTask(int taskIndex) {
        return this.tasks.get(taskIndex);
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    public void deleteTask(int taskIndex) {
        tasks.remove(taskIndex);
    }

    public int getTotalTasks() {
        return this.tasks.size();
    }

    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }

    public void markTaskDone(int taskIndex) {
        tasks.get(taskIndex).markAsDone();
    }

    public void markTaskUndone(int taskIndex) {
        tasks.get(taskIndex).markAsUndone();
    }
}
