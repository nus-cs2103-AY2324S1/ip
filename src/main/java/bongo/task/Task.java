package bongo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * A constructor for a Task.
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns status icon of task.
     * @return Status icon of task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Converts datetime into formatted string.
     * @param datetime
     * @return Returns formatted datetime string.
     */
    public String generateDateString(LocalDateTime datetime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy hh:mm a");
        return formatter.format(datetime);
    }

    /**
     * Returns string to save in text file.
     * @return String to save in text file.
     */
    public abstract String generateStringForTextFile();

    /**
     * Returns string representation of task.
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
