package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task in the task list.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDateTime dateTime;

    /**
     * Constructs a task with a description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the task information formatted for storage txt file.
     *
     * @return A string representation of the task for storage txt file.
     */
    public abstract String toFileString();

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the string indicating the task's status (done or not done).
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Sets the task's description to the new description.
     *
     * @param newDescription The new description of the task.
     */
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Sets the task's date-time to the new date-time.
     *
     * @param newDateTime The new date-time of the task.
     */
    public void setDateTime(LocalDateTime newDateTime) {
        this.dateTime = newDateTime;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return True if task is marked as done; false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     * */
    public void markAsNotDone() {
        isDone = false;
    }
    public abstract boolean isOnDate(LocalDate date);
    /**
     * Returns the string representation of the task.
     *
     * @return A formatted string describing the task.
     */
    @Override
    public String toString() {
        String formattedDescription = getStatusIcon() + " " + this.description;
        if (dateTime != null) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mma"); // Print in 12-hour time format
            String timeString = this.dateTime.format(timeFormatter).toLowerCase();
            return formattedDescription + " (at: " + this.dateTime.format(dateFormatter) + " " + timeString + ")";
        } else {
            return formattedDescription;
        }
    }
}
