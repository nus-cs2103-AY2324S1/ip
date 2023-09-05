package thea;

import java.util.ArrayList;

/**
 * Represents a list of current tasks.
 */
public class TaskList {
    ArrayList<Task> taskArrayList;

    /**
     * Constructs a new empty TaskList object.
     */
    public TaskList() {
        this.taskArrayList = new ArrayList<>();
    }

    /**
     * Constructs a new TaskList object from an existing list.
     *
     * @param taskArrayList ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    /**
     * Indicates whether itself is empty.
     */
    public boolean isEmpty() {
        return this.taskArrayList.isEmpty();
    }

    /**
     * Gets a task of a specified index.
     *
     * @param index index.
     * @return task of the specified index.
     */
    public Task get(int index)  {
        return this.taskArrayList.get(index);
    }

    /**
     * Returns the size of the list.
     *
     * @return size of the list.
     */
    public int size() {
        return this.taskArrayList.size();
    }

    /**
     * Adds a task to itself.
     *
     * @param task the task to be added.
     */
    public void add(Task task) {
        this.taskArrayList.add(task);
    }

    /**
     * Deletes a task of a specified index
     *
     * @param index index.
     * @throws IndexOutOfBoundsException if index does not exist.
     */
    public void delete(int index) throws IndexOutOfBoundsException {
        if (index > (this.size() - 1)) {
            throw new IndexOutOfBoundsException("There is currently no task " + (index + 1));
        } else {
            this.taskArrayList.remove(index);
        }
    }

    /**
     * Marks a task of a specified index
     *
     * @param index index.
     * @throws IndexOutOfBoundsException if index does not exist.
     */
    public void mark(int index) throws IndexOutOfBoundsException {
        if (index > (this.size() - 1)) {
            throw new IndexOutOfBoundsException("There is currently no task " + (index + 1));
        } else {
            this.taskArrayList.get(index).markAsDone();
        }
    }

    /**
     * Unmarks a task of a specified index
     *
     * @param index index.
     * @throws IndexOutOfBoundsException if index does not exist.
     */
    public void unmark(int index) throws IndexOutOfBoundsException {
        if (index > (this.size() - 1)) {
            throw new IndexOutOfBoundsException("There is currently no task " + (index + 1));
        } else {
            this.taskArrayList.get(index).unmarkAsDone();
        }
    }
}
