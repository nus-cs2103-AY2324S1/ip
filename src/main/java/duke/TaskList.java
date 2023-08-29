package duke;

import exception.DukeException;
import task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Task getTasks(int i) {
        return this.tasks.get(i);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task removeTask(int i) throws DukeException {
        if (i < this.tasks.size()) {
            return this.tasks.remove(i);
        } else {
            throw new DukeException("☹ OOPS!!! The number input does not exist.", new IndexOutOfBoundsException());
        }
    }

    public int getSize() {
        return this.tasks.size();
    }

    public void markTaskDone(int i) throws DukeException {
        if (i < this.tasks.size()) {
            this.tasks.get(i).markAsDone();
        } else {
            throw new DukeException("☹ OOPS!!! The number input does not exist.", new IndexOutOfBoundsException());
        }
    }

    public void markTaskUndone(int i) throws DukeException {
        if (i < this.tasks.size()) {
            this.tasks.get(i).markAsUndone();
        } else {
            throw new DukeException("☹ OOPS!!! The number input does not exist.", new IndexOutOfBoundsException());
        }
    }
}
