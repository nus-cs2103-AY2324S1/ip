package duke.task;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> arrTask;

    public TaskList(ArrayList<Task> arrTask) {
        this.arrTask = arrTask;
    }

    public TaskList() {
        this.arrTask = new ArrayList<>();
    }

    public int size() {
        return arrTask.size();
    }

    public void mark(int index) {
        arrTask.get(index).markAsDone();
    }

    public void unmark(int index) {
        arrTask.get(index).markAsUndone();
    }

    public String getPrint(int index) {
        return arrTask.get(index).toString();
    }

    public void addTask(Task task) {
        this.arrTask.add(task);
    }

    public void deleteTask(int index) {
        this.arrTask.remove(index);
    }

    public Task get(int index) {
        return this.arrTask.get(index);
    }
}
