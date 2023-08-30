package services;

import constants.Message;
import exceptions.DukeException;
import tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(Integer index) {
        return tasks.get(index);
    }

    public String listTasks() throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException(Message.TASK_LIST_EMPTY);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append("\t");
            sb.append(i + 1);
            sb.append(".");
            sb.append(tasks.get(i).toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public void markTaskAsDone(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException(Message.TASK_LIST_INVALID_INDEX);
        }
        tasks.get(index).markAsDone();
    }

    public void markTaskAsNotDone(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException(Message.TASK_LIST_INVALID_INDEX);
        }
        tasks.get(index).markAsNotDone();
    }

    public Task deleteTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException(Message.TASK_LIST_INVALID_INDEX);
        }
        return tasks.remove(index);
    }

    public String taskCountSummary() {
        int size = tasks.size();
        if (size == 1) {
            return ("Now you have " + size + " task in the list.");
        } else {
            return ("Now you have " + size + " tasks in the list.");
        }
    }
}
