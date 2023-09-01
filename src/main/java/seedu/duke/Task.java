package seedu.duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task.
 * A task must have a description
 * and a state for recording whether
 * that task is completed or not.
 *
 * @author KAM JIA YUE
 * @since 2023-08-29
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * The main constructor of ths Task class.
     *
     * @param description Description of this task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon representing the state of
     * this task.
     *
     * @return 'X' if this task is completed and ' ' otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the status of this task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Sets the status of this task as undone.
     */
    public void setUndone() {
        this.isDone = false;
    }

    /**
     * Marks the status of this task as completed.
     */
    public void mark() {
        this.setDone();
        System.out.println(Ui.I5 + "Nice! I've marked this task as done:");
        System.out.println(Ui.I7 + this);
    }

    /**
     * Marks the status of this task as not completed.
     */
    public void unmark() {
        this.setUndone();
        System.out.println(Ui.I5 + "OK, I've marked this task as not done yet:");
        System.out.println(Ui.I7 + this);
    }

    /**
     * Returns a string representation of this
     * task when it is saved in the storage.
     *
     * @return a string representation of this task for saving.
     */
    public String toStringForSave() {
        int status = this.isDone ? 1 : 0;
        return status + " | " + this.description;
    }

    /**
     * Parses a time string into a LocalDateTime object.
     *
     * @param timeString Time string to be parsed.
     * @return A LocalDateTime object.
     */
    public LocalDateTime parseStringToTime(String timeString) {
        return LocalDateTime.parse(timeString, DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
    }

    /**
     * Converts a LocalDateTime object into a time string.
     *
     * @param time LocalDateTime object to be converted.
     * @return A time string.
     */
    public String convertTimeToString(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm"));
    }

    /**
     * Converts a LocalDateTime object into a time string
     * when it is saved in the storage.
     *
     * @param time LocalDateTime object to be converted.
     * @return A time string for saving.
     */
    public String convertTimeForSave(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
    }

    /**
     * {@inheritDoc}
     *
     * Returns a string representation of this
     * task when it is printed.
     *
     * @return a string representation of this task for printing.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
