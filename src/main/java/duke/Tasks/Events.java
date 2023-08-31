package duke.Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.FormatterDate;

/**
 * a subclass of a task object
 */
public class Events extends Task {
    protected String start;
    protected LocalDate startDate;
    protected String end;
    protected LocalDate endDate;

    /**
     * Constructor of the event class
     * @param description is the description of the event
     * @param originalStart is the start date of the event
     * @param originalEnd is the end date of the event
     */
    public Events(String description, String originalStart, String originalEnd) {
        super(description);
        this.start = originalStart;
        this.startDate = Task.convertDatePlease(originalStart);
        this.end = originalEnd;
        this.endDate = Task.convertDatePlease(originalEnd);
        if (startDate != null) {
            DateTimeFormatter stringFormatter = FormatterDate.basicOutput.formatter;
            this.start = startDate.format(stringFormatter);
        }
        if (endDate != null) {
            DateTimeFormatter stringFormatter = FormatterDate.basicOutput.formatter;
            this.end = endDate.format(stringFormatter);
        }
    }

    /**
     * string of the event object
     * @return a string
     */
    @Override
    public String toString() {
        return String.format(
                "| E |%s (from: %s to: %s)", super.toString(), start, end);
    }
}
