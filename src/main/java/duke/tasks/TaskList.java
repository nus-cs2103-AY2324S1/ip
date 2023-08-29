package duke.tasks;

import java.util.ArrayList;

/**
 * Represents the TaskList Class.
 *
 * @author Shishir
 */
public class TaskList {

    /** List of all tasks */
    private ArrayList<Task> tasks;

    /**
     * Constructs the TaskList Class.
     * @param tasks List of initial tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the list.
     * @param newTask New task to be added.
     */
    public void add(Task newTask) {
        this.tasks.add(newTask);
    }

    /**
     * Marks the task with the given index.
     * @param index Index of the task to be marked.
     */
    public void mark(int index) {
        this.tasks.get(index).completeTask();
    }

    /**
     * Unmarks the task with the given index.
     * @param index Index of the task to be unmarked.
     */
    public void unmark(int index) {
        this.tasks.get(index).revertTask();
    }

    /** Prints the list of all tasks. */
    public void list() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i + 1) + ") " + this.tasks.get(i).toString());
        }
    }

    /**
     * Deletes the task with the given index.
     * @param index Index of the task to be deleted.
     */
    public void delete(int index) {
        this.tasks.remove(index);
    }

    /**
     * Returns the size of the list.
     * @return Size of the list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns the task at the given index.
     * @param index Index of required task.
     * @return Required task.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the list of all the tasks.
     * @return List of all tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }

}
