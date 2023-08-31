package duke.task;

import duke.parse.DateTimeManager;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a deadline.
 * A deadline contains an end time.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Instantiates a deadline with the given content and the end time
     * @param name the content of the task
     * @param deadline the end time of the deadline
     */
    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Data representation of the task, to be stored in the disk
     * @return data representation of the task to be stored in the disk
     */
    @Override
    public String data() {
        return "D " + super.data() + " /by " + DateTimeManager.dateToStringData(this.deadline);
    }

    /**
     * Checks whether this deadline ends before or on the given date.
     * @param date the datetime to check against
     * @return whether this deadline ends before or on the given date
     */
    @Override
    public boolean containsDate(LocalDate date) {
        return this.deadline.toLocalDate().isBefore(date) || this.deadline.toLocalDate().equals(date);
    }

    /**
     * String representation of the task, to be displayed in UI.
     * @return the string representation of the task to be displayed in UI
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + DateTimeManager.dateToDisplay(this.deadline) + ")";
    }

    /**
     * Checks whether this deadline is the same as another task, for testing purposes.
     * It is the same if content is the same, and end time is the same.
     * @param another the object to compare with
     * @return whether this deadline is the same as another task
     */
    @Override
    public boolean equals(Object another) {
        if (another instanceof Deadline) {
            Deadline anotherDeadline = (Deadline) another;
            return super.equals(anotherDeadline)
                    && this.deadline.equals(anotherDeadline.deadline);
        }
        return false;
    }
}
