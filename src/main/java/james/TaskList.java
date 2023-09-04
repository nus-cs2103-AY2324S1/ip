package james;

import java.util.ArrayList;

/**
 * TaskList class to handle the list of tasks
 */
public class TaskList {

    /** The parser to parse the user input */
    private Parser parser = new Parser();

    /** The list of tasks */
    private ArrayList<Task> items = new ArrayList<Task>();

    /**
     * Constructor for TaskList from existing list of tasks
     *
     * @param items the list of tasks
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
     * @param idx the index of the task to be unmarked
     */
    public void unmarkTask(Integer idx) {
        Task task = this.items.get(idx);
        task.unmark();
    }

    /**
     * Marks the task at the specified index
     *
     * @param idx the index of the task to be marked
     */
    public void markTask(Integer idx) {
        Task task = this.items.get(idx);
        task.mark();
    }

    /**
     * Deletes the task at the specified index
     *
     * @param idx the index of the task to be deleted
     */
    public void deleteTask(Integer idx) {
        Task task = this.items.get(idx);
        this.items.remove(task);
    }

    /**
     * Adds a task to the list
     *
     * @param task the task to be added
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
     * @param index the index of the task to be retrieved
     * @return the task at the specified index
     */
    public Task getTask(int index) {
        return this.items.get(index);
    }

    /**
     * Finds the tasks that contain the query string
     *
     * @param query the query string
     * @return the string representation of the tasks that contain the query string
     */
    public String find(String query) {
        String output = "";
        int count = 0;
        for (int i = 0; i < this.items.size(); i++) {
            Task task = this.items.get(i);
            if (task.toString().contains(query)) {
                output += (count + 1) + "." + task.toString() + "\n";
                count++;
            }
        }
        // remove the last \n
        if (output.length() > 0) {
            output = output.substring(0, output.length() - 1);
        }
        return output;
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
