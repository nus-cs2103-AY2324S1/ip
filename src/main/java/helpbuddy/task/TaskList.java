package helpbuddy.task;

import java.util.ArrayList;

/**
 * A TaskList class that stores Task objects.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs an ArrayList object with the same elements in taskList.
     * @param taskList the taskList with Task objects.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the Task object of the specified int i in the ArrayList.
     * @param i the index of Task.
     * @return the Task object at index i in ArrayList.
     */
    public Task getTask(int i) {
        return this.taskList.get(i);
    }

    /**
     * Adds the Task in the ArrayList.
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes the Task in the ArrayList.
     * @param i the index of task to be deleted in ArrayList.
     */
    public void deleteTask(int i) {
        this.taskList.remove(i);
    }

    /**
     * Checks if the ArrayList size() is 0.
     * @return true if size() is 0; otherwise false.
     */
    public boolean isEmpty() {
        return this.taskList.size() == 0;
    }

    /**
     * Returns the size of the ArrayList.
     * @return an int of the ArrayList's size.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Finds Task objects that has String s in description of Task.
     * @param s String that represents the description of Task to find in taskList.
     * @return an ArrayList with Task objects description matching String s.
     */
    public ArrayList<Task> findCommonTask(String s) {
        ArrayList<Task> commonTaskList = new ArrayList<>();
        for (Task task : taskList) {
            if (task.hasDescription(s)) {
                commonTaskList.add(task);
            }
        }
        return commonTaskList;
    }
}
