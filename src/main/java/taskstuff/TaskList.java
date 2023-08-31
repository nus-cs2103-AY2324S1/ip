package taskstuff;

import java.util.ArrayList;

import duke.DukeException;
/**
 * A class to hold a list of tasks and perform operations on
 * this list.
 */
public class TaskList {

    /** An ArrayList to hold tasks entered by the User. */
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }


    /**
     * Adds the given task to the tasks ArrayList.
     * Prints that the task has been added.
     *
     * @param task The task to add.
     *
     */
    public void addTask(Task task) {
        tasks.add(task);


    }

    /**
     * Returns a string describing the tasks present in tasks ArrayList.
     *
     * @return Returns a string describing this taskList.
     */
    public String[] getTasks() {
        String[] s = new String[this.tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            s[i] = tasks.get(i).toString();
        }
        return s;
    }

    /**
     * Marks a given task.Task and throws duke.DukeException if invalid index.
     *
     * @param index The location of the task in tasks ArrayList.
     */
    public void markTask(int index) throws DukeException {
        if (index > tasks.size() || index <= 0) {
            throw new DukeException("The index is not a valid index. Try again.");
        }
        tasks.get(index - 1).setAsDone();
    }

    /**
     * Marks a given task.Task as not done and throws duke.DukeException if invalid index.
     *
     * @param index The location of the task in tasks ArrayList.
     */
    public void unmarkTask(int index) throws DukeException {
        if (index > tasks.size() || index <= 0) {
            throw new DukeException("The index is not a valid index. Try again.");
        }
        tasks.get(index - 1).setAsNotDone();

    }

    /**
     * Deletes a given task.Task from the tasklist and throws duke.DukeException if invalid index.
     * Returns the deleted task's string representation.
     *
     * @param index The location of the task in tasks ArrayList.
     * @return Returns the string of deleted task if successful.
     */
    public String deleteTask(int index) throws DukeException {
        if (index > tasks.size() || index <= 0) {
            throw new DukeException("The index is not a valid index. Try again.");
        }
        String task = tasks.get(index - 1).toString();
        tasks.remove(index - 1);
        return task;
    }


    /**
     * Returns the size of the taskList.
     * @return Returns the size of the current taskList.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the string representation of task at index i.
     * If invalid index duke.DukeException is thrown.
     *
     * @param index The index of task whose string representation is needed.
     */
    public String getTask(int index) throws DukeException {
        if (index > tasks.size() || index <= 0) {
            throw new DukeException("");
        }
        return this.tasks.get(index - 1).toString();
    }
}
