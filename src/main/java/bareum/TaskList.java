package bareum;

import java.util.ArrayList;

/**
 * This class implements the task list which manages the user's tasks.
 */
public class TaskList {
    /**
     * The list of tasks to manage.
     */
    private ArrayList<Task> taskList;

    /**
     * Creates a new task list to add tasks to.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task Task to add to the task list.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Gets the size of the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Gets the task at the given index.
     *
     * @param index Index of task that user wants to change.
     * @return Task that user wants to change.
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Marks a task as completed.
     *
     * @param index Index of task that user wants to mark as complete.
     */
    public void markAsDone(int index) {
        this.taskList.get(index).markAsDone();
    }

    /**
     * Unmarks a task as completed.
     *
     * @param index Index of task that user wants to unmark as complete.
     */
    public void unmarkAsDone(int index) {
        this.taskList.get(index).unmarkAsDone();
    }

    /**
     * Deletes a task.
     *
     * @param index Index of task that user wants to delete.
     */
    public void delete(int index) {
        this.taskList.remove(index);
    }
}
