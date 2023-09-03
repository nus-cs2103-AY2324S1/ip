package functions;

import tasks.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * A public constructor to initialize a new TaskList instance
     *
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public Task get(int idx) {
        return this.taskList.get(idx);
    }

    public void add(Task task){
        this.taskList.add(task);
    }

    public int size() {
        return this.taskList.size();
    }

    public void remove(int idx) {
        this.taskList.remove(idx);
    }

}
