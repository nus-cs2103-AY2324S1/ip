package spot.task;

import java.time.LocalDate;

import spot.Ui;

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
    public void markAsDone(Ui ui) {
        this.isDone = true;
        ui.setMessage("Wow! Spot has marked this task as done!");
        ui.setMessage("  " + this);
    }

    /**
     * Marks the Task object as not done.
     */
    public void markAsNotDone(Ui ui) {
        this.isDone = false;
        ui.setMessage("Spot will mark this task as not done yet then!");
        ui.setMessage("  " + this);
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
}
