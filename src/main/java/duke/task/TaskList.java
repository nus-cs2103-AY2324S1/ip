package duke.task;

import java.util.ArrayList;

import duke.exceptions.CommandDetailException;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }
    /**
     * Deletes a task from the list.
     *
     * @param index The index of the task to be deleted.
     * @throws CommandDetailException If the index is out of bounds.
     */
    public void deleteTask(int index) throws CommandDetailException {
        try {
            tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandDetailException("OOPS!!! There is no such task!");
        }
    }


    public Task getTask(int index) throws CommandDetailException {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandDetailException("OOPS!!! There is no such task!");
        }
    }
    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked as done.
     * @throws CommandDetailException If the index is out of bounds.
     */
    public void markTask(int index) throws CommandDetailException {
        this.getTask(index).setDone();
    }

    public void unmarkTask(int index) throws CommandDetailException {
        this.getTask(index).setUndone();
    }
    /**
     * Finds tasks that contain the specified keyword.
     *
     * @param keyword The keyword to search for.
     * @return An {@link ArrayList} of tasks that contain the specified keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTaskName().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

    public int size() {
        return tasks.size();
    }

    public void clearTasks() {
        tasks = new ArrayList<>();
    }
    /**
     * Converts the list of tasks to a string representation.
     *
     * @return A string representation of the list of tasks.
     */
    public String toStorage() {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.toSaveFormat()).append("\n");
        }
        return sb.toString();
    }
}
