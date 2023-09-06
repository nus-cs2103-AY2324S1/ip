package ipbot.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * Represents a deadline, with a description and due time.
 */
public class Deadline extends Task {

    protected LocalDateTime endTime;

    /**
     * Attempts to parse a due time String to LocalDateTime.
     * Then, defines a Deadline object with the given description and due time.
     *
     * @param description The description of the deadline.
     * @param endTimeStr The due time of the deadline as a String.
     * @throws DateTimeParseException
     */
    public Deadline(String description, String endTimeStr) throws DateTimeParseException {
        this(description, LocalDateTime.parse(endTimeStr, Task.INPUT_DATE_TIME_FORMATTER));
    }

    /**
     * Defines a Deadline object with the given description and due time.
     *
     * @param description The description of the deadline.
     * @param endTime The due time of the deadline.
     */
    public Deadline(String description, LocalDateTime endTime) {
        super(description);
        this.endTime = endTime;
    }

    /**
     * Checks if the deadline is due on the date of the given LocalDateTime.
     *
     * @param dateTime Defines the date to check if the deadline is due.
     * @return True if the deadline is due on the day given. False otherwise.
     */
    public boolean isDue(LocalDateTime dateTime) {
        return this.endTime.truncatedTo(ChronoUnit.DAYS).isEqual(dateTime.truncatedTo(ChronoUnit.DAYS));
    }

    @Override
    public String toCommaString() {
        String[] commaStringValues = {
                "D",
                this.isDone ? "X" : " ",
                this.description,
                this.endTime.format(Task.INPUT_DATE_TIME_FORMATTER),
        };
        String commaString = String.join(",", commaStringValues);
        return commaString;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + this.endTime.format(Task.DISPLAY_DATE_TIME_FORMATTER) + ")";
    }
}
