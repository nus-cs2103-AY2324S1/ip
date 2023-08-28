package seedu.james;

import java.util.ArrayList;

/**
 * TaskList class to handle the list of tasks
 */
public class TaskList {

    /** The list of tasks */
    private ArrayList<Task> items = new ArrayList<Task>();

    /** The parser to parse the user input */
    public Parser parser = new Parser();

    /**
     * Constructor for TaskList from existing ArrayList<Task>
     *
     * @param items
     */
    public TaskList(ArrayList<Task> items) {
        this.items = items;
    }

    /**
     * Constructor for TaskList from scratch
     */
    public TaskList() {
        this.items = new ArrayList<Task>();
    }

    /**
     * Unmarks the task at the specified index
     *
     * @param idx
     */
    public void unmarkTask(Integer idx) {
        Task task = this.items.get(idx);
        task.unmark();
    }

    /**
     * Marks the task at the specified index
     *
     * @param idx
     */
    public void markTask(Integer idx) {
        Task task = this.items.get(idx);
        task.mark();
    }

    /**
     * Deletes the task at the specified index
     *
     * @param idx
     */
    public void deleteTask(Integer idx) {
        Task task = this.items.get(idx);
        this.items.remove(task);
    }

    /**
     * Adds a task to the list
     *
     * @param task
     */
    public void addTask(Task task) {
        this.items.add(task);
    }

    /**
     * Find the number of tasks in the list
     *
     * @return the number of tasks in the list
     */
    public int size() {
        return this.items.size();
    }

    /**
     * Gets the list of tasks
     *
     * @return all the tasks
     */
    public ArrayList<Task> getTasks() {
        return this.items;
    }

    /**
     * Gets the task at the specified index
     *
     * @param index
     * @return the task at the specified index
     */
    public Task getTask(int index) {
        return this.items.get(index);
    }

    /**
     * Returns the string representation of the task list
     *
     * @return the string representation of the task list
     */
    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < this.items.size(); i++) {
            Task task = this.items.get(i);
            output += (i + 1) + "." + task.toString() + "\n";
        }
        // remove the last \n
        if (output.length() > 0) {
            output = output.substring(0, output.length() - 1);
        }
        return output;
    }
}
