package bobi.task;

import java.time.LocalDateTime;

/**
 * Task is an abstract base class for all types of tasks
 * that users wish to add into their Bobi task list.
 * A Task Object encapsulates the task name and status for users to track.
 *
 * @author ruo-x
 */
public abstract class Task {
    /** Name of task */
    private final String taskName;

    /** Status of task */
    private boolean isDone;

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
     * Returns status of task.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Returns name of task.
     */
    public String getName() {
        return this.taskName;
    }

    /**
     * Marks a task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks a task as un-done.
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
     * Updates the task string in the backend
     * when there is a change in status.
     *
     * @return Updated task string to be stored in backend.
     */
    public abstract String toUpdateString(int newStatus);

    /**
     * Returns the due date of a task, if applicable.
     * To-Do tasks returns null.
     * Deadline tasks return the deadline.
     * Event tasks return the start date.
     */
    public abstract LocalDateTime getTaskDue();
}
