import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public List<Task> taskList;
    
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public int size() {
        return taskList.size();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task deleteTask(int i) {
        return taskList.remove(i);
    }

    public List<Task> getTasks() {
        return taskList;
    }

    public List<Task> load() {
        return taskList;
    }
}
