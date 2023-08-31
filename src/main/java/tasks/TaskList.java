package tasks;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskList implements Serializable {
    private ArrayList<Task> items;
    public TaskList() {
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

    public void reset() {
        this.items = new ArrayList<>();
    }

    public Task[] toArray() {
        Task[] lst = new Task[this.size()];
        return items.toArray(lst);
    }

    public List<Task> toList() {
        return items;
    }

    @Override
    public String toString() {
        String response = "";
        if (items.size() == 0) {
            response = "No tasks found!";
        } else {
            int counter = 1;
            response = "Task List: ";
            for (Task task:this.toArray()) {
                response += "\n" + Integer.toString(counter) + ". " + task.toString();
                counter++;
            }
        }
        return response;
    }
}
