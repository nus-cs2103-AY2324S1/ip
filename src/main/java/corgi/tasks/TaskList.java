package corgi.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import corgi.storage.StorableList;

/**
 * A class representing a list of tasks.
 *
 * This class implements the StorableList interface to provide methods for tasks storing.
 */
public class TaskList implements StorableList<Task> {
    private final List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList containing the given list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the TaskList and returns a new immutable TaskList with the added task.
     *
     * @param t The task to add to the TaskList.
     * @return A new TaskList containing all previous tasks and the added task.
     */
    public TaskList add(Task t) {
        List<Task> newList = new ArrayList<>(this.tasks);
        newList.add(t);
        return new TaskList(newList);
    }

    /**
     * Removes a task at the specified index from the TaskList and returns a new immutable TaskList
     * without the removed task.
     *
     * @param index The index of the task to be removed.
     * @return A new TaskList with the specified task removed.
     * @throws TaskListIndexOutOfBoundsException If the index is invalid.
     */
    public TaskList remove(int index) throws TaskListIndexOutOfBoundsException {
        if (!isValidIndex(index)) {
            throw new TaskListIndexOutOfBoundsException(index);
        }
        List<Task> newList = new ArrayList<>(this.tasks);
        newList.remove(index);
        return new TaskList(newList);
    }

    /**
     * Marks a task's status as done or not done and returns a new immutable TaskList with the updated task.
     *
     * @param index  The index of the task to be marked.
     * @param isDone The new status of the task.
     * @return A new TaskList with the specified task's status updated.
     * @throws TaskListIndexOutOfBoundsException If the index is invalid.
     * @throws TaskStatusException If the task was already marked with the given status.
     */
    public TaskList mark(int index, boolean isDone) throws TaskListIndexOutOfBoundsException, TaskStatusException {
        if (!isValidIndex(index)) {
            throw new TaskListIndexOutOfBoundsException(index);
        }

        List<Task> updatedTasks = new ArrayList<>(this.tasks);

        Task targetTask = updatedTasks.get(index);

        Task modifiedTask = (isDone) ? targetTask.markAsDone() : targetTask.markAsNotDone();

        updatedTasks.set(index, modifiedTask);

        return new TaskList(updatedTasks);
    }

    /**
     * Checks if the provided index is valid within the TaskList.
     *
     * @param index The index to be checked.
     * @return True if the index is valid, otherwise false.
     */
    public boolean isValidIndex(int index) {
        return index >= 0 && index < this.tasks.size();
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Filters the tasks in the TaskList based on the given predicate.
     *
     * @param predicate The predicate used to filter tasks.
     * @return A new TaskList containing the filtered tasks.
     */
    public TaskList filter(Predicate<Task> predicate) {
        List<Task> filteredList = this.tasks
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());

        return new TaskList(filteredList);
    }

    /**
     * Checks if the TaskList is empty.
     *
     * @return True if the TaskList is empty, otherwise false.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Retrieves a string representation of the task at the specified index.
     *
     * @param index The index of the task.
     * @return The string representation of the task.
     * @throws TaskListIndexOutOfBoundsException If the index is invalid.
     */
    public String getTaskInfo(int index) throws TaskListIndexOutOfBoundsException {
        if (!isValidIndex(index)) {
            throw new TaskListIndexOutOfBoundsException(index);
        }
        return this.tasks.get(index).toString();
    }

    /**
     * Returns a storable string representation of all the TaskList's tasks.
     *
     * @return The storable string representation.
     */
    @Override
    public String getStorableString() {
        String storableString = "";
        for (Task task : this.tasks) {
            storableString += task.toStorableString() + "\n";
        }
        return storableString;
    }

    /**
     * Returns a string representation of task list.
     *
     * @return The string representation of task list.
     */
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            s += (i + 1) + ") " + this.tasks.get(i);
            if (i < this.tasks.size() - 1) {
                s += "\n";
            }
        }
        return s;
    }
}
