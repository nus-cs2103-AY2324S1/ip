import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Initialises a new instance of the TaskList class.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Gets the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add to the list.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the list at the specified index.
     *
     * @param index The index of the task to delete.
     */
    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Marks a task at the specified index as done.
     *
     * @param index The index of the task to mark as done.
     */
    public void markTaskAsDone(int index) {
        this.tasks.get(index).markAsDone();
    }

    /**
     * Marks a task at the specified index as undone.
     *
     * @param index The index of the task to mark as undone.
     */
    public void markTaskAsUndone(int index) {
        this.tasks.get(index).markAsUndone();
    }

    /**
     * Returns a string representation of the task list.
     *
     * @return A string representation of the task list.
     */
    @Override
    public String toString() {
        ArrayList<String> allTaskStrings = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            String taskString = String.format("%d. %s", i + 1, this.tasks.get(i).toString());
            allTaskStrings.add(taskString);
        }
        return String.join("\n", allTaskStrings);
    }



}
