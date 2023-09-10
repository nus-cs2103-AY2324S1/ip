package robert.task;

import java.util.ArrayList;
import java.util.Iterator;

import robert.exception.RobertException;

/**
 * An implementation of the list of tasks using <tt>ArrayList</tt>. Used to track
 * all tasks that are stored in Robert.
 *
 * @author Lee Zhan Peng
 */
public class TaskList implements Iterable<Task> {

    /** The ArrayList of tasks stored */
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a task list with an existing ArrayList of tasks.
     *
     * @param tasks an ArrayList of tasks for initialisation.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets the stored task in a particular index.
     *
     * @param taskIndex the index of the task to be retrieved.
     * @return task associated to the index.
     * @throws RobertException if index is out of bounds of the list of tasks.
     */
    public Task getTask(int taskIndex) throws RobertException {
        if (taskIndex < 0 || this.getTaskCount() <= taskIndex) {
            throw new RobertException("Index is out of bounds.\n"
                    + "Please choose a valid index.");
        }
        return this.tasks.get(taskIndex);
    }

    /**
     * Gets the number of tasks stored in the task list.
     *
     * @return the number of tasks.
     */
    public int getTaskCount() {
        return this.tasks.size();
    }

    /**
     * Adds task into the task list.
     *
     * @param task the task to be added into the task list.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes task from the task list.
     *
     * @param taskIndex the index of the task to be deleted from the task list.
     * @return the deleted task.
     * @throws RobertException if index is out of bounds of the list of tasks.
     */
    public Task deleteTask(int taskIndex) throws RobertException {
        if (taskIndex < 0 || this.getTaskCount() <= taskIndex) {
            throw new RobertException("Index is out of bounds.\n"
                    + "Please choose a valid index.");
        }
        Task deletedTask = this.tasks.get(taskIndex);
        this.tasks.remove(taskIndex);
        return deletedTask;
    }

    /**
     * Marks a task from the task list.
     *
     * @param taskIndex the index of the task to be marked.
     * @throws RobertException if index is out of bounds of the list of tasks.
     */
    public void markTask(int taskIndex) throws RobertException {
        if (taskIndex < 0 || this.getTaskCount() <= taskIndex) {
            throw new RobertException("Index is out of bounds.\n"
                    + "Please choose a valid index.");
        }
        this.tasks.get(taskIndex).markAsDone();
    }

    /**
     * Unmarks a task from the task list.
     *
     * @param taskIndex the index of the task to be unmarked.
     * @throws RobertException if index is out of bounds of the list of tasks.
     */
    public void unmarkTask(int taskIndex) throws RobertException {
        if (taskIndex < 0 || this.getTaskCount() <= taskIndex) {
            throw new RobertException("Index is out of bounds.\n"
                    + "Please choose a valid index.");
        }
        this.tasks.get(taskIndex).markAsUndone();
    }

    /**
     * Removes all tasks from the task list.
     */
    public void clearTasks() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Checks whether a task exist in the task list.
     *
     * @param description the exact description of the task to be identified.
     * @return boolean on whether the task is found.
     */
    public boolean isInTaskList(String description) {
        for (Task task : this.tasks) {
            if (task.getDescription().equals(description)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator<Task> iterator() {
        return new TaskIterator();
    }

    /**
     * A private class to initialise an iterator.*
     */
    private class TaskIterator implements Iterator<Task> {

        /** The index of the task during iteration */
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < tasks.size();
        }

        @Override
        public Task next() {
            return tasks.get(currentIndex++);
        }
    }
}
