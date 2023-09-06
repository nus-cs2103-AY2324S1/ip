package mattbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * Implements an Event style task.
 */
public class Event extends Task {
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;
    private static final DateTimeFormatter DTFORMAT  = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
    /**
     * Constructor for the Event class, defaults to an incomplete event.
     */
    public Event(String name, LocalDateTime startDate, LocalDateTime endDate) {
        super(name, false);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    /**
     * Constructor for the Event class, no assumptions on doneness.
     */
    public Event(String name, boolean isDone, LocalDateTime startDate, LocalDateTime endDate) {
        super(name, isDone);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns LocalDateTime for the start of the event.
     *
     * @return LocalDateTime of start of event.
     */
    public LocalDateTime getStartDate() {
        return this.startDate;
    }
    /**
     * Returns LocalDateTime for the end of the event.
     *
     * @return LocalDateTime of end of event.
     */
    public LocalDateTime getEndDate() {
        return this.endDate;
    }
    /**
     * Identifies itself as an Event.
     *
     * @returns Character to identify the type of Task.
     */
    public String identifier() {
        return "E";
    }

    /**
     * Returns a friendly version of the start date.
     *
     * @returns Start Date in form of May 12 2023 20:00
     */
    public String startDateToString() {
        return this.startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH':'mm"));
    }
    /**
     * Returns a friendly version of the end date.
     *
     * @return End Date in form of May 12 2023 20:00
     */
    public String endDateToString() {
        return this.endDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH':'mm"));
    }

    /**
     * Returns String form for storage.
     *
     * @return String for storage format.
     */
    public String toFile() {
        return identifier() + " | " + showStatusAsFile() + " | " + showName() + " | " + getStartDate().format(DTFORMAT)
                + " | " + getEndDate().format(DTFORMAT);
    }
    @Override
    public String toString() {
        return String.format("[%s] [%s] %s (from: %s to: %s)", this.identifier(), this.showStatus(), this.showName(),
                this.startDateToString(), this.endDateToString());
    }
}
