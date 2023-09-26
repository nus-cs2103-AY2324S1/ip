package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The type Task.
 */
public class Task {
    protected String description;
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
                       : "[ ] " + description);
    }

    /**
     * Mark as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Print date time format string.
     *
     * @param dateTime the date time
     * @return the string
     */
    public String printDateTimeFormat(LocalDateTime dateTime) {
        DateTimeFormatter result = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        return dateTime.format(result);
    }
    /**
     * Parses from input string to create tasks.
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
            Task task = new Deadline(description, parts[3]);
            assert task != null : "Creating a Deadline instance failed.";
            if (isDone == 1) {
                task.markAsDone();
            }
            return task;
        } else if (type.equals("E")) {
            Task task = new Event(description, parts[3], parts[4]);
            assert task != null : "Creating an Event instance failed.";
            if (isDone == 1) {
                task.markAsDone();
            }
            return task;
        } else {
            throw new IllegalArgumentException("Invalid task type: " + type);
        }
    }
}
