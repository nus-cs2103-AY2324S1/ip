package duke;

import java.util.ArrayList;

import task.Task;
public class TaskList {
    private ArrayList<Task> arrList;

    public TaskList() {
        this.arrList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> arrList) {
        this.arrList = arrList;
    }

    public ArrayList<Task> getArrList() {
        return this.arrList;
    }

    public int size() {
        return this.arrList.size();
    }
    public void mark(int choice) {
        this.arrList.get(choice - 1).markDone();
    }

    public void unmark(int choice) {
        this.arrList.get(choice - 1).markUndone();
    }

    public void add(Task task) {
        this.arrList.add(task);
    }
    public Task delete(int choice) {
        return this.arrList.remove(choice - 1);
    }
    public String taskToString(int choice) {
        return this.arrList.get(choice - 1).toString();
    }
}
