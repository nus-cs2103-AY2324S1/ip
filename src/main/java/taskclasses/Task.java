package taskclasses;

import extensions.Tag;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a basic task with a description and completion status.
 */
public class Task {
    private String description;
    private boolean isDone;
    private Tag tag;
    /**
     * Constructs a Task with the given description and sets the completion status to false.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        assert description != null : "Task description should not be null";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task with the given description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     */
    public Task(String description, boolean isDone, Tag tag) {
        assert description != null : "Task description should not be null";
        this.description = description;
        this.isDone = isDone;
        this.tag = tag;
    }

    /**
     * Creates a task object from a data line in storage.
     *
     * @param dataLine The data line representing the task.
     * @return A Task object created from the data line.
     */
    public static Task createTaskFromData(String dataLine) {
        String[] parts = dataLine.split(" \\| ");
        assert parts.length >= 3 : "Invalid data line format";

        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");

        int dateIndex = 3;
        Tag tag = null;

        if(parts.length > 3 && parts[3].contains("#")) {
            dateIndex = 4;
            String tagData = parts[3].split("#")[1].trim();
            tag = new Tag(tagData);
        }

        if (type.equals("T")) {
            return new Todo(description, isDone, tag);
        } else if (type.equals("D")) {
            LocalDate by = LocalDate.parse(parts[dateIndex].trim(), inputFormatter);
            return new Deadline(description, isDone, by, tag);
        } else if (type.equals("E")) {

            String[] dates = parts[dateIndex].split(" - ");

            String fromDateString = dates[0];
            String toDateString = dates[1];

            LocalDate from = LocalDate.parse(fromDateString, inputFormatter);
            LocalDate to = LocalDate.parse(toDateString, inputFormatter);
            return new Event(description, isDone, from, to, tag);
        } else {
            return null; // Unknown task type
        }
    }

    public void markAsDone() {
        assert !isDone : "This task is already marked as done";
        this.isDone = true;
    }

    public void markAsUndone() {
        assert isDone : "This task is not marked as done";
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Formats the task for storage.
     *
     * @return A formatted string representing the task for storage.
     */
    public String formatToFile() {
        String status = isDone ? "1" : "0";
        if(this.tag != null) {
            return status + " | " + description + " | " + this.tag;
        }
        return status + " | " + description;
    }
    public void tagTask(Tag tag) {
        if(this.tag == null) {
            this.tag = tag;
        }
    }

    public void untagTask() {
        if(this.tag != null) {
            this.tag = null;
        }
    }
    @Override
    public String toString() {
        if (this.tag != null) {
            return String.format("[%s] %s %s", this.getStatusIcon(), this.description, this.tag.toString());
        } else {
            return String.format("[%s] %s", this.getStatusIcon(), this.description);
        }
    }
}
