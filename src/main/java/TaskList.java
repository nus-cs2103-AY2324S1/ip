import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> arrTask;
    public TaskList() {
        this.arrTask = new ArrayList<>();
    }

    public void addTask(Task task) {
        arrTask.add(task);
    }

    public Task deleteTask(int index) {
        return arrTask.remove(index -1);
    }

    public Task getTask(int index) {
        return arrTask.get(index - 1);
    }

    public int getSize() {
        return arrTask.size();
    }
}
