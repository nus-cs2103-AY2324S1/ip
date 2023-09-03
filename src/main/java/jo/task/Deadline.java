package jo.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task in the `Jo` application.
 * It includes properties such as a description and a deadline date.
 */
public class Deadline extends Task {

    protected LocalDate deadline;

    /**
     * Constructs a new `Deadline` object with the specified description, completion status, and deadline date.
     * @param description The description of the deadline task.
     * @param isDone      `true` if the deadline task is marked as done, `false` if it is undone.
     * @param deadline    The deadline date of the task.
     */
    public Deadline(String description, boolean isDone, LocalDate deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Returns a formatted string representation of the deadline task.
     * @return A string in the format: "[D][Status] Description (by: DeadlineDate)".
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                this.getStatusIcon(),
                this.description,
                this.deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
        );
    }

    /**
     * Converts the deadline task to a string format suitable for storing in a file.
     * @return A string in the format: "D | Status | Description | DeadlineDate".
     */
    @Override
    public String toFile() {
        return String.format("D | %s | %s | %s",
                    this.isDone ? "1" : "0", this.description, this.deadline);
    }

    /**
     * Retrieves the deadline date of the deadline task.
     * @return The deadline date of the task.
     */
    public LocalDate getDeadline() {
        return this.deadline;
    }

}
