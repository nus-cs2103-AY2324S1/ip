package task;

/**
 * Represents a task that can be marked as completed or not completed.
 */
public interface Completable {

    /**
     * Checks if the task is completed.
     *
     * @return true if the task is completed, false otherwise.
     */
    boolean isCompleted();

    /**
     * Marks the task as completed.
     */
    void setCompleted();

    /**
     * Marks the task as not completed.
     */
    void setNotCompleted();
}
