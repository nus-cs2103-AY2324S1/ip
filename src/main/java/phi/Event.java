package phi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the Event task type, and contains two Strings with user-specified dates as the start and end of a task
 */
public class Event extends Task {
    private String start;
    private String end;

    /**
     * Constructor for a new Event instance
     *
     * @param msg       message to be displayed
     * @param isDone    Boolean determining if task is completed
     * @param start     User-specified start time of task
     * @param end       User-specified end time of task
     */
    public Event(String msg, boolean isDone, String start, String end) {
        super(Type.E, isDone, msg);
        this.start = start;
        this.end = end;
        LocalDate startDate;
        LocalDate endDate;
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");

        try {
            startDate = LocalDate.parse(start, inputFormat);
            this.start = startDate.format(outputFormat);
        } catch (DateTimeParseException e) {
            //System.out.println("Can't find a proper date format, using /from input as a String");
        }

        try {
            endDate = LocalDate.parse(end, inputFormat);
            this.end = endDate.format(outputFormat);
        } catch (DateTimeParseException e) {
            //System.out.println("Can't find a proper date format, using /to input as a String");
        }
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (from: %s to: %s)", start, end);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String outputFormat() {
        return String.format("%s|%b|%s|%s|%s", taskType.toString(), done, taskName, start, end);

    }
}