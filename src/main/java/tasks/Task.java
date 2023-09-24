package tasks;

import java.time.LocalDateTime;

/**
 * Represents a task with a description and a completion status.
 * This is the base class for the tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with a given description.
     *
     * @param description The description or title of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public abstract boolean isOnDate(LocalDateTime date);

    /**
     * Retrieves the status icon of the task, where "X" indicates the task is completed.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done/completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as !(done/completed).
     */
    public void unMark() {
        this.isDone = false;
    }

    /**
     * Returns a formatted string representing the task.
     * The format depends on the specific type of task.
     *
     * @return The formatted string representation of the task.
     */
    public String format() {
        if (this instanceof Todo) {
            return String.format("T | %s | %s", isDone ? "1" : "0", getDescription());
        } else if (this instanceof Deadline) {
            Deadline deadline = (Deadline) this;
            return String.format("D | %s | %s | %s", isDone ? "1" : "0", getDescription(), deadline.getBy());
        } else if (this instanceof Event) {
            Event event = (Event) this;
            return String.format("E | %s | %s | %s - %s", isDone ? "1" : "0",
                    getDescription(), event.getFrom(), event.getTo());
        }
        return "";
    }

    /**
     * Returns a string representation of the task, which includes the task status icon
     * and its description.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
