package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import duke.DukeException;

/**
 * Represents a list of tasks (todos/deadlines/events).
 */
public class TaskList {

    /** The list of tasks present in the TaskList. */
    private List<Task> tasks;

    /**
     * Creates a new empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a new TaskList with existing data from a Stream of tasks.
     * @param taskStream The Stream of tasks to add to the TaskList.
     */
    public TaskList(Stream<Task> taskStream) {
        tasks = taskStream.collect(Collectors.toList());
    }

    /**
     * Adds a new Task into the TaskList.
     *
     * @param task The task to be added into the TaskList.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Removes a Task with a specified index from the TaskList, then returns the removed Task.
     *
     * @param i The task index to be removed from the TaskList.
     * @return The Task that was removed from the TaskList.
     */
    public Task remove(int i) {
        return this.tasks.remove(i - 1);
    }

    /**
     * Returns the String representation of the Task with the specified index.
     *
     * @param i The task index to be accessed within the TaskList.
     * @return String representation of the Task with the specified index within the TaskList.
     */
    public String getTaskString(int i) {
        return this.tasks.get(i - 1).toString();
    }

    /**
     * Gets the alternate String representation of every Task within the TaskList (to be saved) and stores them into a
     * List, in order.
     *
     * @return The List containing all the save-file String representation of the Tasks within the TaskList.
     */
    public List<String> getSavedStrings() {
        return tasks
                .stream()
                .map(Task::toSaveFormatString)
                .collect(Collectors.toList());
    }

    /**
     * Updates a Task with a specified index.
     *
     * @param i The index of the Task to update within the TaskList.
     * @param updateType The part of the Task to be updated.
     * @param newValue The new value to put into the updated Task (as a String).
     */
    public void updateTask(int i, UpdateType updateType, String newValue) throws DukeException {
        this.tasks.get(i - 1).update(updateType, newValue);
    }

    /**
     * Clones a Task and adds it to the back of the TaskList.
     *
     * @param i The index of the Task to clone within the TaskList.
     */
    public void cloneTask(int i) {
        Task toClone = tasks.get(i - 1).clone();
        tasks.add(toClone);
    }


    /**
     * Marks a Task with a specified index as done.
     *
     * @param i The index of the Task to mark as done within the TaskList.
     */
    public void markAsDone(int i) {
        this.tasks.get(i - 1).markAsDone();
    }

    /**
     * Marks a Task with a specified index as undone.
     *
     * @param i The index of the Task to mark as undone within the TaskList.
     */
    public void unmarkAsDone(int i) {
        this.tasks.get(i - 1).unmarkAsDone();
    }

    /**
     * Finds all tasks with description containing the search string, and returns the list of tasks as a TaskList.
     *
     * @param findStr The search string to search for the tasks.
     * @return TaskList containing all tasks with description containing the search string; an empty TaskList if no
     *         matching tasks are found.
     */
    public TaskList find(String findStr) {
        return new TaskList(tasks
                .stream()
                .filter(task -> task.contains(findStr)));
    }
}
