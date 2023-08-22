import java.util.ArrayList;

/**
 * Collection of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list by index.
     *
     * @param index The index of the task to be removed.
     */
    public void remove(int index) {
        tasks.remove(index);
    }

    /**
     * Marks a task as done by index.
     *
     * @param index The index of the task to be marked as done.
     */
    public void mark(int index) {
        tasks.get(index).mark();
    }

    /**
     * Unmarks a task by index.
     *
     * @param index The index of the task to be unmarked.
     */
    public void unmark(int index) {
        tasks.get(index).unmark();
    }

    /**
     * Retrieves a task by index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Retrieves the size of the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Provides a string representation of the task list.
     *
     * @return A string representation of the task list.
     */
    @Override
    public String toString() {
        if (tasks.size() == 0 ){
            return "No tasks right now..\nHave a nice rest!";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            stringBuilder.append(i + 1).append(".").append(tasks.get(i).toString());
            if (i != tasks.size()-1) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }
}