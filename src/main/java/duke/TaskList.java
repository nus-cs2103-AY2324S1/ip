package duke;// Solution below adapted and inspired by https://chat.openai.com/share/7f037351-3be6-4105-b138-77f68d428c84

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    // Set the tasks in the duke.TaskList
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int numTasks() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }
}
