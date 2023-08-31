import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int getSize() {
        return this.tasks.size();
    }

    public void add(Task t) {
        this.tasks.add(t);
    }

    public void remove(Task t) {
        this.tasks.remove(t);
    }

    public Task getTaskByIndex(int i) {
        return this.tasks.get(i);
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }
}
