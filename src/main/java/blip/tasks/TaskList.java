package blip.tasks;

import java.util.ArrayList;

/**
 * Represents a list of tasks in Blip ChatBot.
 */
public class TaskList {
    /**
     * Array List of tasks.
     */
    private ArrayList<Task> tasks;

    /**
     * Constructor of TaskList with existing array list.
     * @param tasks The Array List of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructor of TaskList if no array list is created yet.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the number of tasks in task list.
     * @return Int number of tasks in task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task from task list with index.
     * @param index The index of the task to retrieve from task list
     * @return The task from task list
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Deletes task from task list.
     * @param index The index of the task to delete from task list
     */
    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Adds task to task list.
     * @param task The task to add into task list.
     */
    public void addTask(Task task) {
        if (task != null) {
            this.tasks.add(task);
        }
    }

    /**
     * Marks task as done.
     * @param index The index of task to mark.
     */
    public void markTask(int index) {
        this.tasks.get(index).markStatus();
    }

    /**
     * Unmarks task as not done.
     * @param index The index of task to unmark.
     */
    public void unmarkTask(int index) {
        this.tasks.get(index).unmarkStatus();
    }

}
