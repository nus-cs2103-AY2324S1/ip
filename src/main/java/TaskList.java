import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {}

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void remove(int index) {
        this.tasks.remove(index);
    }
}
