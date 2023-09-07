package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

public class Deadline extends Task {
    /** The actual deadline of a deadline task, as a LocalDate or LocalDateTime*/
    private Temporal deadlineTemporal;

    /** The formatter used to aid in parsing the deadline string for UI purposes */
    private DateTimeFormatter printFormatter;

    /** The formatter used to aid in parsing the deadline string for storage purposes */
    private DateTimeFormatter saveFormatter;

    /**
     * Constructs a Deadline instance.
     *
     * @param name The name of the task.
     * @param isDone The completion status of the task.
     * @param deadlineTemporal The actual deadline of the task, as a LocalDate or LocalDateTime object.
     */
    public Deadline(String name, boolean isDone, Temporal deadlineTemporal) {
        super(name, isDone);
        this.deadlineTemporal = deadlineTemporal;
        this.printFormatter = deadlineTemporal instanceof LocalDateTime
                ? DateTimeFormatter.ofPattern("HHmm, dd LLL, yyyy")
                : DateTimeFormatter.ofPattern("dd LLL, yyyy");
        this.saveFormatter = deadlineTemporal instanceof LocalDateTime
                ? DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")
                : DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", printFormatter.format(this.deadlineTemporal));
    }

    /**
     * {@inheritDoc}
     *
     * Returns the string representation of a Deadline, in storage format.
     */
    public String toString(boolean isWritten) {
        String completionStr = super.isDone() ? "1" : "0";
        return "D" + " | " + completionStr + " | " + super.getName() + " | " + saveFormatter.format(this.deadlineTemporal);
    }
}
