package jerma.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructor for a Deadline Task
     * 
     * @param description The description of the task
     * @param by          Date when the deadline should be complete by
     * @throws DateTimeParseException Thrown if date is not parseable
     */
    public Deadline(String description, String by) {
        super(description);
        this.symbol = "D";
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns string representation of Deadline Task for saving purposes
     * 
     * @return String representation of Deadline Task for saving purposes
     */
    @Override
    public String save() {
        return String.format("%s|%s|%s", this.symbol, super.save(), this.by);
    }

    /**
     * Returns string representation of Deadline Task
     * 
     * @return String representation of Deadline Task
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (by %s)", this.symbol, super.toString(),
                this.by);
    }
}
