import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public List<Task> taskForce;

    public TaskList() {
        taskForce = new ArrayList<Task>();
    }

    public void add(Task task) {
        taskForce.add(task);
    }

    public void delete(int index) {
        taskForce.remove(index);
    }

    public Task mark(int index) {
        Task task = taskForce.get(index);
        task.markTaskDone();
        return task;
    }

    public Task unmark(int index) {
        Task task = taskForce.get(index);
        task.markTaskNotDone();
        return task;
    }

    public Task retrieveTask(int index) {
        return taskForce.get(index);
    }
}