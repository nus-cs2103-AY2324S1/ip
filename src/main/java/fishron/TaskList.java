package fishron;

import java.util.ArrayList;
public class TaskList {

    private ArrayList<Task> taskList;
    public TaskList() {
        this.taskList = new ArrayList<>();
    }
    public TaskList(ArrayList taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void deleteTask(int index) {
        this.taskList.remove(index);
    }

    public ArrayList getList() {
        return this.taskList;
    }

    public int getSize() {
        return this.taskList.size();
    }

    public void markAsDone(int index) {
        this.taskList.get(index - 1).markAsDone();
    }

    public void markAsUndone(int index) {
        this.taskList.get(index - 1).markAsUndone();
    }

    public Task getTask(int index) {
        return this.taskList.get(index - 1);
    }
}