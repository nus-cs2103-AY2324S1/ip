package slay.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents Deadline Task.
 */
public class Deadline extends Task {
    public static final String TYPE = "deadline";
    protected LocalDateTime by;

    public Deadline(String description, String dateString, String timeString) throws DateTimeParseException {
        super(description);
        LocalDate date = LocalDate.parse(dateString);
        LocalTime time = LocalTime.parse(timeString);
        LocalDateTime by = LocalDateTime.of(date, time);
        this.by = by;
    }

    public Deadline(Boolean isDone, String description, String dateTimeString) throws DateTimeParseException {
        super(description, isDone);
        LocalDateTime by = LocalDateTime.parse(dateTimeString);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy, HH:mm");
        String byFormatted = this.by.format(formatter);
        return "[D]" + super.toString() + " (by: " + byFormatted + ")";
    }

    /**
     * Returns encoded Deadline Task.
     *
     * @return String representation of the Deadline Task to be saved into the data file.
     */
    @Override
    public String getEncodedString() {
        String encoded = "D | ";

        if (super.isDone) {
            encoded += "1";
        } else {
            encoded += "0";
        }

        encoded += " | " + super.getTag()
                + " | " + super.getDescription()
                + " | " + this.by;

        return encoded;
    }
}
