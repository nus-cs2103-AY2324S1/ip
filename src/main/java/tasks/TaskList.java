package tasks;

import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    public Task remTask(int n) {
        return tasks.remove(n);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public boolean isTaskListEmpty() {
        return tasks.isEmpty();
    }

    public Task getTask (int n) {
        return tasks.get(n);
    }

    public int getSize() {
        return tasks.size();
    }
}
