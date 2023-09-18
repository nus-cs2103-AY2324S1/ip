package teho.main;

import teho.main.Task;

import java.util.ArrayList;
import java.util.List;


/**
 * Represents list of tasks.
 */
public class TaskList {
    /** List of tasks. */
    private ArrayList<Task> taskList;

    /**
     * Constructs a TaskList instance with the given list of tasks.
     *
     * @param taskList List of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
   }

    /**
     * Returns number of tasks in list.
     *
     * @return Number of tasks in list.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Returns the task with the corresponding taskNumber.
     *
     * @param taskNumber Index number of the task to be returned.
     * @return Task with the corresponding index number.
     */
    public Task getTask(int taskNumber) {
        return this.taskList.get(taskNumber);
    }

    /**
     * Adds task into list.
     *
     * @param task Task to be added into list.
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Removes task from the list.
     *
     * @param taskNumber Index number of the corresponding task to be removed from list.
     */
    public void remove(int taskNumber) {
        assert taskNumber >= 0 : "taskNumber should not be negative";
        this.taskList.remove(taskNumber);
    }

    public static List<Task> duplicateList(TaskList taskList) {
        List<Task> duplicatedList = new ArrayList<>(taskList.taskList);
        return duplicatedList;
    }
}
