package fishron;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Initializes a new Event instance.
     *
     * @param description The description of the event.
     * @param from        The starting date and time of the event in the format "dd-MM-yyyy HHmm".
     * @param to          The ending date and time of the event in the format "dd-MM-yyyy HHmm".
     * @throws FishronException If the date/time format is invalid.
     */
    public Event(String description, String from, String to) throws FishronException {
        super(description);
        try {
            this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
            this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new FishronException("☹ OOPS!!! Please provide a valid date/time format. e.g. 21-05-2023 1200");
        }
    }

    /**
     * Converts the event task to a string in the file format.
     *
     * @return A string representation of the event task in the file format.
     */
    public String toFileString() {
        return "E | " + super.toFileString() + " | " + from.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")) + " | " +
                to.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }

    /**
     * Converts the event task to a string representation.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma")) + " to: " +
                to.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma")) + ")";
    }
}
