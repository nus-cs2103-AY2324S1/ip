package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

/**
 * Represents an event inherits from a Task.
 */
public class Event extends Task {
    private static String invalidDate = "Please provide dates with the following format: YYYY-MM-DD";
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructor for Event.
     * @param name
     * @param from
     * @param to
     */
    public Event(String name, LocalDate from, LocalDate to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructor for Event with specified isDone.
     * @param name
     * @param isDone
     * @param from
     * @param to
     */
    public Event(String name, boolean isDone, LocalDate from, LocalDate to) {
        super(name, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Checks if an event is happening on specified String date
     * @param dateStr date in String format
     * @return boolean whether an event is happening on specified String date
     * @throws DukeException if invalid date
     */
    public boolean isToday(String dateStr) throws DukeException {
        try {
            LocalDate date = LocalDate.parse(dateStr);
            return this.from.compareTo(date) <= 0 && date.compareTo(to) <= 0;
        } catch (DateTimeException e) {
            throw new DukeException(invalidDate);
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s (%s - %s)", super.toString(),
            this.from.format(DateTimeFormatter.ofPattern("d MMM yyyy")),
            this.to.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }

    @Override
    public String exportToText() {
        return String.format("event,>%s,>%s,>%s", super.exportToText(), this.from, this.to);
    }
}
