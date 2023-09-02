package seedu.duke.tasklist;

import seedu.duke.Exceptions.InvalidTaskIndexException;
import seedu.duke.Tasks.Task;

import java.util.ArrayList;

/**
 * The TaskList class represents a collection of tasks and provides methods
 * for editing tasks, such as adding, marking, unmarking, deleting, and retrieving tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList with an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a new TaskList with an existing list of tasks.
     *
     * @param tasks The ArrayList of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Marks a task at the specified index as done.
     *
     * @param index The index of the task to be marked as done.
     * @return String representation of the task that was marked as done.
     * @throws InvalidTaskIndexException If the provided index is invalid.
     */
    public String markTask(Integer index) throws InvalidTaskIndexException {
        try {
            Task toMark = tasks.get(index);
            toMark.markAsDone();
            return toMark.toString();
        } catch(IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException("");
        }
    }

    /**
     * Unmarks a task at the specified index as undone.
     *
     * @param index The index of the task to be marked as undone.
     * @return String representation of the task that was marked as undone.
     * @throws InvalidTaskIndexException If the provided index is invalid.
     */
    public String unmarkTask(Integer index) throws InvalidTaskIndexException {
        try {
            Task toMark = tasks.get(index);
            toMark.markAsUndone();
            return toMark.toString();
        } catch(IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException("");
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param taskToAdd The Task object to be added to the task list.
     * @return String representation of the task that was added.
     */
    public String addTasks(Task taskToAdd) {
        tasks.add(taskToAdd);
        return taskToAdd.toString();
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskToDelete The Task object to be deleted from the task list.
     * @return String representation of the task that was deleted.
     */
    public String deleteTask(Task taskToDelete) {
        tasks.remove(taskToDelete);
        return taskToDelete.toString();
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return Integer that represents the number of tasks in the task list.
     */
    public Integer getTasksSize() {
        return this.tasks.size();
    }

    /**
     * Retrieves a task at the specified index from the Arraylist of tasks.
     *
     * @param i The index of the task to retrieve.
     * @return The Task object at the specified index.
     * @throws InvalidTaskIndexException If the provided index is invalid.
     */
    public Task getTask(Integer i) throws InvalidTaskIndexException {
        try {
            return tasks.get(i);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException("Invalid Task Number.");
        }
    }

    /**
     * Retrieves the entire list of tasks.
     *
     * @return An ArrayList containing all the tasks in the task list.
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

}
