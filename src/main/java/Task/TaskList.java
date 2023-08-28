package Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList = new ArrayList<Task>();

    public void AddTask(Task task) {
        taskList.add(task);
    }

    public void RemoveTask(Task task) {
        taskList.remove(task);
    }

    public int Size() {
        return taskList.size();
    }

    public Task GetTask(int i) {
        return taskList.get(i);
    }
}
