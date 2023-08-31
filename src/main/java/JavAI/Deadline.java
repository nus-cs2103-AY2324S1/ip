package JavAI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
/**
 * Represents a deadline task that has a description and a due date.
 */
public class Deadline extends Task {

    /** The due date of the deadline task. */
    protected LocalDate endDate;
    protected LocalTime endTime;

    /**
     * Constructs a deadline task with a description and a due date.
     *
     * @param description The description of the deadline task.
     * @param endDate The due date of the deadline task.
     * @param endDate The due time of the deadline task.
     */
    public Deadline(String description, String endDate, String endTime) {
        super(description);
        this.endDate = LocalDate.parse(endDate);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("HHmm");
       this.endTime = LocalTime.parse(endTime, inputFormatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " ( by: " +
                this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + this.endTime + " )";
    }
}
