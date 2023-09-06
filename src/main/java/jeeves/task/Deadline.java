package jeeves.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd LLL yyyy");

    protected LocalDate by;

    public Deadline(String desc, LocalDate by) {
        super(desc);
        this.by = by;
    }

    public Deadline(String desc, LocalDate by, boolean status) {
        super(desc, status);
        this.by = by;
    }

    /**
     * Getter method for the Task deadline
     *
     * @return Deadline of the task
     */
    public LocalDate getDeadline() {
        return by;
    }

    @Override
    public String toString() {
        return String.format(this.id + ". [D]" + super.toString() + " (by: " + by.format(dtf) + ")");
    }
}
