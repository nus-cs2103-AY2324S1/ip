package duke;

import duke.tasks.Task;

import java.util.ArrayList;

/**
 * Represents and handles a list of tasks in application.
 */
public class TaskList {

    protected ArrayList<Task> taskList;

    /**
     * Initialises a new TaskList object.
     * @param store DataStorage to access saved tasks.
     */
    public TaskList(DataStorage store) {
        taskList = store.taskDataList;
    }

    /**
     * Adds a specified task from taskList.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a specified task from taskList.
     *
     * @param taskIndex Index of task to be deleted.
     * @throws DukeException If error produced during execution.
     */
    public void delete(int taskIndex) throws DukeException {
        if (taskIndex >= (taskList.size()) || taskIndex < 0) {
            throw new DukeException("There are only " + taskList.size() + " tasks");
        } else {
            taskList.remove(taskIndex);
        }
    }

    /**
     * Marks a specified task from taskList.
     *
     * @param taskIndex Index of task to be marked.
     * @throws DukeException If error produced during execution.
     */
    public void mark(int taskIndex) throws DukeException {
        if (taskIndex >= (taskList.size()) || taskIndex < 0) {
            throw new DukeException("There are only " + taskList.size() + " tasks");
        } else {
            taskList.get(taskIndex).markAsDone();
        }
    }

    /**
     * Unmarks a specified task from taskList.
     *
     * @param taskIndex Index of task to be unmarked.
     * @throws DukeException If error produced during execution.
     */
    public void unmark(int taskIndex) throws DukeException {
        if (taskIndex >= (taskList.size()) || taskIndex < 0) {
            throw new DukeException("There are only " + taskList.size() + " tasks");
        } else {
            taskList.get(taskIndex).markAsUndone();
        }
    }

    /**
     * Returns the task corresponding to the given task index.
     *
     * @param taskIndex the index of the task that is to be retrieved.
     * @return The task corresponding to the given task index.
     */
    public Task getTask(int taskIndex) {
        return taskList.get(taskIndex);
    }

    /**
     * Returns the number of tasks in the taskList..
     *
     * @return The length of the taskList.
     */
    public int getLength() {
        return taskList.size();
    }

    /**
     * Returns the ArrayList of tasks.
     *
     * @return The ArrayList of tasks..
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

}
