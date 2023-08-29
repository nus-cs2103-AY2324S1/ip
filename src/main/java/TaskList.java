import java.util.ArrayList;

// Class to handle keeping track of tasks
public class TaskList {

    // ArrayList of tasks to keep track of all tasks
    private ArrayList<Task> taskList;

    // Counter to keep track of position

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public int size() {
        return taskList.size();
    }

    // Adds an item to the list while notifying the user
    public void add(Task task) {
        taskList.add(task);
    }

    public Task getTask(int i) {
        return taskList.get(i);
    }

    // Marks the item
    public Task mark(int index) {
        Task curr = taskList.get(index - 1); // Decrement by 1 to match display index
        curr.mark();
        return curr;
    }

    // Unmarks the item
    public Task unmark(int index) {
        Task curr = taskList.get(index - 1); // Decrement by 1 to match display index
        curr.unmark();
        return curr;
    }

    public Task delete(int index) {
        Task curr = taskList.get(index - 1);
        taskList.remove(index - 1);

        return curr;
    }
}
