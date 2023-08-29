package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents the task list object that contains an array list of tasks.
 */
public class TaskList {
    /** Array List that represents the task list. */
    private ArrayList<Task> taskList;

    /**
     * Constructor for creating an empty task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor for when there is successful loading of cached task list.
     * @param tasks Task List to be loaded into the chatbot.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    /**
     * Returns an array list containing all the tasks.
     * 
     * @return Array list that contains all the tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return taskList;
    }

    /**
     * Returns the task at the specified index.
     * 
     * @param index Index of the specified task.
     * @return The specified task at the index.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Marks the task at the specified index.
     * 
     * @param index The index of the task to be marked.
     */
    public void markTask(int index) {
        taskList.get(index).changeStatus();
    }

    /**
     * Adds the task to the task list.
     * 
     * @param task The task to be added to the list.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a task at the specified index in the task list.
     * 
     * @param index The index of the task to be deleted.
     * @throws DukeException If index is invalid.
     */
    public void delete(int index) throws DukeException {
        if (index < 0 || index >= taskList.size()) {
            throw new DukeException("OOPS! The index to remove is invalid!");
        }

        taskList.remove(index);
    }

    /**
     * Returns the size of the task list.
     * 
     * @return Size of the task list.
     */
    public int size() {
        return taskList.size();
    }
}
