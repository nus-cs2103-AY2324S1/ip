import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int getLength() {
        return tasks.size();
    }

    public void remove(int index) {
        this.tasks.remove(index);
    }

    public int getTaskCount() {
        return this.tasks.size();
    }

}
