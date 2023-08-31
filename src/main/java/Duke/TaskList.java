package Duke;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        //Load if possible
        Storage.load()
    }

    public void addTask(Task task, int index) {
        taskList.add(index, task);
    }
    public void removeTask(int index) {
        taskList.remove(index);
    }
}
