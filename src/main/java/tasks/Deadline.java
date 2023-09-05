package tasks;

import helpers.DateTimeDetection;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;

/**
 * This class encapsulates a Deadline child class
 * that contains a description, and an end date/time.
 */
public class Deadline extends Task {
    protected LocalDateTime end;

    /**
     * Constructs a Deadline object.
     *
     * @param description
     * @param end Date or Time, or both, when the Deadline is due.
     */
    public Deadline(String description, String end) {
        super(description);
        setDateTime(end);
    }

    /**
     * Constructs a Deadline object that specifies whether it has been completed.
     *
     * @param description
     * @param isDone
     * @param end Date or Time, or both, when the Deadline is due.
     */
    public Deadline(String description, boolean isDone, String end) {
        super(description, isDone);
        setDateTime(end);
    }

    /**
     * Sets the String input of the end date/time to a LocalDateTime
     *
     * @param input In the form of yyyy-MM-dd HH:mm. Either Date or Time may be omitted but not both.
     */
    public void setDateTime(String input) {
        String[] parts = input.split(" ", 2);
        this.end = DateTimeDetection.detectDateTime(parts[1]);
    }


    @Override
    public String toText() {
        return "D " + this.getDoneStatus() + " " +
                this.description + " /" +
                this.end.toString().replace("T", " ");
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() +
                " (" + DateTimeDetection.formatDateTime(this.end) + ")";
    }
}
