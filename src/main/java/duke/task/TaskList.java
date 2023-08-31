package duke.task;

import java.util.ArrayList;

/**
 * The TaskList class contains the task list and all the operations
 * performed on the tasks in the list.
 */
public class TaskList {
    ArrayList<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds the task to the current task list.
     *
     * @param task Task to be added into task list.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the task from the current task list.
     *
     * @param taskNumber Task Number corresponding to the task list to be removed.
     */
    public void remove(int taskNumber) {
        tasks.remove(taskNumber - 1);
    }

    /**
     * Returns the number of tasks in current task list.
     *
     * @return number of tasks.
     */
    public int getNumberOfTasks() {
        return tasks.size();
    }

    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber - 1);
    }

    public ArrayList<Task> getList() {
        return this.tasks;
    }
}
