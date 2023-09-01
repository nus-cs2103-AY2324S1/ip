import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
    private ArrayList<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(TaskList tasks) {
        this.tasks = tasks.tasks;
    }

    /**
     * Returns Task at index i
     * @param i
     * @return Task at index i
     */
    public Task get(int i) {
        return this.tasks.get(i);
    }

    /**
     * @return length of TaskList
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Removes Task at index i
     * @param i
     */
    public void remove(int i) {
        this.tasks.remove(i);
    }

    /**
     * Adds task to the end of TaskList.
     * @param task
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    @Override
    public String toString() {
        String taskString = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            taskString += (" " + (i + 1) + ". " + this.tasks.get(i) + "\n");
        }
        return taskString;
    }
}
