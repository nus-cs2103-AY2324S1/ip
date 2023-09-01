package javai;

import java.util.ArrayList;

/**
 * The TaskList class represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {

        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an initial list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {

        this.tasks = tasks;
    }

    /**
     * Gets the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int size() {

        return tasks.size();
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {

        tasks.add(task);
    }

    /**
     * Deletes a task at the specified index from the TaskList.
     *
     * @param index The index of the task to be deleted.
     */
    public void delete(int index) {

        tasks.remove(index);
    }


    /**
     * Gets a task at the specified index from the TaskList.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     * @throws JavAIException If the index is out of bounds.
     */
    public Task get(int index) throws JavAIException {

        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new JavAIException("☹ OOPS!!! The task number does not exist.Please enter valid task number.");
        }
    }

    /**
     * Gets the tasks as an ArrayList.
     *
     * @return An ArrayList containing all the tasks.
     */
    public ArrayList<Task> getTasksAsArrayList() {

        return this.tasks;
    }



}
