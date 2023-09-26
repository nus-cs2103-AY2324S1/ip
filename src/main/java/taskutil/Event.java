package taskutil;

import java.time.LocalDateTime;

import duke.Parser;

/**
 * Class for Event task.
 */
public class Event extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructor for Event task.
     *
     * @param title Description of task.
     * @param start Start of Event as LocalDateTime object.
     * @param end End of Event as LocalDateTime object.
     */
    public Event(String title, LocalDateTime start, LocalDateTime end) {
        super(title);
        this.start = start;
        this.end = end;
    }

    /**
     * Checks that input datetime falls within start/end of the event.
     *
     * @param localDateTime DateTime to be considered.
     * @return Whether event is under schedule of input datetime.
     */
    public boolean isUnderSchedule(LocalDateTime localDateTime) {
        return localDateTime.isBefore(end) && localDateTime.isAfter(start);
    }

    @Override
    public String toString() {
        String from = start.format(DISPLAY_FORMAT);
        String to = end.format(DISPLAY_FORMAT);
        String time = String.format(" (from: %s to: %s)", from, to);
        return "[E]" + super.toString() + time;
    }

    /**
     * Converts Event task to a string for storing in data file.
     *
     * @return Formatted string with data for Event task.
     */
    @Override
    public String toFileString() {
        String period = String.format(" | %s | %s", Parser.formatDate(start), Parser.formatDate(end));
        return "E" + super.toFileString() + period;
    }
}
