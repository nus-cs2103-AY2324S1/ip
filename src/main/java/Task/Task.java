package task;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * The type Task.
 */
public class Task {
    /**
     * The Description.
     */
    protected String description;
    /**
     * The Is done.
     */
    protected boolean isDone;

    /**
     * Instantiates a new Task.
     *
     * @param description the description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets status icon.
     *
     * @return the status icon
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " + description
                       : "[ ] " + description );
    }

    /**
     * Mark as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmark.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Parse from string task.
     *
     * @param input the input
     * @return the task
     */
    public static Task parseFromString(String input) {
        String[] parts = input.split(" \\| ");
        String type = parts[0];
        int isDone = Integer.parseInt(parts[1]);
        String description = parts[2];

        if (type.equals("T")) {
            Task task = new Todo(description);
            assert task != null : "Creating a Todo instance failed.";
            if (isDone == 1) {
                task.markAsDone();
            }
            return task;
        } else if (type.equals("D")) {
            LocalDateTime by = LocalDateTime.parse(parts[3]);
            Task task = new Deadline(description, by);
            assert task != null : "Creating a Deadline instance failed.";
            if (isDone == 1) {
                task.markAsDone();
            }
            return task;
        } else if (type.equals("E")) {
            LocalDateTime from = LocalDateTime.parse(parts[3]);
            LocalTime to = LocalTime.parse(parts[4]);
            Task task = new Event(description, from, to);
            assert task != null : "Creating an Event instance failed.";
            if (isDone == 1) {
                task.markAsDone();
            }
            return task;
        } else {
            throw new IllegalArgumentException("Invalid task type: " + type);
        }
    }

    @Override
    public String toString() {
        return String.format("Task | %d | %s", isDone ? 1 : 0, description);
    }

}
