package duke.util;

import duke.task.Task;

import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> arr;

    /**
     * Constructor of class TaskList.
     * @param arr The input ArrayList that stores the past tasks that were stored
     *            in a file if there were any.
     */
    public TaskList(ArrayList<Task> arr) {
        // arr is given from the duke.util.Storage.load()
        this.arr = arr;
    }

    /**
     * To add a Task into the TaskList.
     * @param instance The task added into the TaskList.
     */
    public void add(Task instance) {
        this.arr.add(instance);
    }

    /**
     * To delete a task from the TaskList.
     * @param num The index number of the Task in the TaskList.
     * @return The Task that got deleted.
     */
    public Task delete(int num) {
        if (num >= this.arr.size()) {
            return null;
        }
        Task task = this.arr.get(num);
        this.arr.remove(num);
        return task;
    }

    /**
     * Mark the Task of the specified index number in TaskList.
     * @param num The index number of the Task in the TaskList
     * @return the marked Task.
     */
    public Task mark(int num) {
        return arr.get(num).description(true);
    }

    /**
     * Unmark the Task of the specified index number in TaskList.
     * @param num The index number of the Task in the TaskList
     * @return the unmarked Task.
     */
    public Task unmark(int num){
        return arr.get(num).description(false);
    }

    /**
     * Returns the number of Tasks in the TaskList.
     * @return The number of Tasks in the TaskList.
     */
    public int size() {
        return this.arr.size();
    }

    /**
     * Returns the String representation of the TaskList.
     * @return The String representation of the TaskList.
     */
    @Override
    public String toString() {
        String toBePrinted = "";
        for (int i = 0; i < arr.size(); i++) {
            toBePrinted = toBePrinted.concat(arr.get(i).toString());
            toBePrinted += System.lineSeparator();
        }
        return toBePrinted;
    }


    /**
     * Returns the String that should be stored into the specified file.
     * @return the String that should be stored into the specified file.
     */
    public String toWrite() {
        String toBeWritten = "";
        for (int i = 0; i < arr.size(); i++) {
            toBeWritten = toBeWritten.concat(arr.get(i).toWrite());
            toBeWritten += System.lineSeparator();
        }
        return toBeWritten;
    }

}
