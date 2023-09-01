package seedu.duke;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Encapsulates the Event class.
 * An event is a Task with a start and end date.
 */
public class Event extends Task {
    protected String type = "E";
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Creates a new Event instance.
     *
     * @param name The name of the event given by the user.
     * @param start The start date of the event.
     * @param end The end date of the event.
     */
    public Event(String name, LocalDateTime start, LocalDateTime end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getTaskType() {
        return "[" + this.type + "]";
    }

    @Override
    public String getTimeInfo() {
//        return "hi";
        // Format month in words
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH);
        String startMonthInWords = this.start.format(monthFormatter);
        String endMonthInWords = this.end.format(monthFormatter);

        // Format day of the month
        int startDayOfMonth = this.start.getDayOfMonth();
        int endDayOfMonth = this.end.getDayOfMonth();

        // Format year
        int startYear = this.start.getYear();
        int endYear = this.end.getYear();

        // Format the time in AM/PM
        DateTimeFormatter amPmFormatter = DateTimeFormatter.ofPattern("h:mm a");
        LocalTime startTime = this.start.toLocalTime();
        String formattedStartTime = startTime.format(amPmFormatter);
        LocalTime endTime = this.end.toLocalTime();
        String formattedEndTime = endTime.format(amPmFormatter);

        return "(from: " + startMonthInWords + " " + startDayOfMonth + " " + startYear + ", " + formattedStartTime + " " +
                "to: " + endMonthInWords + " " + endDayOfMonth + " " + endYear + ", " + formattedEndTime + ")";
    }
}
