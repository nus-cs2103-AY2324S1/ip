package chatty;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> arraylistOfTasks) {
        this.tasks = arraylistOfTasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public void remove(int index) {
        tasks.remove(index);
    }

    public String printTasks() {
        StringBuilder result = new StringBuilder();
        result.append("Here are the tasks in your list:").append("\n");
        for (int i = 0; i < this.tasks.size(); i++) {
            result.append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        return result.toString();
    }

    public String printTaskCount() {
        return "Now you have " + this.tasks.size() + " tasks in the list.";
    }

}
