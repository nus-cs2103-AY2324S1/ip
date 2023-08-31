package helpbuddy.task;

import helpbuddy.exception.HelpBuddyException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class is a subclass of Task which defines tasks with deadline.
 */
public class Deadline extends Task {
    /** A String that indicates deadline of task. */
    private LocalDateTime by;

    /**
     * A constructor for tasks with deadline
     * @param description Name of task
     * @param by Deadline of task to be completed by.
     * @throws HelpBuddyException If description or deadline is empty.
     */
    public Deadline(String description, LocalDateTime by) throws HelpBuddyException {
        super(description);
        this.by = by;
        if (description.isBlank()) {
            throw new HelpBuddyException("The description of a deadline cannot be empty.\n");
        } else if (by == null) {
            throw new HelpBuddyException("Please enter /by followed by a deadline.\n");
        }
    }

    /**
     *
     * @return String representation of task with deadline
     */
    @Override
    public String toString() {
        String deadline = this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }

    @Override
    public String stringifyTask() {
        return String.format("D|%d|%s|%s", this.isDone ? 1 : 0, this.description, this.by);
    }

}
