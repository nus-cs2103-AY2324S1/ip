package duke;

import java.util.ArrayList;
import java.util.function.Predicate;

import duke.task.Task;

/**
 * Represents the temporary storage used when running the application.
 *
 * @author Donovan Chan Jia Jun
 */
public class TaskList {
    private ArrayList<Task> arrList;

    public TaskList() {
        this.arrList = new ArrayList<>();
    }

    /**
     * Constructs TaskList instance to store list of Task.
     *
     * @param arrList ArrayList containing Tasks of the user
     */
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
     * Returns a list of tasks that contain the word as the name.
     *
     * @param word String input by user representing the task name to correspond to
     * @return ArrayList of tasks that corresponds to the word input
     */
    public ArrayList<Task> find(String word) {
        Predicate<Task> findName = task -> task.containName(word);
        ArrayList<Task> tempList = new ArrayList<>();
        for (Task task : this.arrList) {
            if (findName.test(task)) {
                tempList.add(task);
            }
        }
        return tempList;
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
