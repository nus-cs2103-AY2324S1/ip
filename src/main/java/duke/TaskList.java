package duke;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Represents a list of tasks that can be managed, including adding, deleting, and marking tasks.
 * Tasks are stored in a List<Task> and can be interacted with using the provided methods.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs a TaskList with the provided list of saved tasks.
     *
     * @param savedTasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(List<Task> savedTasks) {
        assert savedTasks != null;
        this.tasks = savedTasks;
    }

    /**
     * Constructs an empty TaskList, to be used when there is no previously saved tasks.
     *
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     *  Returns number of tasks in the tasklist
     * @return Number of tasks
     */
    public int getNumTasks() {
        return tasks.size();
    }

    /**
     * Deletes a task at the specified index from the TaskList.
     *
     * @param idx The index of the task to be deleted.
     * @throws IndexOutOfBoundsException If the index is out of the valid range of task indices.
     */
    public Task deleteTask(int idx) throws IndexOutOfBoundsException{
        Task task = tasks.get(idx);
        tasks.remove(task);
        return task;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added to the list.
     */
    public int addTask(Task task) {
        this.tasks.add(task);
        return tasks.size();
    }

    /**
     * Marks a task at the specified index as completed.
     *
     * @param idx The index of the task to be marked as completed.
     * @throws IndexOutOfBoundsException If the index is out of the valid range of task indices.
     */
    public Task markTask(int idx) throws IndexOutOfBoundsException {
        Task task = tasks.get(idx);
        task.setIsCompleted(true);
        return task;
    }

    /**
     * Unmarks a task at the specified index as not completed.
     *
     * @param idx The index of the task to be unmarked.
     * @throws IndexOutOfBoundsException If the index is out of the valid range of task indices.
     */
    public Task unmarkTask(int idx) throws IndexOutOfBoundsException {
        Task task = tasks.get(idx);
        task.setIsCompleted(false);
        return task;
    }

    /**
     * Returns the list of tasks stored in the TaskList.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Finds tasks that match the provided keyword in their descriptions.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A new TaskList containing tasks that match the keyword.
     */
    public TaskList find(String keyword) {
        TaskList ret = new TaskList();
        for (Task task : tasks) {
            if (task.description.contains(keyword)) {
                ret.addTask(task);
            }
        }
        return ret;
    }

    /**
     * Removes duplicate tasks from the current TaskList and returns a new TaskList
     * containing the removed duplicate tasks to show the user.
     *
     * @return A new TaskList containing the duplicate tasks that were removed from
     *         the current TaskList. If there are no duplicates, an empty TaskList
     *         is returned.
     */
    public TaskList removeDuplicates() {
        HashSet<Task> taskSet = new HashSet<>();
        TaskList duplicates = new TaskList();
        for (Task task : tasks) {
            boolean added = taskSet.add(task);
            if (!added) {
                duplicates.addTask(task);
            }
        }
        tasks = new ArrayList<>(taskSet);
        return duplicates;
    }

    /**
     * Returns a string representation of the TaskList.
     *
     * @return A formatted string representing the tasks in the TaskList.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            stringBuilder.append(i);
            stringBuilder.append(".");
            stringBuilder.append(tasks.get(i));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
