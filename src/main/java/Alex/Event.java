package Alex;

import java.time.Month;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

/**
 * A class that can be instantiated to represent the task that has a from date and an end date.
 */
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

    /**
     * A method that can be used to get the start time of this task.
     * @return the string representation of the start date of this task.
     */
    public String getFromTime() {
        return this.fromTime;
    }

    /**
     * A method that can be used to get the end time of this task.
     * @return the string representation of the end time of this task.
     */
    public String getToTime() {
        return this.toTime;
    }

    /**
     * A method that can be used to get the start date of this task in LocalDate object.
     * @return the LocalDate object representing the start date of this task.
     */
    public LocalDate getFromDate() {
        String fromDateString = this.fromTime.substring(0, 10);
        LocalDate fromDate = LocalDate.parse(fromDateString);
        return fromDate;
    }

    /**
     * A method that can be used to get the end date of this task in LocalDate object.
     * @return the LocalDate object representing the end date of this task.
     */
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
