package duke;

import java.util.List;

/**
 * The TaskList class represents a list of tasks in the Duke application.
 * It provides methods to manipulate and interact with the list of tasks.
 */
public class TaskList {
    private final List<Task> userList;

    /**
     * Constructs a TaskList instance with the provided list of tasks.
     *
     * @param taskList The list of tasks to be managed by the TaskList.
     */
    public TaskList(List<Task> taskList) {
        this.userList = taskList;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        userList.add(task);
    }

    /**
     * Marks a task as done in the task list.
     *
     * @param task The task to be marked as done.
     */
    public void markTask(Task task) {
        task.markAsDone();
        Ui.showMarkedTask(task);
    }

    /**
     * Marks a task as not done in the task list.
     *
     * @param task The task to be marked as not done.
     */
    public void unmarkTask(Task task) {
        task.markAsUndone();
        Ui.showUnmarkedTask(task);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param task The task to be deleted from the list.
     */
    public void deleteTask(Task task) {
        userList.remove(task);
        Ui.showDeletedTask(task, this);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return userList.size();
    }

    /**
     * Returns the task at the specified index in the task list.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return userList.get(index);
    }
}