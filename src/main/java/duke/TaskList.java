package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks that can be managed, including adding, deleting, and marking tasks.
 * Tasks are stored in a List<Task> and can be interacted with using the provided methods.
 */
public class TaskList {
    private final List<Task> tasks;
    private final Ui ui;

    /**
     * Constructs a TaskList with the provided list of saved tasks and a Ui object.
     *
     * @param savedTasks The list of tasks to initialize the TaskList with.
     * @param ui The Ui object used to display messages to the user.
     */
    public TaskList(List<Task> savedTasks, Ui ui) {
        this.tasks = savedTasks;
        this.ui = ui;
    }

    /**
     * Constructs an empty TaskList with the provided Ui object, to be used when there is no previously saved tasks.
     *
     * @param ui The Ui object used to display messages to the user.
     */
    public TaskList(Ui ui) {
        this.tasks = new ArrayList<>();
        this.ui = ui;
    }

    /**
     * Deletes a task at the specified index from the TaskList.
     *
     * @param idx The index of the task to be deleted.
     * @throws IndexOutOfBoundsException If the index is out of the valid range of task indices.
     */
    public void deleteTask(int idx) throws IndexOutOfBoundsException{
        Task task = tasks.get(idx);
        tasks.remove(task);
        ui.showDeleteTaskMessage(task, tasks.size());
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        ui.showAddTaskMessage(task, tasks.size());
    }

    /**
     * Marks a task at the specified index as completed.
     *
     * @param idx The index of the task to be marked as completed.
     * @throws IndexOutOfBoundsException If the index is out of the valid range of task indices.
     */
    public void markTask(int idx) throws IndexOutOfBoundsException {
        Task task = tasks.get(idx);
        task.setIsCompleted(true);
        ui.showMarkedTask(task);
    }

    /**
     * Unmarks a task at the specified index as not completed.
     *
     * @param idx The index of the task to be unmarked.
     * @throws IndexOutOfBoundsException If the index is out of the valid range of task indices.
     */
    public void unmarkTask(int idx) throws IndexOutOfBoundsException {
        Task task = tasks.get(idx);
        task.setIsCompleted(false);
        ui.showUnmarkedTask(task);
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
        TaskList ret = new TaskList(ui);
        for (Task task : tasks) {
            if (task.description.contains(keyword)) {
                ret.addTask(task);
            }
        }
        return ret;
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
