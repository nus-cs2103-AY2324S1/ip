import java.util.*;

import dukeExceptions.*;
import dukeExceptions.IndexOutOfBoundsException;

public class TaskList {
    protected ArrayList<Task> taskArr;

    public TaskList() {
        this.taskArr = new ArrayList<Task>();
    }

    public Task getTask(int index) throws DukeException {
        try {
            return this.taskArr.get(index);
        } catch (Exception e) {
            throw new IndexOutOfBoundsException();
        }
    }

    public int length() {
        return this.taskArr.size();
    }

    public void markTaskAsDone(int index) throws DukeException {
        this.getTask(index).markAsDone();
    }

    public void markTaskAsNotDone(int index) throws DukeException {
        this.getTask(index).markAsNotDone();

    }

    public String taskToString(int index) {
        return this.taskArr.get(index).toString();
    }

    public String numTasksToString() {
        if (this.length() == 1) {
            return "1 task";
        }
        return this.length() + " tasks";
    }

    public void addTask(Task task) {
        this.taskArr.add(task);
    }

    public void delete(int index) {
        this.taskArr.remove(index);
    }
}
