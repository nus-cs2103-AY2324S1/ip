package duke;

import java.util.ArrayList;

import task.Task;
public class TaskList {
    private ArrayList<Task> arrList;

    public TaskList() {
        this.arrList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> arrList) {
        this.arrList = arrList;
    }

    /**
     * Returns the encapsulated ArrayList.
     *
     * @return ArrayList Containing the Task objects
     */

    public ArrayList<Task> getArrList() {
        return this.arrList;
    }

    /**
     * Returnst he size of the list of tasks.
     *
     * @return int Size of list of tasks from the ArrayList
     */
    public int size() {
        return this.arrList.size();
    }

    /**
     * Marks and updates the tasklist.
     *
     * @param choice Number input from the user, deciding which task index to mark (Note that choice starts from 1)
     */
    public void mark(int choice) {
        this.arrList.get(choice - 1).markDone();
    }

    /**
     * Un-marks and updates the tasklist.
     *
     * @param choice Number input from the user, deciding which task index to un-mark (Note that choice starts from 1)
     */
    public void unmark(int choice) {
        this.arrList.get(choice - 1).markUndone();
    }

    /**
     * Add task to the tasklist.
     *
     * @param task Task object to be added to the tasklist
     */
    public void add(Task task) {
        this.arrList.add(task);
    }

    /**
     * Delete task of particular choice from the tasklist.
     *
     * @param choice int representing the choice of task to be removed from tasklist (Note that choice starts from 1)
     * @return Task that has been deleted
     */
    public Task delete(int choice) {
        return this.arrList.remove(choice - 1);
    }

    /**
     * Returns the String representation of the selected Task choice that is shown when listed.
     *
     * @param choice int representing the choice of task to represent from tasklist (Note that choice starts from 1)
     * @return String representation of the Task in the tasklist
     */
    public String taskToString(int choice) {
        return this.arrList.get(choice - 1).toString();
    }
}
