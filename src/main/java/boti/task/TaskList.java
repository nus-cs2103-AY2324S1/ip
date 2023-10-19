package boti.task;

import java.util.ArrayList;

/**
 * The TaskList class for the list of tasks
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Instantiates the TaskList
     *
     * @param tasks the list of the tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets the task with a certain index
     *
     * @param index the index of the task
     * @return the task with the certain index
     */
    public Task getTask(int index) {
        assert index >= 0 && index < getSize()
                : "The index must not be be less than 0 but less than the number of tasks";
        return tasks.get(index);
    }

    /**
     * Adds a new task
     *
     * @param task the new task
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the task with a certain index
     *
     * @param index the index of the removed task
     */
    public void removeTask(int index) {
        assert index >= 0 && index < getSize()
                : "The index must not be be less than 0 but less than the number of tasks";
        tasks.remove(index);
    }

    /**
     * Marks the task with a certain index
     *
     * @param index the index of the marked task
     */
    public void markTask(int index) {
        assert index >= 0 && index < getSize()
                : "The index must not be be less than 0 but less than the number of tasks";
        tasks.get(index).mark();
    }

    /**
     * Unmarks the task with a certain index
     *
     * @param index the index of the unmarked task
     */
    public void unmarkTask(int index) {
        assert index >= 0 && index < getSize()
                : "The index must not be be less than 0 but less than the number of tasks";
        tasks.get(index).unmark();
    }

    /**
     * Gets the number of tasks
     *
     * @return the number of tasks
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Gets the string representation to store the TaskList
     *
     * @return the string representation to store the TaskList
     */
    public String storeInString() {
        String ret = "";
        for (Task task: tasks) {
            ret += task.storeInString() + "\n";
        }
        return ret;
    }

    /**
     * Finds list of task containing the keyword
     *
     * @param keyWord the keyword
     * @return the list of task containing the keyword
     */
    public TaskList find(String keyWord) {
        ArrayList<Task> results = new ArrayList<>();

        for (Task task: tasks) {
            if (task.hasKeyword(keyWord)) {
                results.add(task);
            }
        }

        return new TaskList(results);
    }

    /**
     * Checks whether the TaskList is empty
     *
     * @return whether the TaskList is empty
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    @Override
    public String toString() {
        String ret = "";
        for (int i = 0; i < tasks.size(); i++) {
            ret += "    " + (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return ret;
    }
}
