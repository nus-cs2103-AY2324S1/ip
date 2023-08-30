package task;

import java.util.ArrayList;

public class TaskList {
    // Attribute
    private ArrayList<Task> tasks;

    // Constructor
    
    /**
     * The constructor for TaskList
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    // Method

    /**
     * Gets the task with a certain index
     * 
     * @param index the index of the task
     * @return the task with the certain index
     */
    public Task getTask(int index) {
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
        tasks.remove(index);
    }

    /**
     * Marks the task with a certain index
     * 
     * @param index the index of the marked task
     */
    public void markTask(int index) {
        tasks.get(index).mark();
    }

    /**
     * Unmarks the task with a certain index
     * 
     * @param index the index of the unmarked task
     */
    public void unmarkTask(int index) {
        tasks.get(index).unmark();
    }

    /**
     * Gets the number of tasks
     * 
     * @return the number of tasks
     */
    public int size() {
        return tasks.size();
    }
    
    /**
     * Gets the string representation to store the TaskList
     * 
     * @return the string representation to store the TaskList
     */
    public String storeInString() {
        String ret = "";
        for(Task task: tasks) {
            ret += task.storeInString() + "\n";
        }
        return ret;
    }
    /**
     * Gets the string representation of the TaskList
     * 
     * @return the string representation of the TaskList
     */
    public String toString() {
        String ret = "";
        for(int i = 0; i < tasks.size(); i++) {
            ret += "    " + (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return ret;
    }
}
