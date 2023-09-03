package duke;

import java.util.ArrayList;

/**
 * The TaskList class is a mutable delegation of an ArrayList of Task objects.
 * This class is used to encapsulate the list of Tasks and perform certain functions
 * internally.
 */
public class TaskList {
    ArrayList<Task> taskList;

    /**
     * Creates a TaskList object with an input list of tasks.
     *
     * @param taskList The ArrayList of tasks to be taken into the TaskList class.
     */
    TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Creates a TaskList object with an empty list of tasks.
     */
    TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Returns the taskList.
     * Useful when certain ArrayList class functions need to be performed
     * on the list of tasks directly.
     *
     * @return The taskList
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Returns the String representation of a task at a given index.
     *
     * @param a The index at which the Task is located.
     * @return String representation of the Task.
     */
    public String taskString(int a) {
        return this.taskList.get(a).toString();
    }

    /**
     * Adds a task to the contained list of Tasks.
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Retrieves a Task object from a given index.
     *
     * @param a The index at which the Task is located.
     * @return The Task object located at the given index.
     */
    public Task get(int a) {
        return this.taskList.get(a);
    }

    /**
     * Deletes a Task object at a given index.
     *
     * @param a The index at which the Task is located.
     */
    public void remove(int a) {
        this.taskList.remove(a);
    }

    /**
     * Returns the size of the contained list of tasks.
     *
     * @return int value of the size of the contained list of tasks.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param index The index at which the Task is located.
     */
    public void mark(int index) {
        this.taskList.get(index).mark();
    }

    /**
     * Marks the task at the given index as undone.
     *
     * @param index The index at which the Task is located.
     */
    public void unmark(int index) {
        this.taskList.get(index).unmark();
    }

    /**
     * Iterates through the contained list of tasks and outputs them
     * to the Ui class for printing to standard output.
     */
    public void taskIterator() {
        for (int i = 0; i < taskList.size(); i++) {
            Ui.staticTabPrinter(String.format("%d. %s", i + 1,
                    taskList.get(i).toString()));
        }
    }
}
