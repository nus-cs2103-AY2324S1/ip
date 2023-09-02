package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Representation of a list of tasks.
 */

public class TaskList {

    private List<Task> taskList = new ArrayList<>();

    /**
     * Obtains the number of tasks in the list.
     *
     * @return an integer
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Adds the task into the list.
     *
     * @param task
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Removes the task from the list.
     *
     * @param index
     */
    public void remove(int index) {
        this.taskList.remove(index);
    }

    /**
     * A getter to return the task in the list.
     *
     * @param index an integer value
     * @return a Task from the list
     */
    public Task getTask(int index) {
        if (index <= this.taskList.size() && index >= 1) {
            return this.taskList.get(index - 1);
        } else {
            return null;
        }
    }
}

