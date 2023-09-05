import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> tasks() {
        return (ArrayList)tasks;
    }
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }


}
