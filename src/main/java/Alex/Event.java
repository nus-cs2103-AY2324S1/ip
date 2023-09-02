package Alex;

import java.time.Month;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

public class Event extends Task{
    protected String fromTime;
    protected String toTime;

    protected LocalDateTime parsedFromTime;
    protected LocalDateTime parsedToTime;

    public Event(String description, String fromTime, String toTime) throws AlexException, DateTimeParseException {
        super(description);
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.parsedFromTime = LocalDateTime.parse(fromTime, UserInputStorage.TIMEFORMATTER);
        this.parsedToTime = LocalDateTime.parse(toTime, UserInputStorage.TIMEFORMATTER);
    }

    public String getFromTime() {
        return this.fromTime;
    }

    public String getToTime() {
        return this.toTime;
    }

    public LocalDate getFromDate() {
        String fromDateString = this.fromTime.substring(0, 10);
        LocalDate fromDate = LocalDate.parse(fromDateString);
        return fromDate;
    }

    public LocalDate getToDate() {
        String toDateString = this.toTime.substring(0, 10);
        LocalDate toDate = LocalDate.parse(toDateString);
        return toDate;
    }

    @Override
    public String toString() {
        int fromHour = parsedFromTime.getHour();
        int fromMinute = parsedFromTime.getMinute();
        Month fromMonth = parsedFromTime.getMonth();
        int fromDay = parsedFromTime.getDayOfMonth();
        int fromYear = parsedFromTime.getYear();

        int toHour = parsedToTime.getHour();
        int toMinute = parsedToTime.getMinute();
        Month toMonth = parsedToTime.getMonth();
        int toDay = parsedToTime.getDayOfMonth();
        int toYear = parsedToTime.getYear();
        return "[E]" + super.toString()
                + " (from: " + fromHour +":" + fromMinute + " " + fromDay + " " + fromMonth + " " + fromYear + " "
                + " to: " + toHour + ":" + toMinute + " " + toDay + " " + toMonth + " " + toYear + ")";
    }
}
