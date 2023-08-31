package duke.task;

import duke.exceptions.CommandDetailException;

import java.util.ArrayList;


public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) throws CommandDetailException {
        try {
            tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandDetailException("OOPS!!! There is no such task!");
        }
    }


    public Task getTask(int index) throws CommandDetailException {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandDetailException("OOPS!!! There is no such task!");
        }
    }

    public void markTask(int index) throws CommandDetailException {
        this.getTask(index).setDone();
    }

    public void unmarkTask(int index) throws CommandDetailException {
        this.getTask(index).setUndone();
    }

    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTaskName().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

    public int size() {
        return tasks.size();
    }

    public String toStorage() {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.toSaveFormat()).append("\n");
        }
        return sb.toString();
    }
}
