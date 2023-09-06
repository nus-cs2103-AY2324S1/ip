package Jelly.task;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Corresponds to a task that occurs from a specified time to a specified time.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    protected LocalDate fromDate;
    protected LocalDate toDate;

    protected LocalDateTime fromDateTime;
    protected LocalDateTime toDateTime;

    /**
     * Constructor for an event task.
     *
     * @param description The description of the task.
     * @param from The date/date and time which the task begins.
     * @param to The date/date and time which the task ends.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.fromDate = parseDate(from);
        this.toDate = parseDate(to);
        this.fromDateTime = parseDateTime(from);
        this.toDateTime = parseDateTime(to);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {

        String fromWhen = from;
        String toWhen = to;

        if (fromDate != null) {
            fromWhen = fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } else if (fromDateTime != null) {
            fromWhen = fromDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        }

        if (toDate != null) {
            toWhen = toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } else if (toDateTime != null) {
            toWhen = toDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        }
        return "[E]" + super.toString() + "(from: " + fromWhen + " to: " + toWhen + ")";
    }

    @Override
    public String writeToFile() {
        return "E | " + (getIsDone() ? "1" : "0") + " | " + getDescription() + " | " + from + " to " + to;
    }

    /**
     * Parses the date given for the task.
     *
     * @param date The start date of the task.
     * @return The parsed date, or null if incorrect format.
     */
    protected LocalDate parseDate(String date) {

        List<DateTimeFormatter> formats = new ArrayList<>();
        formats.add(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        formats.add(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        formats.add(DateTimeFormatter.ofPattern("d/M/yyyy"));

        for (int i = 0; i < formats.size(); i++) {
            try {
                return LocalDate.parse(date, formats.get(i));
            } catch (DateTimeParseException e) {

            }
        }
        return null;
    }

    /**
     * Parses the date and time given for the task.
     * @param dateTime The start date and time of the task.
     * @return The parsed date and time, or null if incorrect format.
     */
    protected LocalDateTime parseDateTime(String dateTime) {

        List<DateTimeFormatter> formats = new ArrayList<>();
        formats.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        formats.add(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        formats.add(DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
        formats.add(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));

        for (int i = 0; i < formats.size(); i++) {
            try {
                return LocalDateTime.parse(dateTime, formats.get(i));
            } catch (DateTimeParseException e) {

            }
        }
        return null;
    }
}