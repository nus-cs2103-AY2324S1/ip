package task;

import exception.BobTaskOutOfBoundsException;

import java.util.ArrayList;
import java.util.Iterator;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task deleteTask(int num) throws BobTaskOutOfBoundsException {
        try {
            return tasks.remove(num - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new BobTaskOutOfBoundsException();
        }
    }

    public int size() {
        return this.tasks.size();
    }

    public Task getTask(int num) {
        try {
            return this.tasks.get(num - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }
}
