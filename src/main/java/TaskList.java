import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> prevList) {
        this.list = prevList;
    }

    public Task get(int index) {
        return this.list.get(index);
    }

    public int size() {
        return this.list.size();
    }

    public void remove(int index) {
        this.list.remove(index);
    }

    public void add(Task task) {
        this.list.add(task);
    }
}
