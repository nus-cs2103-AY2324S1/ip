package duke.tasks;

import java.time.LocalDateTime;

/**
 * Represents a task object.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the given task description.
     *
     * @param description Describes task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string to indicate whether task is done.
     *
     * @return status of whether task is done in "X" or " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns string description of task.
     *
     * @return String description of task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Updates description of task
     *
     * @param newDescription New description.
     */
    public void updateTaskDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Updates a date field of task.
     *
     * @param fieldToUpdate Field that is to be updated.
     * @param newDate New date.
     */
    public void updateDate(String fieldToUpdate, LocalDateTime newDate) {
    }

    /**
     * Represents task in string format.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Represents task in string format to be stored.
     *
     * @return String representation of task.
     */
    public String toStorageFormat() {
        return (" | " + (isDone ? "1" : "0") + " | " + this.description);
    }

}
