package jarvis.tasklist;

import jarvis.task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> loadedTasks) {
        this.tasks = loadedTasks;
    }

    // Adds a task to the list.
    public void add(Task task) {
        tasks.add(task);
    }

    // Removes a task from the list based on the index.
    public Task remove(int index){
        return tasks.remove(index);
    }

    // Gets a task based on its index.
    public Task get(int index) {
        return tasks.get(index);
    }

    // Returns the number of tasks in the list.
    public int size() {
        return tasks.size();
    }

    // Returns the entire list of tasks.
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}