package duke.task;

import duke.exception.DukeException;
import java.util.ArrayList;

/**
 * Manages a list of tasks and provides methods to perform operations on them.
 */
public class TaskList {
    ArrayList<Task> taskArr;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.taskArr = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param taskArr The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> taskArr) {
        this.taskArr = taskArr;
    }

    /**
     * Returns the size of the task list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return taskArr.size();
    }

    /**
     * Gets a task from the list at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return this.taskArr.get(index);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        this.taskArr.add(task);
    }

    /**
     * Deletes a task from the task list at the specified index.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        this.taskArr.remove(index);
    }

    /**
     * Marks a task in the task list as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markTask(int index) {
        taskArr.get(index).markAsDone();
    }

    /**
     * Unmarks a task in the task list if it is marked as done.
     *
     * @param index The index of the task to be unmarked.
     * @throws DukeException If the task is not marked as done.
     */
    public void unmarkTask(int index) throws DukeException {
        taskArr.get(index).unmark();
    }
}
