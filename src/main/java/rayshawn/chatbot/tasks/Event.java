package rayshawn.chatbot.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents event task in tasklist.
 */
public class Event extends Task {
    private LocalDate date;
    private LocalTime start;
    private LocalTime end;

    /**
     * Constructor for Event.
     *
     * @param task description of task
     * @param date date of event
     * @param start start time of event
     * @param end end time of event
     */
    public Event(String task, String date, String start, String end) {
        super(task, "E");

        assert date != null : "Date should not be null";
        assert start != null : "Start time should not be null";
        assert end != null : "End time should not be null";
        this.date = LocalDate.parse(date);
        this.start = LocalTime.parse(start, DateTimeFormatter.ofPattern("ha"));
        this.end = LocalTime.parse(end, DateTimeFormatter.ofPattern("ha"));
    }

    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getStart() {
        return this.start.format(DateTimeFormatter.ofPattern("ha"));
    }

    public String getEnd() {
        return this.end.format(DateTimeFormatter.ofPattern("ha"));
    }

    public void updateDate(String date) {
        this.date = LocalDate.parse(date);
    }

    public void updateStart(String start) {
        this.start = LocalTime.parse(start, DateTimeFormatter.ofPattern("ha"));
    }
    public void updateEnd(String end) {
        this.end = LocalTime.parse(end, DateTimeFormatter.ofPattern("ha"));
    }

    @Override
    public String toString() {
        assert date != null : "Date should not be null";
        assert start != null : "Start time should not be null";
        assert end != null : "End time should not be null";

        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("h a");
        return super.toString() + " (from: "
                + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                + " " + start.format(timeFormat) + " to: " + end.format(timeFormat) + ")";

    }
}
