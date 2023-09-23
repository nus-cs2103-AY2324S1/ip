package Frenchie;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public String listTasks() {
        int counter = 1;
        StringBuilder output = new StringBuilder();
        for (Task task : tasks) {
            output.append(counter).append(". ").append(task.toString()).append("\n");
            counter += 1;
        }
        return output.toString();
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public int size() {
        return this.tasks.size();
    }
    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    public void markTaskAsCompleted(int index) {
        this.tasks.get(index).setIsCompleted();
    }

    public String returnMatchTasks(String keyword) {
        int counter = 1;
        StringBuilder output = new StringBuilder();
        for (Task task: tasks) {
            if (task.toString().contains(keyword)) {
                output.append(counter).append(". ").append(task.toString()).append("\n");
                counter += 1;
            }
        } return output.toString();
    }
    public void markTaskAsIncomplete(int index) {
        this.tasks.get(index).setIsIncomplete();
    }
    // Other methods for updating tasks
}

