package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline, which is a Task with a due date
 */
public class Deadline extends Task {
    protected LocalDate dueBy;

    /**
     * Constructs a new Deadline
     * @param name Name of the deadline
     * @param dueBy Date the deadline is due
     * @param isDone Status of whether the deadline is done
     */
    public Deadline(String name, LocalDate dueBy, String isDone) {
        super(name, isDone);
        this.dueBy = dueBy;
    }

    /**
     * Returns the string representation of the deadline to be saved
     * @return String containing the status of event, name of event and the due date
     */
    @Override
    public String toDataString() {
        return super.toDataString() + " | " + dueBy.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * Returns the string representation of the deadline to be printed
     * @return String containing the status of event, name of event and the due date
     */
    @Override
    public String toString() {
        String str = "[D] " + super.getStatus() + " " + super.name + " (by: " +
                dueBy.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        return str;
    }
}
