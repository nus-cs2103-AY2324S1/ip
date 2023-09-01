package sisyphus.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate deadline;

    /**
     * Constructor when provided description and deadline.
     *
     * @param description
     * @param deadline
     */
    public Deadline (String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Constructor when provided description, isDone state and deadline.
     *
     * @param description
     * @param isDone
     * @param deadline
     */
    public Deadline (String description, boolean isDone, LocalDate deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Formats localDate into MMM d yyyy format.
     *
     * @param localDate
     * @return formatted date in MMM d yyyy format.
     */
    public String formatDate(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns string representation.
     *
     * @return string representation of deadline with status icon and deadline.
     */
    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " +  this.description + " (by: " + formatDate(this.deadline) + ")";
    }

    /**
     * Returns string representation used for saveFormat which is csv.
     *
     * @return string representation with comma as separator.
     */
    @Override
    public String toSaveFormat() {
        return String.format("D,%s,%s,%s", this.description, this.isDone ? "1" : "0",
                this.deadline);
    }
}
