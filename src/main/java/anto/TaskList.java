package anto;

import java.util.ArrayList;

/**
 * TaskList class represents list of all current tasks and handles all actions
 * related to tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;
    private Storage storage;

    /**
     * Creates a new TaskList class.
     *
     * @param taskList Initial ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Add task given to arraylist.
     *
     * @param newTask Task to be added.
     * @throws AntoException Throws anto exception if there is an IO Exception
     */
    public void addToList(Task newTask) throws AntoException {
        this.taskList.add(newTask);
        this.storage.addTaskToStorage(newTask);
    }

    /**
     * Returns current array list of tasks.
     *
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> getTaskArrayList() {
        return this.taskList;
    }

    /**
     * Returns current number of tasks.
     *
     * @return Current number of tasks.
     */
    public int getLength() {
        return this.taskList.size();
    }

    /**
     * Marks task at index as done.
     *
     * @param index Index of task to mark as done.
     * @throws AntoException Throws AntoException if there is an IO Exception.
     */
    public void markTaskAsDone(int index) throws AntoException {
        this.storage.markTaskAsDone(index);
        this.taskList.get(index).markAsDone();
    }

    /**
     * Unmark task at index.
     *
     * @param index Index of task to unmark.
     * @throws AntoException Throws AntoException if there is an IO Exception.
     */
    public void unmarkTask(int index) throws AntoException {
        this.storage.unmarkTask(index);
        this.taskList.get(index).unmark();
    }

    /**
     * Deletes task at given index.
     *
     * @param index Index of task to delete.
     * @return Deleted task.
     * @throws AntoException Throws AntoException if there is an IO Exception.
     */
    public Task deleteTask(int index) throws AntoException {
        this.storage.deleteTask(index);
        return this.taskList.remove(index);
    }

    /**
     * Find tasks with matching keyword.
     *
     * @param keyword Keyword to search tasks.
     * @return Return Array List of tasks with specified keyword.
     */
    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();

        for (Task curr : this.taskList) {
            if (curr.description.contains(keyword)) {
                foundTasks.add(curr);
            }
        }

        return foundTasks;
    }

}
