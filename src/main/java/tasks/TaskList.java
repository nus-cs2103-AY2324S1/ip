package tasks;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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
    public Task removeTask(int index) throws IndexOutOfBoundsException {
        return this.items.remove(index);
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
    public void mark(int index, boolean completed) throws IllegalArgumentException, IndexOutOfBoundsException {
        items.get(index).setCompleted(completed);
    }

    public String filteredSearch(Predicate<Task> condition) {
        String response = "";
        int counter = 1;
        for (Task task:this.toArray()) {
            if (condition.test(task)) {
                response += "\n" + Integer.toString(counter) + ". " + task.toString();
                counter++;
            }
        }
        if (response == "") {
            return "No tasks found with given search!";
        } else {
            return response;
        }
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
