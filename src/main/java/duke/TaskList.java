package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    public ArrayList<Task> getAllTasks() {
        return taskList;
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public void markTask(int index) {
        taskList.get(index).changeStatus();
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public void delete(int index) throws DukeException {
        if (index < 0 || index >= taskList.size()) {
            throw new DukeException("OOPS! The index to remove is invalid!");
        }

        taskList.remove(index);
    }

    public int size() {
        return taskList.size();
    }
}
