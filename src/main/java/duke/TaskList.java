package duke;

import duke.task.Task;
import java.util.List;
import java.util.ArrayList;

/**
 * The TaskList class represents a list of tasks in Duke.
 * It provides methods for adding, removing, and retrieving tasks from the list.
 */
public class TaskList{
    private List<Task> tasks = new ArrayList<>();
    /**
     * Default constructor to initialize an empty TaskList.
     */
    public TaskList() {
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task){
        this.tasks.add(task);
    }

    /**
     * Removes a task from the task list at the specified index.
     *
     * @param index The index of the task to be removed.
     * @return The removed task.
     */
    public Task removeTask(int index){
        return this.tasks.remove(index);
    }

    /**
     * Retrieves a task from the task list at the specified index.
     *
     * @param index The index of the task to be retrieved.
     * @return The retrieved task.
     */
    public Task getTask(int index){
        return this.tasks.get(index);
    }

    /**
     * Marks a task in the task list as done.
     *
     * @param index The index of the task to be marked as done.
     */

    public void markDone(int index){
        this.tasks.get(index).markAsDone();
    }

    /**
     * Marks a task in the task list as not done.
     *
     * @param index The index of the task to be marked as not done.
     */
    public void markNotDone(int index){
        this.tasks.get(index).markAsNotDone();
    }

    /**
     * Returns the size of the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int getSize() {
        return tasks.size();
    }

}