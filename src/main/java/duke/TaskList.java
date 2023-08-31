package duke;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeLoadingException;
import duke.task.Task;

/**
 * Manages the operation on the tasks
 */
public class TaskList {

    /**
     * Stores all the task
     */
    private ArrayList<Task> tasks;
    /**
     * construct a new empty task list
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Loads the list of task from the storage into the task list instance
     *
     * @param storedInput - the list of task obtained from storage
     * @return true if all task loaded successfully else false
     */
    public boolean loadTasks(List<String> storedInput) {
        // parse and store data while looking out for data corruption
        boolean isCorrupted = false;
        for (String s : storedInput) {
            try {
                Task newTask = Parser.fromStorage(s);
                this.tasks.add(newTask);
            } catch (DukeLoadingException | DateTimeParseException e) {
                isCorrupted = true;
            }
        }
        return isCorrupted;
    }

    /**
     * returns the current task list length
     *
     * @return task list length
     */
    public int length() {
        return this.tasks.size();
    }

    /**
     * Adds the given task into memory
     *
     * @param task - the task being added
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * remove the given task from memory
     *
     * @param index - the index of the task being removed
     * @return the task that was removed
     */
    public Task remove(int index) {
        return this.tasks.remove(index);
    }

    /**
     * returns the element at the index
     *
     * @param index - index of the element
     * @return the element at the index
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * returns all the tasks currently stored
     *
     * @return an array of Task
     */
    public Task[] getAllTask() {
        return this.tasks.toArray(new Task[this.length()]);
    }
}
