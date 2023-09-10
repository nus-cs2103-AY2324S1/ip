import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int size() {
        return tasks.size();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void mark(int taskNo) {
        tasks.get(taskNo).markAsDone();
    }

    public void unmark(int taskNo) {
        tasks.get(taskNo).markAsUndone();
    }

    public Task remove(int taskNo) {
        return tasks.remove(taskNo);
    }

    public String getTaskString(int taskNo) {
        return tasks.get(taskNo).toString();
    }

    public String getTaskSaveString(int taskNo) {
        return tasks.get(taskNo).toSaveString();
    }
}
