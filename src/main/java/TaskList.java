import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();

    public TaskList(ArrayList<Task> tasks) {
        this.list.addAll(tasks);
    }

    public void add(Task task) {
        this.list.add(task);
    }

    public void delete(int taskIndex) {
        this.list.remove(taskIndex);
    }

    public Task get(int taskIndex) {
        return this.list.get(taskIndex);
    }

    public int getLength() {
        return this.list.size();
    }

    public boolean isEmpty() {
        return this.getLength() == 0;
    }

}
