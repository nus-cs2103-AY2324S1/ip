package duck.task;

import java.util.ArrayList;

import duck.DuckException;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int taskCount;

    /**
     * Initialises an empty TaskList.
     * For when no history is found.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.taskCount = 0;
    }

    /**
     * Initialises a TaskList with the given tasks.
     * For when history is available.
     * 
     * @param tasks ArrayList of tasks to initialise the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.taskCount = tasks.size();
    }

    // Getter for taskCount
    public int getTaskCount() {
        return this.taskCount;
    }

    /**
     * Adds a task to the end of the list.
     * 
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
        taskCount++;
    }

    /**
     * Returns the task at a given index.
     * 
     * @param index Index of the task to be retrieved.
     * @return Task at the given index.
     * @throws DuckException If the index is invalid.
     */
    public Task getTask(int index) throws DuckException {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DuckException("Error - invalid task number.");
        }
    }

    /**
     * Marks the task at a given index.
     * 
     * @param index Index of the task to be marked.
     * @throws DuckException If the index is invalid or when the task was already marked.
     */
    public void mark(int index) throws DuckException {
        Task currTask;
        try {
            currTask = tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DuckException("Error - invalid task number.");
        }
        currTask.mark();
    }

    /**
     * Unmarks the task at a given index.
     * 
     * @param index Index of the task to be unmarked.
     * @throws DuckException If the index is invalid or when the task was already unmarked.
     */
    public void unmark(int index) throws DuckException {
        Task currTask;
        try {
            currTask = tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DuckException("Error - invalid task number.");
        }
        currTask.unmark();
    }

    /**
     * Deletes the task at a given index.
     * 
     * @param index Index of the task to be deleted.
     * @throws DuckException If the index is invalid.
     */
    public void delete(int index) throws DuckException{
        try {
            tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DuckException("Error - invalid task number.");
        }
        taskCount--;
    }

    /**
     * Converts the TaskList into a readable string represenation to be printed.
     * 
     * @return Readable string representation of the TaskList.
     */
    @Override
    public String toString() {
        String allTasks = "";
        for (int i = 0; i < taskCount; i++) {
            allTasks += "\n" + (i + 1) + ". " + tasks.get(i);
        }
        return allTasks;
    }
}


