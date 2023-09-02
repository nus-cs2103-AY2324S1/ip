package duke;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

//CHECKSTYLE.OFF: MissingJavadocMethodCheck
/**
 * Represents a deadline task that user creates
 */
public class Deadline extends Task {
    protected LocalDate by;
    protected LocalTime time;

    public Deadline(String description, LocalDate by, LocalTime time) {
        super(description);
        this.by = by;
        this.time = time;
    }

    /**
     * Return the string representation of deadline in the right date and time format
     *
     * @return a string with the right date and time format
     */
    @Override
    public String toString() {
        if (time == null) {
            return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + time.format(DateTimeFormatter.ofPattern(" hh:mm a")) + ")";
        }

    }

    /**
     * Returns a string with the right format that can be saved in the file
     *
     * @return string of right date and time format for the file
     */
    public String save() {
        if (time != null) {
            return "D|" + super.status() + "|" + by + time.format(DateTimeFormatter.ofPattern(" hhmm"));
        } else {
            return "D|" + super.status() + "|" + by;
        }
    }
}
