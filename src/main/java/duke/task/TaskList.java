package duke.task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructor for TaskList.
     *
     * @param list list of tasks to initialise the list with.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds a task to the list.
     *
     * @param task task to be added.
     */
    public void addTask(Task task) {
        this.list.add(task);
    }

    /**
     * Deletes a task from the list.
     *
     * @param index index of the task to be deleted.
     */
    public void deleteTask(int index) {
        this.list.remove(index);
    }

    /**
     * Gets a task from the list.
     *
     * @param index index of the task to be retrieved.
     * @return task at the specified index.
     */
    public Task getTask(int index) {
        return this.list.get(index);
    }

    /**
     * Marks a task as done.
     *
     * @param index index of the task to be marked.
     */
    public void markTask(int index) {
        this.list.get(index).mark();
    }

    /**
     * Unmarks a task as done.
     *
     * @param index index of the task to be unmarked.
     */
    public void unmarkTask(int index) {
        this.list.get(index).unmark();
    }

    /**
     * Returns the size of the list.
     *
     * @return size of the list.
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Returns the tasks as an ArrayList.
     *
     * @return tasks as an ArrayList.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Returns the list as a string.
     *
     * @return list as a string.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {

            sb.append(String.format("%d. %s \n", i + 1, list.get(i).toString()));
        }
        return sb.toString();
    }

}
