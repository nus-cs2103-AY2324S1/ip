package taskclasses;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a basic task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the given description and sets the completion status to false.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task with the given description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Creates a task object from a data line in storage.
     *
     * @param dataLine The data line representing the task.
     * @return A Task object created from the data line.
     */
    public static Task createTaskFromData(String dataLine) {
        String[] parts = dataLine.split(" \\| ");
        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        if (type.equals("T")) {
            return new Todo(description);
        } else if (type.equals("D")) {
            LocalDate by = LocalDate.parse(parts[3].trim(), inputFormatter);
            return new Deadline(description, isDone, by);
        } else if (type.equals("E")) {

            String[] dates = parts[3].split(" - ");

            String fromDateString = dates[0];
            String toDateString = dates[1];

            LocalDate from = LocalDate.parse(fromDateString, inputFormatter);
            LocalDate to = LocalDate.parse(toDateString, inputFormatter);
            return new Event(description, isDone, from, to);
        } else {
            return null; // Unknown task type
        }
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
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
        return status + " | " + description;
    }
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
