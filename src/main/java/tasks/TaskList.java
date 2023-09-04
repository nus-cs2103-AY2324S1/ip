package tasks;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.function.Predicate;
/**
 * Representation of a task list
 * @author Alvis Ng (supermii2)
 */
public class TaskList implements Serializable {
    private ArrayList<Task> items;
    /**
     * Creates a new empty task list
     */
    public TaskList() {
        this.items = new ArrayList<>();
    }
    /**
     * Adds the given task to the task list
     * @param t Task to be added
     */
    public void addTask(Task t) {
        this.items.add(t);
    }
    /**
     * Gets the task at the given index
     * @param index Index of requested task
     * @return Task requested, null if invalid
     */
    public Task getTask(int index) {
        try {
            return this.items.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
    /**
     * Removes the task at the given index
     * @param index Index of removed task
     * @return Removed task
     * @throws IndexOutOfBoundsException Task is invalid
     */
    public Task removeTask(int index) throws IndexOutOfBoundsException {
        return this.items.remove(index);
    }
    /**
     * Returns the size of the task list
     * @return size of task list as int
     */
    public int size() {
        return this.items.size();
    }
    /**
     * Clears the task list
     */
    public void reset() {
        this.items = new ArrayList<>();
    }
    /**
     * Returns the taskList as an array
     * @return
     */
    private Task[] toArray() {
        Task[] lst = new Task[this.size()];
        return items.toArray(lst);
    }
    /**
     * Marks the given task as the given state
     * @param index Index of marked task
     * @param completed State to mark task as
     * @throws IllegalArgumentException Task is already appropriately marked
     * @throws IndexOutOfBoundsException Index is invalid
     */
    public void mark(int index, boolean completed) throws IllegalArgumentException, IndexOutOfBoundsException {
        items.get(index).setCompleted(completed);
    }
    /**
     * Perform a filtered search on the task list
     * @param condition Condition to return tasks if true
     * @return String representation of all tasks that match predicate
     */
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
    /**
     * String representation of task list
     */
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
