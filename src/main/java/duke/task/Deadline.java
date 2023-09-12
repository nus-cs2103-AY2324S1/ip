package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import duke.parse.DateTimeManager;

/**
 * Represents a deadline.
 * A deadline contains an end time.
 */
public class Deadline extends Task {
    private final LocalDateTime deadlineTime;

    /**
     * Instantiates a deadline with the given content and the end time
     * @param name The content of the task.
     * @param deadline The end time of the deadline.
     */
    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.deadlineTime = deadline;
    }

    /**
     * Data representation of the task, to be stored in the disk
     * @return Data representation of the task to be stored in the disk.
     */
    @Override
    public String getData() {
        return "D " + super.getData() + " /by " + DateTimeManager.dateToStringData(this.deadlineTime);
    }

    /**
     * Checks whether this deadline ends before or on the given date.
     * @param date The datetime to check against.
     * @return Whether this deadline ends before or on the given date.
     */
    @Override
    public boolean containsDate(LocalDate date) {
        return this.deadlineTime.toLocalDate().isBefore(date) || this.deadlineTime.toLocalDate().equals(date);
    }

    /**
     * String representation of the task, to be displayed in UI.
     * @return The string representation of the task to be displayed in UI.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + DateTimeManager.dateToDisplay(this.deadlineTime) + ")";
    }

    /**
     * Checks whether this deadline is the same as another task, for testing purposes.
     * It is the same if content is the same, and end time is the same.
     * @param another The object to compare with.
     * @return Whether this deadline is the same as another task.
     */
    @Override
    public boolean equals(Object another) {
        if (another instanceof Deadline) {
            Deadline anotherDeadline = (Deadline) another;
            return super.equals(anotherDeadline)
                    && this.deadlineTime.equals(anotherDeadline.deadlineTime);
        }
        return false;
    }
}
