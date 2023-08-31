package helpbuddy.task;


import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public Task getTask(int i) {
        return this.taskList.get(i);
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void deleteTask(int i) {
        this.taskList.remove(i);
    }

    public boolean isEmpty() {
        return this.taskList.size() == 0;
    }

    public int getSize() {
        return this.taskList.size();
    }
}
