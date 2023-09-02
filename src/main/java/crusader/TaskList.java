package crusader;

import crusader.exception.CrusaderException;
import crusader.exception.CrusaderNoSuchTaskException;
import crusader.task.Task;

import java.util.ArrayList;

/**
 * A set of tasks to do
 */
public class TaskList {
    /**
     * The list of task
     */
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty tasklist
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initializes a tasklist with a pre-existing list of tasks
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Describes the list of tasks in the task list.
     */
    @Override
    public String toString() {
        String returnString = "";
        for (int x = 0; x < tasks.size(); x++) {
            returnString = String.format("%s%d. %s\n", returnString, x + 1, tasks.get(x).toString());
        }
        return returnString;
    }

    /**
     * Marks a task as done.
     *
     * @param index index of the task to be marked. 1-indexed.
     * @return the updated task.
     */
    public Task markTask(int index) throws CrusaderException {
        try {
            Task task = tasks.get(index - 1);
            task.mark();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new CrusaderNoSuchTaskException("There is no task at that index!");
        }
    }

    /**
     * Unmarks a task.
     *
     * @param index index of the task to be unmarked. 1-indexed.
     * @return the updated task.
     */
    public Task unmarkTask(int index) throws CrusaderException {
        try {
            Task task = tasks.get(index - 1);
            task.unmark();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new CrusaderNoSuchTaskException("There is no task at that index!");
        }
    }

    /**
     * Deletes a task.
     *
     * @param index index of the task to be deleted. 1-indexed.
     * @return the deleted task.
     */
    public Task deleteTask(int index) throws CrusaderException {
        try {
            Task task = tasks.get(index - 1);
            tasks.remove(task);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new CrusaderNoSuchTaskException("There is no task at that index!");
        }
    }

    /**
     * Gets the current size of the task list.
     * @return the size of the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Adds a task to the task list.
     * @param task task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Gets the task list.
     *
     * @return the task list.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
