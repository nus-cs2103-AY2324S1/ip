package duke.task;

/**
 * Task is an abstract base class for all types of tasks
 * that users wish to add into their Bobi task list.
 * A Task Object encapsulates the task name and status for users to track.
 *
 * @author ruo-x
 */
public abstract class Task {
    /** Name of task */
    String taskName;

    /** Status of task */
    boolean isDone;

    /**
     * Constructor of a Task object.
     *
     * @param status Status of the task.
     * @param taskName Name of the task.
     */
    public Task(Boolean status, String taskName) {
        this.taskName = taskName;
        this.isDone = status;
    }

    /**
     * Marks a task as done.
     * Status of the task object is changed to true.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks a task as un-done.
     * Status of the task object is changed to false.
     */
    public void unMark() {
        this.isDone = false;
    }

    /**
     * Converts the task into a String format
     * which will be stored in the backend.
     *
     * @return String to be stored in backend.
     */
    public abstract String toStoreString();

    /**
     * Updates the task into a String format
     * when there is a change in status.
     * This newly updated string will be stored in the backend.
     *
     * @return Updated string to be stored in backend.
     */
    public abstract String toUpdateString(int i);
}
