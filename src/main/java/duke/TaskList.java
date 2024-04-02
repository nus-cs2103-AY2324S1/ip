package duke;

import java.util.ArrayList;

/**
 * Represents the list of tasks.
 */
public class TaskList {
    private ArrayList<Task> lst;

    public TaskList(ArrayList<Task> list){
        lst = list;
    }

    public TaskList() {
        lst = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     * @param task task added to list.
     */
    public void add(Task task) {
        lst.add(task);
    }

    /**
     * Deletes a task from the list.
     * @param index index of task in the list.
     */
    public void delete(int index) {
        Task t = lst.get(index);
        lst.remove(t);
    }

    /**
     * Marks a task as done.
     * @param index index of task in the list.
     */
    public void markTask(int index){
        lst.get(index).markAsDone();
    }

    /**
     * Marks a task as not done.
     * @param index index of task in the list.
     */
    public void unmarkTask(int index) {
        lst.get(index).markAsNotDone();
    }

    /**
     * Gets a task from the list.
     * @param index index of task in the list.
     * @return task of that index.
     */
    public Task get(int index) {
        return lst.get(index);
    }

    /**
     * Gets the total number of tasks in the list.
     * @return the total number of tasks in the list.
     */
    public int total() {
        return lst.size();
    }

}
