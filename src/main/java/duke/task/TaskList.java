package duke.task;

import duke.command.DukeException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> arr) {
        this.tasks = arr;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (!this.tasks.isEmpty()) {
            for (int i = 0; i < this.tasks.size(); i++) {
                sb.append(" ").append(i + 1).append(".").append(this.tasks.get(i));
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }

    public int getTotalTasks() {
        return this.tasks.size();
    }

    public Task getTask(int index) {
        if (index >= 0 && index < this.tasks.size()) {
            return this.tasks.get(index);
        }
        return null;
    }
    public void markAsDone(int taskIndex) throws DukeException {
        if (taskIndex >= 0 && taskIndex < this.tasks.size()) {
            this.tasks.get(taskIndex).markAsDone();
        } else {
            throw new DukeException("Invalid task index.");
        }
    }

    public void markAsNotDone(int taskIndex) throws DukeException {
        if (taskIndex >= 0 && taskIndex < this.tasks.size()) {
            this.tasks.get(taskIndex).markAsNotDone();
        } else {
            throw new DukeException("Invalid task index.");
        }
    }

    public Task deleteTask(int taskIndex) throws DukeException {
        if (taskIndex >= 0 && taskIndex < this.tasks.size()) {
            Task deletedTask = this.tasks.remove(taskIndex);
            return deletedTask;
        } else {
            throw new DukeException("Invalid task index.");
        }
    }
}