package duke;

import java.util.ArrayList;

public class Tasklist {
    private ArrayList<Task> tasks;

    /**
     * Constructor for Tasklist
     *
     * @param tasks Arraylist of tasks to put in the Tasklist.
     */
    public Tasklist(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns an Arraylist representing the Tasks in the Tasklist.
     *
     * @return Arraylist of Tasks in the Tasklist.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the size of the Tasklist.
     *
     * @return Size of the Tasklist.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Gets the task at a particular index in the Tasklist.
     *
     * @param i The index of the Task in the Tasklist.
     * @return The Task at that index in the Tasklist.
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }

    /**
     * Gets the String representation of the Task at a particular index in the Tasklist.
     * @param i The index of the Task in the Tasklist.
     * @return The String representation of the Task at that index in the Tasklist.
     */
    public String getItemString(int i) {
        return tasks.get(i).toString();
    }

    /**
     * Marks an item in the Tasklist as done.
     *
     * @param i Index of the item to be marked.
     */
    public void markDone(int i) {
        tasks.get(i).markDone();
    }

    /**
     * Marks an item in the Tasklist as undone.
     *
     * @param i Index of the item to be marked.
     */
    public void markUndone(int i) {
        tasks.get(i).markUndone();
    }

    /**
     * Delete an item in the Tasklist.
     *
     * @param i Index of the item to be deleted.
     */
    public void deleteItem(int i) {
        tasks.remove(i);
    }

    /**
     * Adds a Task to the Tasklist.
     *
     * @param task Task to be added to the Tasklist.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Filters tasks by name.
     *
     * @param s Substring which must be present in the Task name.
     * @return An Arraylist containing tasks whose names match the substring provided.
     */
    public ArrayList<Task> filterBySubstring(String s) {
        ArrayList<Task> filteredTasks = new ArrayList<>();

        for (Task task : tasks) {
            String taskName = task.getName().toLowerCase();
            s = s.toLowerCase();

            if (taskName.contains(s)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }
}
