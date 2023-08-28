package geraldbot.util;

import geraldbot.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public Task remove(int idx) {
        Task removedTask = this.taskList.remove(idx);
        return removedTask;
    }

    public Task get(int idx) {
        Task selectedTask = this.taskList.get(idx);
        return selectedTask;
    }

    public int size() {
        return this.taskList.size();
    }

}
