package echobot.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task with the given description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Converts the task to a string for saving purpose.
     *
     * @return String representation of the task.
     */
    public String toFileString() {
        String type = "";
        String dateTime = "";

        if (this instanceof Todo) {
            type = "T";
        } else if (this instanceof Deadline) {
            type = "D";
            dateTime = " | " + ((Deadline) this).getBy().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else if (this instanceof Event) {
            type = "E";
            dateTime = " | " + ((Event) this).getFrom().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + " | "
                    + ((Event) this).getTo().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        }

        return type + " | " + (isDone ? "1" : "0") + " | " + description + dateTime;
    }

    /**
     * Converts a file string into task object.
     *
     * @param fileString File string representation of the task.
     * @return Task object.
     */
    public static Task fromFileString(String fileString) {
        String[] parts = fileString.split("\\s*\\|\\s*"); // Adjusted delimiter
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;

        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            String by = parts[3];
            // Use parseDate method to convert to LocalDate
            task = new Deadline(description, parseDate(by));
            break;
        case "E":
            String from = parts[3];
            String to = parts[4];
            // Use parseDateTime method to convert to LocalDateTime
            task = new Event(description, parseDateTime(from), parseDateTime(to));
            break;
        default:
            throw new IllegalArgumentException("Invalid task type: " + type);
        }

        if (isDone) {
            task.mark();
        }

        return task;
    }

    /**
     * Parse date and time string into a LocalDateTime object.
     *
     * @param dateTime Date and time string.
     * @return LocalDateTime object.
     */
    private static LocalDateTime parseDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Parses date string into a LocalDate object.
     *
     * @param date Date string.
     * @return LocalDate object.
     */
    private static LocalDate parseDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Returns the status icon for the task.
     *
     * @return Status icon ("X" for complete, " " for incomplete).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Unmarks the task as not done.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Gets the task's description.
     *
     * @return Task's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the task is done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isTaskDone() {
        return isDone;
    }

    /**
     * Displays the task information.
     *
     * @return The task details.
     */
    public String display() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
