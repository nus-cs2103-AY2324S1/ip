package milbot;

import java.util.ArrayList;
import java.util.List;
import taskclasses.*;

public class TaskList {
    private List<Task> taskList;
    public TaskList() {
        taskList = new ArrayList<>();
    }
    public List<Task> getTaskList() {
        return taskList;
    }
    public int getSize() {
        return taskList.size();
    }
    public Task getTask(int index) {
        return taskList.get(index);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void removeTask(int index) {
        taskList.remove(index);
    }
}
