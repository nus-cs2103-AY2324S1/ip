package duke.task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public void deleteTask(int index) {
        this.list.remove(index);
    }

    public Task getTask(int index) {
        return this.list.get(index);
    }

    public void markTask(int index) {
        this.list.get(index).mark();
    }

    public void unmarkTask(int index) {
        this.list.get(index).unmark();
    }


    public int size() {
        return this.list.size();
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

}
