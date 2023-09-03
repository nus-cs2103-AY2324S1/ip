package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Tasks that need to be done before a specific date/time.
 *
 * @author Sebastian Tay
 */
public class Deadline extends Task {
    //TODO

    static final String SYMBOL = "D";
    protected String by;

    protected LocalDateTime deadline;

    /**
     * Constructor for deadline task that is used for user inputs.
     *
     * @param description is the description of the task.
     * @param by deadline for the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.deadline = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    /**
     * Constructor for deadline task that is used when retrieving tasks from the saved file.
     *
     * @param description is the description of the task.
     * @param by deadline for the task.
     * @param isDone indicates whether the task has been completed.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
        this.deadline = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }


    @Override
    public String getType() {
        return SYMBOL;
    }

    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + "(by: " + this.deadline.format(DateTimeFormatter.ofPattern("dd MMMM yyyy HHmm")) + "H)";
    }

    @Override
    public String convertToStorageForm() {
        final String separator = "::";
        final String status = isDone() ? "1" : "0";

        //D::0::return book::June 6th
        return SYMBOL + separator + status + separator + getDescription() + separator + this.by;
    }
}
