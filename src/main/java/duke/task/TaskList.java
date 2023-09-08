package duke.task;

import java.util.ArrayList;

/**
 * Represents the list of tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Gets the task list.
     *
     * @return The task list.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Gets the task at the specified index.
     *
     * @param index Integer specified the position of task in the task list.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Prints the task list in sequential order, starts from 1.
     */
    public void printTaskList() {
        int index = 0;
        for (Task task: this.taskList) {
            System.out.println("    " + (++index) + "." + task.toString());
        }
    }

    /**
     * Returns the size of the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Adds the task to the task list.
     *
     * @param task The task that is added to the end of the task list.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index Integer specified the position of task in the task list.
     * @return The task that is deleted from the task list.
     */
    public Task deleteTask(int index) {
        return taskList.remove(index);
    }

    /**
     * Returns the list of tasks that contain the string given.
     *
     * @param str String that may be in the task description.
     * @return the list of tasks that contain the string given.
     */
    public ArrayList<Task> contains(String str) {
        ArrayList<Task> resultTask = new ArrayList<>();
        for (Task task: taskList) {
            if (task.contains(str)) {
                resultTask.add(task);
            }
        }
        return resultTask;
    }
}
