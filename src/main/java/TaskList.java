import java.util.ArrayList;

/**
 * Manages the operation on the tasks
 */
public class TaskList {
    /**
     * Stores all the task
     */
    private final ArrayList<Task> TASKS = new ArrayList<>();


    /**
     * construct a new task list
     */
    public TaskList() {

    }

    /**
     * returns the current task list length
     *
     * @return task list length
     */
    public int length() {
        return this.TASKS.size();
    }

    /**
     * Adds the given task into memory
     *
     * @param task - the task being added
     */
    public void add(Task task) {
        this.TASKS.add(task);
    }

    /**
     * remove the given task from memory
     *
     * @param index - the index of the task being removed
     * @return the task that was removed
     */
    public Task remove(int index) {
        return this.TASKS.remove(index);
    }

    /**
     * returns the element at the index
     *
     * @param index - index of the element
     * @return the element at the index
     */
    public Task get(int index) {
        return this.TASKS.get(index);
    }


    public Task[] getAllTask() {
        return this.TASKS.toArray(new Task[this.length()]);
    }
}
