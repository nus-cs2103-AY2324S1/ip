package jarvis;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void setTasks(ArrayList<Task> tasks) {
        taskList.clear();
        taskList.addAll(tasks);
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }
    
    public Task getTask(int index) {
        return taskList.get(index);
    }

    public int getTaskCount() {
        return taskList.size();
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public Task deleteTask(int i) {
        Task removedTask = taskList.get(i);
        taskList.remove(i);
        return removedTask;
    }
}
