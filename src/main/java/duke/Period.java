package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Period extends Task{
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs aa Period object with the given description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the period.
     * @param to The end time of the period.
     */
    public Period(String description, String from, String to) {
        super(description);
        this.from = parseDateTime(from);
        this.to = parseDateTime(to);
    }

    /**
     * Parses the given date-time string into a LocalDateTime object.
     * Supports multiple date-time formats.
     *
     * @param by The date-time string to be parsed.
     * @return The LocalDateTime representation of the given date-time string.
     * @throws DateTimeParseException If no suitable date-time format is found.
     */
    private LocalDateTime parseDateTime(String by) {
        DateTimeFormatter[] formatters = {
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
                DateTimeFormatter.ISO_LOCAL_DATE_TIME
        };

        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDateTime.parse(by, formatter);
            } catch (DateTimeParseException e) {

            }
        }
        throw new DateTimeParseException("No suitable date-time format found", by, 0);
    }

    /**
     * Returns the string representation of this period.
     *
     * @return The string representation of this period.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "[P]" + super.toString() + " (from: " + this.from.format(formatter) + " to: "
                + this.to.format(formatter) + ")";
    }

    /**
     * Returns the file-friendly string representation of this period.
     *
     * @return The file-friendly string representation of this period.
     */
    @Override
    public String toFile() {
        return "P | " + super.toFile() + " | " + this.from + " | " + this.to;
    }
}
