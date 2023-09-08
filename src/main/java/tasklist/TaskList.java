package tasklist;

import java.util.ArrayList;
import task.Task;

/**
 * The TaskList class encapsulates a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs a TaskList object with a given list of tasks.
     *
     * @param taskList An ArrayList of Task objects.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Constructs a TaskList object with an empty list of tasks.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a Task object to the taskList.
     *
     * @param task The Task object being added to the taskList.
     */
    public void add(Task task){
        this.taskList.add(task);
    }

    /**
     * Deletes a Task object from the taskList using its number.
     *
     * @param taskNumber The number of the task to be deleted.
     */
    public void delete(int taskNumber) {
        // change number to index
        taskList.remove(taskNumber - 1);
    }

    /**
     * Retrieves the number of tasks in the tasklist.
     *
     * @return The number of tasks in the tasklist.
     */
    public int size() {
        return taskList.size();
    }


    /**
     * Retrieves a task from the tasklist using its number.
     *
     * @param taskNumber The number of the task to be retrieved.
     * @return The Task object at the specified position in the list.
     */
    public Task get(int taskNumber) {
        return taskList.get(taskNumber - 1);
    }

}
