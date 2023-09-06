package functional;

import java.util.ArrayList;
import java.util.List;

/**
 * An extension of Arraylist
 */
public class TaskList<Task> extends ArrayList<Task> {

    /**
     * Constructs a `TaskList` with the specified list of tasks
     * @param list The List containing tasks to initialize the task list.
     */
    public TaskList(List<Task> list) {
        super(list);
    }

    public TaskList() {
        super();
    }

    /**
     * get method to obtain the Task at a specific index
     * @param index index of the element to return
     * @return the Tasks retrieved from the given index
     */
    public Task get(int index) {
        return super.get(index);
    }
}
