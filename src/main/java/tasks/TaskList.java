package tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import storage.StorableList;

/**
 * A class representing a list of tasks.
 *
 * This class implements the StorableList interface to provide methods for tasks storing.
 */
public class TaskList implements StorableList<Task> {
    private List<Task> tasks;

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
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param t The task to be added.
     */
    public void add(Task t) {
        this.tasks.add(t);
    }
    
    /**
     * Removes a task at the specified index from the TaskList.
     *
     * @param index The index of the task to be removed.
     * @throws TaskListIndexOutOfBoundsException If the index is invalid.
     */
    public void remove(int index) throws TaskListIndexOutOfBoundsException {
        if (!isValidIndex(index)) {
            throw new TaskListIndexOutOfBoundsException(index);
        }
        this.tasks.remove(index);
    }

    /**
     * Marks a task's status as done or not done.
     *
     * @param index  The index of the task to be marked.
     * @param status The new status of the task.
     * @throws TaskListIndexOutOfBoundsException If the index is invalid.
     * @throws TaskStatusException If task was already marked as the given status.
     */
    public void mark(int index, boolean status) throws TaskListIndexOutOfBoundsException, TaskStatusException{
        if (!isValidIndex(index)) {
            throw new TaskListIndexOutOfBoundsException(index);
        }
        
        Task task = this.tasks.get(index);

        if (status) {
            task.markAsDone();
        } else {
            task.markAsNotDone();
        }
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
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public static void printTasks(TaskList tl) {
        for (int i = 0; i < tl.tasks.size(); i++) {
            System.out.println((i+1) + ") " + tl.tasks.get(i));
        }
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
}
