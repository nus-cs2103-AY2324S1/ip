package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task deleteTask(int index) {
        return taskList.remove(index);
    }

    public int listSize() {
        return taskList.size();
    }

    public Task getTask(int taskIndex) {
        return taskList.get(taskIndex);
    }
}

