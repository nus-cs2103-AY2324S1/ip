package dialogix.main;

import java.util.ArrayList;

import dialogix.exception.DialogixException;
import dialogix.task.Task;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private final ArrayList<ArrayList<Task>> taskListStack;

    TaskList() {
        this.tasks = new ArrayList<>();
        taskListStack = new ArrayList<>();
    }

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        taskListStack = new ArrayList<>();
    }

    /**
     * Adds a deep copy of the task list to the stack.
     */
    public void addToStack() {
        ArrayList<Task> newList = new ArrayList<>(tasks);
        taskListStack.add(newList);
    }

    /**
     * Restores the task list by performing the undo operation.
     *
     * @param steps The number of steps to undo.
     */
    public void undo(int steps) {
        int currentStackSize = taskListStack.size();
        if (currentStackSize >= steps) {
            for (int i = 0; i < steps; i++) {
                taskListStack.remove(currentStackSize - 1 - i);
            }
            tasks = new ArrayList<>(taskListStack.get(currentStackSize - steps));
        }
    }

    /**
     * Gets the maximum undo steps available.
     *
     * @return The maximum undo steps available.
     */
    public int getMaxUndo() {
        return taskListStack.size();
    }

    /**
     * Gets all tasks present.
     *
     * @return The list of all tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Returns the task at the given zero-based index.
     *
     * @param index The zero-based index of the task to be returned.
     * @return The task at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task get(int index) throws IndexOutOfBoundsException {
        validateIndex(index);
        return tasks.get(index);
    }

    /**
     * Marks a task as done given the zero-based index of the task.
     *
     * @param index The zero-based index of the task to be marked as done.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public void markTaskAsDone(int index) throws IndexOutOfBoundsException {
        validateIndex(index);
        tasks.get(index).markAsDone();
    }

    /**
     * Adds a task to the back of the list.
     *
     * @param task The task to be added.
     * @throws IllegalArgumentException If the task is null.
     */
    public void add(Task task) throws IllegalArgumentException {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null.");
        }
        tasks.add(task);
    }

    /**
     * Deletes a task from the list given the zero-based index of the task.
     *
     * @param index The zero-based index of the task to be deleted.
     * @throws DialogixException If the index is invalid.
     */
    public void delete(int index) throws DialogixException {
        validateIndex(index);
        if (index < 0 || index >= tasks.size()) {
            throw new DialogixException("OOPS!!! The index to remove cannot be less than 0 or "
                    + "greater than the length of the list.");
        }
        tasks.remove(index);
    }

    /**
     * Returns the size of the task list.
     *
     * @return The size of the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Finds tasks in the list containing the given String in their description.
     *
     * @param toFind The criteria to filter tasks.
     * @return An ArrayList containing all tasks containing the given String in their description.
     * @throws IllegalArgumentException If the search criteria is blank.
     */
    public ArrayList<Task> find(String toFind) throws IllegalArgumentException {
        if (toFind.isBlank()) {
            throw new IllegalArgumentException("Search criteria cannot be blank.");
        }

        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDescription().contains(toFind)) {
                filteredTasks.add(t);
            }
        }
        return filteredTasks;
    }

    private void validateIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Index is out of range.");
        }
    }
}
