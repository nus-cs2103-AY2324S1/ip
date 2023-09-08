package bob;

import java.util.ArrayList;

/**
 * Represents the list that contains all of the tasks, with methods to add and delete tasks.
 */
public class TaskList {

    private ArrayList<Task> list;

    /**
     * Constructor for TaskList class.
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Constructor for TaskList class.
     *
     * @param prevList the previous list of tasks if it exists.
     */
    public TaskList(ArrayList<Task> prevList) {
        this.list = prevList;
    }

    /**
     * Returns the Task corresponding to the specified index.
     *
     * @param index of Task wanted.
     * @return Task in the index.
     */
    public Task get(int index) {
        return this.list.get(index);
    }

    /**
     * Returns the size of the list, i.e. the total number of tasks.
     *
     * @return int the size of the list.
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Removes a Task from the list corresponding to the specified index.
     *
     * @param index the element to be removed.
     */
    public void remove(int index) {
        this.list.remove(index);
    }

    /**
     * Adds a Task to the list.
     *
     * @param task to be added to the list.
     */
    public void add(Task task) {
        this.list.add(task);
    }
}
