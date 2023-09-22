package task_creator;

import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;

/**
 * This is the abstract superclass of all the task_creator classes.
 */
public abstract class CreateTask {
    /**
     * This method is the method that the subclasses must override.
     * @param content
     * @param taskList
     * @return A new Task object.
     */
    public Task create(String content, ArrayList<Task> taskList) {
        return null;
    }
}
