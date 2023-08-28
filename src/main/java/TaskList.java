import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable{
    ArrayList<Task> items;

    TaskList() {
        this.items = new ArrayList<>();
    }

    public void addTask(Task t) {
        this.items.add(t);
    }

    public Task getTask(int index) {
        try {
            return this.items.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
    public Task removeTask(int index) {
        try {
            return this.items.remove(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public int size() {
        return this.items.size();
    }

    public Task[] toArray() {
        Task[] lst = new Task[this.size()];
        return items.toArray(lst);
    }
}
