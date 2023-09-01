import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public int size() {
        return taskList.size();
    }
    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public void markTask(int index) {
        taskList.get(index).setDone(true);
    }

    public void unmarkTask(int index) {
        taskList.get(index).setDone(false);
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }

    public Task getLastTask() {
        return getTask(size() - 1);
    }
}
