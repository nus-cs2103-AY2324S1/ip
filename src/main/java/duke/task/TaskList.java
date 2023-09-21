package duke.task;

import duke.command.DukeException;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The TaskList class represents a list of tasks in the Duke application.
 * It provides methods for adding, deleting, marking tasks as done, and loading/saving tasks to a file.
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
     * Constructs a TaskList with the provided list of tasks.
     *
     * @param arr The list of tasks to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> arr) {
        this.tasks = arr;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        assert task != null : "task cannot be null";
        this.tasks.add(task);
    }

    /**
     * Checks if a task at the specified index is marked as done.
     *
     * @param taskIndex The index of the task to check.
     * @return true if the task is done, false otherwise.
     */
    public boolean isTaskDone(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < this.tasks.size()) {
            Task task = this.tasks.get(taskIndex);
            return task.isDone();
        }
        return false;
    }

    /**
     * Returns a string representation of all tasks in the TaskList.
     *
     * @return A string containing the list of tasks.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (!this.tasks.isEmpty()) {
            for (int i = 0; i < this.tasks.size(); i++) {
                sb.append(" ").append(i + 1).append(".").append(this.tasks.get(i));
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Returns an ArrayList of all tasks in the TaskList.
     *
     * @return An ArrayList of all tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }

    /**
     * Returns the total number of tasks in the TaskList.
     *
     * @return The total number of tasks.
     */
    public int getTotalTasks() {
        return this.tasks.size();
    }

    /**
     * Gets a task from the TaskList based on its index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index, or null if the index is out of bounds.
     */
    public Task getTask(int index) {
        if (index >= 0 && index < this.tasks.size()) {
            return this.tasks.get(index);
        }
        return null;
    }

    /**
     * Marks a task as done based on its index in the TaskList.
     *
     * @param taskIndex The index of the task to mark as done.
     * @throws DukeException If there is an error marking the task as done.
     */
    public void markAsDone(int taskIndex) throws DukeException {
        if (taskIndex >= 0 && taskIndex < this.tasks.size()) {
            this.tasks.get(taskIndex).markAsDone();
        } else {
            throw new DukeException("Invalid task index.");
        }
    }

    /**
     * Marks a task as not done based on its index in the TaskList.
     *
     * @param taskIndex The index of the task to mark as not done.
     * @throws DukeException If there is an error marking the task as not done.
     */
    public void markAsNotDone(int taskIndex) throws DukeException {
        if (taskIndex >= 0 && taskIndex < this.tasks.size()) {
            this.tasks.get(taskIndex).markAsNotDone();
        } else {
            throw new DukeException("Invalid task index.");
        }
    }

    /**
     * Deletes a task from the TaskList based on its index.
     *
     * @param taskIndex The index of the task to delete.
     * @return The deleted task.
     * @throws DukeException If there is an error deleting the task.
     */
    public Task deleteTask(int taskIndex) throws DukeException {
        if (taskIndex >= 0 && taskIndex < this.tasks.size()) {
            Task deletedTask = this.tasks.remove(taskIndex);
            return deletedTask;
        } else {
            throw new DukeException("Invalid task index.");
        }
    }

    /**
     * Finds tasks in the TaskList that contain a specified keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return An ArrayList of tasks matching the keyword.
     */
    public ArrayList<Task> findTasksByKeyword(String keyword) {
        assert keyword != null : "keyword cannot be null";
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : this.tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }

    /**
     * Sorts tasks in the TaskList by priority.
     */
    public void sortTasksByPriority() {
        Collections.sort(this.tasks, new PriorityComparator());
    }
}