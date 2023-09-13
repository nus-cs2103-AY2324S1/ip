package spot.task;

import spot.exception.SpotException;

import java.time.LocalDate;

/**
 * Represents a task.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a new Task object.
     *
     * @param description Description of the Task object.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new Task object.
     *
     * @param description Description of the Task object.
     * @param isDone Boolean representing the state of completion of the Task object.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the Task object as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the Task object as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns String representation of the state of completion of the Task object.
     *
     * @return String representation of the state of completion of the Task object.
     */
    public String getStatusIcon() {
        if (this.isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    /**
     * Returns String representation of the Task object.
     *
     * @return String representation of the Task object.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns representation of the Task object to be stored
     * in a text file within the hard disk.
     *
     * @return Representation of the Task object to be stored.
     */
    public String toLine() {
        return this.getStatusIcon() + " | " + this.description;
    }

    /**
     * Returns whether the Task object falls on the specified date.
     *
     * @param date Specified date.
     * @return Boolean representing whether the Task object falls on the specified date.
     */
    public abstract boolean fallsOn(LocalDate date);

    public boolean descriptionContains(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Updates the task's description.
     *
     * @param description Updated description.
     */
    public void updateDescription(String description) {
        this.description = description;
    }

    /**
     * Updates the task's deadline.
     *
     * @param deadline Updated deadline.
     * @throws SpotException If the task has no deadline field.
     */
    public abstract void updateDeadline(LocalDate deadline) throws SpotException;

    /**
     * Updates the task's start date.
     *
     * @param start Updated start date.
     * @throws SpotException If the task has no start date field.
     */
    public abstract void updateStart(LocalDate start) throws SpotException;

    /**
     * Updates the task's end date.
     *
     * @param end Updated end date.
     * @throws SpotException If the task has no end date field.
     */
    public abstract void updateEnd(LocalDate end) throws SpotException;
}
