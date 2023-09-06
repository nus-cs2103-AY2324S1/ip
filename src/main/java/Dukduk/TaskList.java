package dukduk;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getTaskCount() {
        return tasks.size();
    }

    private boolean isValidTaskIndex(int taskIndex) {
        return taskIndex >= 0 && taskIndex < tasks.size();
    }

    public Task getTask(int taskIndex) {
        if (isValidTaskIndex(taskIndex)) {
            return tasks.get(taskIndex);
        }
        return null;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void markTaskAsDone(int taskIndex) {
        if (isValidTaskIndex(taskIndex)) {
            tasks.get(taskIndex).markAsDone();
        }
    }

    public void unMarkTask(int taskIndex) {
        if (isValidTaskIndex(taskIndex)) {
            tasks.get(taskIndex).unmark();
        }
    }

    public void deleteTask(int taskIndex) throws DukdukException {
        if (isValidTaskIndex(taskIndex)) {
            Task removedTask = tasks.remove(taskIndex);
            Ui.deleteTask(tasks, taskIndex, removedTask);
        } else {
            throw new DukdukException("OOPS!!! Task not found. Please provide a valid task number.");
        }
    }
}
