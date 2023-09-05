package chadbod;

import java.time.LocalDateTime;

/**
 * Abstract class representing a task in the ChadBod application.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task class based on the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get the status icon for the task (done or not done).
     *
     * @return The status icon as a string.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Mark the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Mark the task as not done.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Convert the task to a string representation.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Create a Task object from a string representation.
     *
     * @param line The string representation of the task.
     * @return The Task object created from the string, or null if parsing fails.
     */
    public static Task fromString(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length >= 3 && parts[0].equals("T")) {
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            Task t = new Todo(description);
            if (isDone) {
                t.markDone();
            }
            return t;
        } else if (parts.length >= 4 && parts[0].equals("D")) {
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            LocalDateTime deadline = LocalDateTime.parse(parts[3]);
            Task t = new Deadline(description, deadline);
            if (isDone) {
                t.markDone();
            }
            return t;
        } else if (parts.length >= 5 && parts[0].equals("E")) {
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            LocalDateTime from = LocalDateTime.parse(parts[3]);
            LocalDateTime to = LocalDateTime.parse(parts[4]);
            Task t = new Event(description, from, to);
            if (isDone) {
                t.markDone();
            }
            return t;
        } else {
            return null;
        }
    }

    /**
     * Check if the task description contains a specific keyword.
     *
     * @param keyword The keyword to search for in the task description.
     * @return True if the keyword is found, false otherwise.
     */
    public boolean containsKeyword(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Convert the task to a string representation for saving to a file.
     *
     * @return The string representation of the task for file storage.
     */
    public abstract String toFileString();
}
