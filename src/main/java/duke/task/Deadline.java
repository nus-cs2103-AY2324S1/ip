package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class.
 */
public class Deadline extends Task {
    private LocalDate deadline;
    /**
     * Constructor for the duke.task.Todo class.
     *
     * @param name The name of the todo task.
     * @param deadline The deadline of the task.
     * @param done Whether the task is marked done or not.
     */
    public Deadline(String name, String deadline, boolean done) {
        super(name, done);
        this.deadline = LocalDate.parse(deadline);
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[D][X] " + this.name + " By: " + this.deadline;
        } else {
            return "[D][ ] " + this.name + " By: " + this.deadline;
        }
    }

    /**
     * Displays the deadline in a user-friendly format.
     *
     * @return The string representation of the deadline for display to the user.
     */
    public String displayableForm() {
        if (this.done) {
            return "[D][X] " + this.name + " By: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            return "[D][ ] " + this.name + " By: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
    }
}
