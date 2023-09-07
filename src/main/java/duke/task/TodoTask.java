package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The TodoTask class represents a task with a to-do type in the Duke application.
 * It extends the base Task class and includes additional fields for date range information.
 */
public class TodoTask extends Task {
    private LocalDate fromDate;
    private LocalDate toDate;

    /**
     * Constructs a TodoTask with the specified description, date range, and completion status.
     *
     * @param description The description of the to-do task.
     * @param fromDate    The starting date of the date range.
     * @param toDate      The ending date of the date range.
     * @param isDone      The completion status of the task (true if done, false otherwise).
     */
    public TodoTask(String description, LocalDate fromDate, LocalDate toDate, boolean isDone) {
        super(description, isDone);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Returns a string representation of the TodoTask, including task type, completion status, and description details.
     *
     * @return A string representation of the TodoTask.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() + " " + this.getDescriptionDetails();
    }

    /**
     * Converts the TodoTask to a string format suitable for saving to a file, including task type, completion status,
     * description, and date range.
     *
     * @return A string formatted for file storage.
     */
    @Override
    public String toFileString() {
        return String.format("%s | %d | %s (from: %s to: %s)", getTaskType(), this.isDone ? 1 : 0, this.description,
                this.fromDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                this.toDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    /**
     * Returns the task type identifier for a to-do task (i.e., "T").
     *
     * @return The task type identifier.
     */
    @Override
    public String getTaskType() {
        return "T";
    }

    /**
     * Returns the description details of the to-do task.
     *
     * @return The to-do task name together with the from and to dates.
     */
    private String getDescriptionDetails() {
        return this.description + " (from: " + this.fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +
                " to: " + this.toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}