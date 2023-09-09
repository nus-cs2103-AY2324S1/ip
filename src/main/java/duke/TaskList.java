package duke;

import java.util.ArrayList;

/**
 * The tasklist to create
 */
public class TaskList {
    private final ArrayList<Task> items;

    /**
     * Converts arraylist to tasklist
     * @param items takes in arraylist
     */
    public TaskList(ArrayList<Task> items) {
        this.items = items;
    }

    /**
     * gives size
     * @return size
     */
    public int getSize() {
        return items.size();
    }

    /**
     * get a specific item
     * @param i index u want
     * @return returns that task
     */
    public Task get(int i) {
        return items.get(i);
    }

    /**
     * remove an item
     * @param i the index of item to be removed
     * @return returns the removed item
     */
    public Task remove(int i) {
        return items.remove(i);
    }

    /**
     * add a task
     * @param add the task to add
     */
    public void add(Task add) {
        items.add(add);
    }

    /**
     * converts tasklist to arr
     * @return returns the arr
     */
    public ArrayList<Task> toArray() {
        return items;
    }
}
