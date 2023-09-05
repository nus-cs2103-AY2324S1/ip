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
            String formattedDateTime = dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
            return formattedDescription + " (at: " + formattedDateTime + ")";
        } else {
            return formattedDescription;
        }
    }
}
