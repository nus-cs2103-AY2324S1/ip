package data;

import java.util.ArrayList;

import data.exception.DukeException;
import data.tasks.Task;
import storage.Storage;
import ui.Ui;

/**
 * The TaskList class.
 * Manages the list of tasks that the user has created.
 */
public class TaskList {
    private ArrayList<Task> tasks;

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
    }

    /**
     * Marks/unmarks the {@link Task} at the given taskIndex
     * depending on isDone.
     * 
     * @param taskIndex The index of the task stored.
     * @param isDone Whether the task is being marked as done or not done.
     * @return The task that was marked.
     * @throws DukeException Thrown when the taskIndex given
     *                       is negative or out of bounds.
     */
    public Task mark(int taskIndex, boolean isDone) throws DukeException {
        // User tries to mark/unmark a task that is out of bounds.
        if (taskIndex < 0 || taskIndex >= getSize()) {
            throw new DukeException(String.format(
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
     * @throws DukeException Thrown when the taskIndex given
     *      *                is negative or out of bounds.
     */
    public Task delete(int taskIndex) throws DukeException {
        if (taskIndex < 0 || taskIndex >= getSize()) {
            throw new DukeException(String.format(
                "Unable to "
                        + Ui.cTxt("delete", Ui.Color.PURPLE)
                        + " task %d :( You have %d task(s) stored.",
                taskIndex + 1, getSize()
            ));
        }

        Task removedTask = this.tasks.remove(taskIndex);
        return removedTask;
    }

    /**
     * Returns the string representation of the TaskList.
     * Contains string representations of all {@link Task} stored
     * which is suitable for {@link Storage} to write to memory.
     */
    @Override
    public String toString() {
        String taskListString = "";
        for (Task task : this.tasks) {
            taskListString += task.toFileFormatString() + "\n";
        }
        return taskListString.strip();
    }
}
