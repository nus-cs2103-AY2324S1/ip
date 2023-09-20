package jo;

import java.time.LocalDate;
import java.util.ArrayList;

import jo.task.Deadline;
import jo.task.Event;
import jo.task.Task;

/**
 * Represents a list of tasks in the `Jo` application.
 * It provides methods for managing tasks such as adding, deleting, marking, and searching.
 */
public class TaskList {

    protected ArrayList<Task> tList;

    /**
     * Constructs a new `TaskList` with the specified list of tasks.
     *
     * @param tasks The initial list of tasks to populate the `TaskList`.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tList = tasks;
    }

    /**
     * Constructs an empty `TaskList`.
     */
    public TaskList() {
        this.tList = new ArrayList<>();
    }

    /**
     * Adds a task to the `TaskList`.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        this.tList.add(task);
    }

    /**
     * Deletes a task from the `TaskList` based on its index.
     *
     * @param index The index of the task to delete.
     */
    public void deleteTask(int index) {
        tList.remove(index);
    }

    /**
     * Marks a task in the `TaskList` as done or undone based on its index.
     *
     * @param index  The index of the task to mark.
     * @param isDone `true` to mark the task as done, `false` to mark it as undone.
     */
    public void markTask(int index, boolean isDone) {
        Task targetTask = this.tList.get(index);
        targetTask.mark(isDone);
    }

    /**
     * Searches for tasks with a specific deadline in the `TaskList`.
     *
     * @param deadline The deadline to search for.
     * @return An ArrayList of tasks matching the specified deadline and are not marked as done.
     */
    public ArrayList<Task> search(LocalDate deadline) {
        ArrayList<Task> resultList = new ArrayList<>();
        for (Task t : this.tList) {
            if (t instanceof Deadline) {
                Deadline task = (Deadline) t;
                if (task.getDeadline().equals(deadline) && !task.getIsDone()) {
                    resultList.add(task);
                }
            } else if (t instanceof Event) {
                Event task = (Event) t;
                if (task.getDeadline().equals(deadline) && !task.getIsDone()) {
                    resultList.add(task);
                }
            }
        }
        return resultList;
    }

    /**
     * Searches for tasks in the task list that contain the specified keyword in their descriptions.
     *
     * @param keyword The keyword to search for within task descriptions.
     * @return An ArrayList of tasks that match the search criteria (contain the keyword).
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> resultList = new ArrayList<>();
        for (Task t : this.tList) {
            if (t.getDescription().contains(keyword)) {
                resultList.add(t);
            }
        }
        return resultList;
    }

    /**
     * Retrieves a task from the `TaskList` based on its index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return this.tList.get(index);
    }

    /**
     * Gets the number of tasks in the `TaskList`.
     *
     * @return The number of tasks in the `TaskList`.
     */
    public int getSize() {
        return this.tList.size();
    }

}
