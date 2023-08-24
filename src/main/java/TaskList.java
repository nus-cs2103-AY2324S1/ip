import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public ArrayList<Task> getTask() {
        return taskList;
    }
}
