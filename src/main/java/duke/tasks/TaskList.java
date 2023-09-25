package duke.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * A <code>TaskList</code> object keeps track of all tasks that the user has noted.
 */

public class TaskList {
    private static List<Task> taskLists;

    /**
     * The empty class constructor. Creates an empty <code>TaskList</code>
     */
    public TaskList() {
        taskLists = new ArrayList<>();
    }

    /**
     * The class constructor with parameters.
     *
     * @param arr A <code>List</code> of existing tasks
     */
    public TaskList(List<Task> arr) {
        taskLists = arr;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The <code>Task</code> to be added.
     */
    public void addTask(Task task) {
        taskLists.add(task);
    }

    /**
     * Returns a specified task from the list.
     * Does not remove the task.
     *
     * @param index The index location of the <code>Task</code> to be provided.
     * @return The specified <code>Task</code>
     */
    public Task getTask(int index) {
        return taskLists.get(index);
    }

    /**
     * Marks a <code>Task</code> as done.
     *
     * @param index The index of the <code>Task</code> to be marked as done.
     */
    public void mark(int index) {
        taskLists.get(index).markDone();
    }

    /**
     * Marks a <code>Task</code> as unfinished.
     *
     * @param index The index of the <code>Task</code> to be marked as unfinished.
     */
    public void unmark(int index) {
        taskLists.get(index).markUndone();
    }

    /**
     * Returns the length of the current list.
     */
    public int getLength() {
        return taskLists.size();
    }

    /**
     * Removes a specified <code>Task</code> from the list.
     *
     * @param index The index of the <code>Task</code> to be removed.
     */
    public void remove(int index) {
        taskLists.remove(index);
    }

    /**
     * Returns the <code>String</code> representation of the <code>TaskList</code>
     *
     * @return The string representing a list of all tasks
     */
    public String writeTaskList() {
        String res = "";
        String temp;
        for (int i = 0; i < taskLists.size(); i++) {
            temp = taskLists.get(i).storedString();
            res = res + temp + "\n";
        }
        return res;
    }
}
