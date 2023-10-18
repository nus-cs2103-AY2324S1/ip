package data;

import java.util.ArrayList;
import java.util.Collections;

import data.exception.InvalidParamException;
import data.tasks.Task;

/**
 * The TaskList class.
 * Manages the list of tasks that the user has created.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * The constructor method of the TaskList class.
     * 
     * @param tasks An ArrayList if there are existing tasks
     *              else null can be passed in to instantiate
     *              an empty TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        if (tasks != null) {
            this.tasks = tasks;
        } else {
            this.tasks = new ArrayList<Task>();
        }
    }

    /**
     * Checks if the TaskList contains no tasks.
     *
     * @return A boolean indicating whether the
     *         TaskList is empty.
     */
    public boolean isEmpty() {
        return this.tasks.size() == 0;
    }

    /**
     * Retrieves the number of tasks stored in the TaskList.
     *
     * @return An integer indicating the number of tasks stored.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Retrieves the {@link Task} corresponding to the taskIndex.
     * Starts from 0.
     *
     * @param taskIndex The index of the task stored.
     * @return The task stored at the given index.
     */
    public Task getTask(int taskIndex) {
        return this.tasks.get(taskIndex);
    }

    /**
     * Appends the given {@link Task} to the list of stored tasks.
     *
     * @param task The task to be stored in the list.
     */
    public void add(Task task) {
        this.tasks.add(task);
        Collections.sort(tasks);
        assert this.tasks.contains(task);
    }

    /**
     * Marks/unmarks the {@link Task} at the given taskIndex
     * depending on isDone.
     *
     * @param taskIndex The index of the task stored.
     * @param isDone Whether the task is being marked as done or not done.
     * @return The task that was marked.
     * @throws InvalidParamException Thrown when the taskIndex given
     *                               is negative or out of bounds.
     */
    public Task mark(int taskIndex, boolean isDone) throws InvalidParamException {
        // User tries to mark/unmark a task that is out of bounds.
        if (taskIndex < 0 || taskIndex >= getSize()) {
            throw new InvalidParamException(String.format(
                "Unable to %s task %d :( You have %d task(s) stored.",
                isDone ? "mark" : "unmark", taskIndex, getSize()
            ));
        }

        Task task = getTask(taskIndex);
        if (isDone) {
            task.mark();
        } else {
            task.unmark();
        }
        return task;
    }

    /**
     * Deletes the {@link Task} at the given taskIndex.
     *
     * @param taskIndex The index of the task stored.
     * @return The task that was deleted.
     * @throws InvalidParamException Thrown when the taskIndex given
     *      *                        is negative or out of bounds.
     */
    public Task delete(int taskIndex) throws InvalidParamException {
        if (taskIndex < 0 || taskIndex >= getSize()) {
            throw new InvalidParamException(String.format(
                "Unable to delete task %d :( You have %d task(s) stored.",
                taskIndex + 1, getSize()
            ));
        }

        Task removedTask = this.tasks.remove(taskIndex);
        assert !this.tasks.contains(removedTask) : "deleted task still found in tasks";
        return removedTask;
    }

    /**
     * Returns the string representation of the TaskList.
     * Contains string representations of all {@link Task} stored
     * which is suitable to write to a persistent file.
     *
     * @param keyword The keyword to be used to find tasks
     *                containing it.
     * @return An ArrayList of {@link Task} whose description
     *         contains the keyword given.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            int index = t.findKeyword(keyword);
            if (index >= 0) {
                result.add(t);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder taskListString = new StringBuilder();
        for (Task task : this.tasks) {
            taskListString
                .append(task.toFileFormatString())
                .append("\n");
        }
        return taskListString.toString().strip();
    }
}
