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

    public ArrayList<Task> getTask() {
        return taskList;
    }

    public int getTaskCount() {
        return taskList.size();
    }
}
