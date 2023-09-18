package helpbuddy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import helpbuddy.exception.HelpBuddyException;

/**
 * The Deadline class creates a Task storing a deadline of type LocalDateTime.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructs a new Deadline object with specified String description and LocalDateTime by as deadline.
     * @param description the description of Task.
     * @param by the deadline of task to be completed by.
     * @throws HelpBuddyException if description or by has no value.
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
     * Returns String representing the Deadline object.
     * @return a string representation of description, isDone and by.
     */
    @Override
    public String toString() {
        String deadline = this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }

    /**
     * Returns String representing the Deadline object to be saved in a file.
     * @return a string representation of description, isDone and by.
     */
    @Override
    public String stringifyTask() {
        return String.format("D|%d|%s|%s", this.isDone ? 1 : 0, this.description, this.by);
    }

    /**
     * Compares the object to the specified object. The result is true if and only if argument is not null and
     * its stringifyTask() is the same as the object's.
     * @param obj the object to compare with.
     * @return true if objects are the same; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof Deadline) {
            Deadline task = (Deadline) obj;
            return this.stringifyTask().equals(task.stringifyTask());
        }

        return false;
    }

}
