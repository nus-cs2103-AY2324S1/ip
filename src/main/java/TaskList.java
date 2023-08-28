import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    public List<Task> toList() {
        return items;
    }

    @Override
    public String toString() {
        String response = "";
        if (Rock.taskList.size() == 0) {
            response = "No tasks found!";
        } else {
            int counter = 1;
            response = "Task List: ";
            for (Task task:Rock.taskList.toArray()) {
                response += "\n" + Integer.toString(counter) + ". " + task.toString();
                counter++;
            }
        }
        return response;
    }
}
