package duke.util;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> array;
    private TaskComparator comparator;

    /**
     * Constructor of class TaskList.
     * @param array The input ArrayList that stores the past tasks that were stored
     *            in a file if there were any.
     */
    public TaskList(ArrayList<Task> array) {
        // arr is given from the duke.util.Storage.load()
        this.array = array;
        this.comparator = new TaskComparator();
    }

    /**
     * To find tasks containing the String keyword.
     * @param keyword The input keyword.
     * @return An ArrayList containing the tasks containing the keyword.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> tasksContainingkey = new ArrayList<>();
        for (Task task : this.array) {
            if (task.toString().contains(keyword)) {
                tasksContainingkey.add(task);
            }
        }
        return tasksContainingkey;
    }

    /**
     * To add a Task into the TaskList.
     * @param instance The task added into the TaskList.
     */

    public void add(Task instance) {
        this.array.add(instance);
        this.array.sort(comparator);
    }

    /**
     * To delete a task from the TaskList.
     * @param num The index number of the Task in the TaskList.
     * @return The Task that got deleted.
     */
    public Task delete(int num) {
        if (num >= this.array.size()) {
            return null;
        }
        Task task = this.array.get(num);
        this.array.remove(num);
        return task;
    }

    /**
     * Mark the Task of the specified index number in TaskList.
     * @param num The index number of the Task in the TaskList
     * @return the marked Task.
     */
    public Task mark(int num) {
        array.get(num).setStatus(true);
        return array.get(num);
    }

    /**
     * Unmark the Task of the specified index number in TaskList.
     * @param num The index number of the Task in the TaskList
     * @return the unmarked Task.
     */
    public Task unmark(int num){
        array.get(num).setStatus(false);
        return array.get(num);
    }

    /**
     * Returns the number of Tasks in the TaskList.
     * @return The number of Tasks in the TaskList.
     */
    public int size() {
        return this.array.size();
    }

    /**
     * Returns the String representation of the TaskList.
     * @return The String representation of the TaskList.
     */

    public String getTaskDescription(int index) {
        return this.array.get(index).toString();
    }

    public Task getTask(int number) {
        return this.array.get(number);
    }


    /**
     * Returns the String that should be stored into the specified file.
     * @return the String that should be stored into the specified file.
     */
    public String toWrite() {
        String toBeWritten = "";
        for (Task task : this.array) {
            toBeWritten = toBeWritten.concat(task.toWrite());
            toBeWritten += System.lineSeparator();
        }
        return toBeWritten;
    }

}
