// Replace ArrayList to store tasks

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> userTasks;

    public TaskManager() {
        this.userTasks = new ArrayList<Task>();
    }

    public void add(Task task) {
        this.userTasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + userTasks.size() + " tasks in the list.");

    }

    public void remove(int taskID) {
        this.userTasks.remove(taskID);
    }

    public Task get(int taskID) {
        return this.userTasks.get(taskID);
    }

    public int size() {
        return this.userTasks.size();
    }
}
