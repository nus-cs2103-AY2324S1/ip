import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Describes the list of tasks in the task list.
     */
    @Override
    public String toString() {
        String returnString = "";
        for (int x = 0; x < tasks.size(); x++) {
            returnString = String.format("%s%d. %s\n", returnString, x + 1, tasks.get(x).toString());
        }
        return returnString;
    }

    /**
     * Marks a task as done.
     *
     * @param index index of the task to be marked. 1-indexed.
     * @return the updated task.
     */
    public Task markTask(int index) {
        Task task = tasks.get(index - 1);
        task.mark();
        return task;
    }

    /**
     * Unmarks a task.
     *
     * @param index index of the task to be unmarked. 1-indexed.
     * @return the updated task.
     */
    public Task unmarkTask(int index) {
        Task task = tasks.get(index - 1);
        task.unmark();
        return task;
    }

    /**
     * Deletes a task.
     *
     * @param index index of the task to be deleted. 1-indexed.
     * @return the deleted task.
     */
    public Task deleteTask(int index) {
        Task task = tasks.get(index - 1);
        tasks.remove(task);
        return task;
    }

    /**
     * Gets the current size of the task list.
     * @return the size of the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Adds a task to the task list.
     * @param task task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }
}
