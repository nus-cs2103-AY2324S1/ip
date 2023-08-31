package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Event class represents a task that occurs within a specific time range.
 */
public class Event extends Task {

    protected String start;
    protected String end;
    protected LocalDate parsedStartDate;
    protected LocalDate parsedEndDate;

    /**
     * Constructs an Event instance with the specified description, start date, and end date.
     *
     * @param description The description of the event.
     * @param start       The start date of the event in string format.
     * @param end         The end date of the event in string format.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
        parseDates();
    }

    /**
     * Constructs an Event instance with the specified description, start date, end date, and completion status.
     *
     * @param description The description of the event.
     * @param start       The start date of the event in string format.
     * @param end         The end date of the event in string format.
     * @param mark        The completion status of the event.
     */
    public Event(String description, String start, String end, boolean mark) {
        super(description, mark);
        this.start = start;
        this.end = end;
        parseDates();
    }

    /**
     * Parses the start and end date strings into LocalDate objects using multiple date formats.
     */
    private void parseDates() {
        DateTimeFormatter[] dateFormats = {
          DateTimeFormatter.ofPattern("yyyy-MM-dd"),
          DateTimeFormatter.ofPattern("MMM dd yyyy")
        };

        for (DateTimeFormatter dateFormat : dateFormats) {
            try {
                parsedStartDate = LocalDate.parse(start, dateFormat);
                parsedEndDate = LocalDate.parse(end, dateFormat);
                break;
            } catch (DateTimeParseException e) {
                parsedStartDate = null;
                parsedEndDate = null;
            }
        }
    }

    /**
     * Returns the string representation of the Event task.
     *
     * @return The formatted string representation of the task.
     */
    @Override
    public String toString() {
        String startString = parsedStartDate != null
          ? parsedStartDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
          : start;

        String endString = parsedEndDate != null
          ? parsedEndDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
          : end;

        return "[E]" + super.toString() + " from " + startString + " to " + endString;
    }
}
