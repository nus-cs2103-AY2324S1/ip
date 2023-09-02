package benben;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A type of task that ahs a start and end time
 */
public class Event extends Task {
    /**
     * The Start time.
     */
    protected LocalDateTime startTime;
    /**
     * The End time.
     */
    protected LocalDateTime endTime;

    /**
     * The Formatter that parses the string intO A LocalDateTime object
     */
    protected final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withZone(ZoneId.systemDefault());

    /**
     * Instantiates a new Event.
     *
     * @param description the description
     * @param startTime   the start time
     * @param endTime     the end time
     * @throws BenBenException if the string is of the wrong format
     */
    public Event(String description,String startTime, String endTime) throws BenBenException {
        super(description);
        try {
            this.startTime = LocalDateTime.parse(startTime, formatter);
            this.endTime = LocalDateTime.parse(endTime, formatter);
            if (this.startTime.isAfter(this.endTime)) {
                throw new BenBenException("The end time should be later than the start time!");
            }
        } catch (DateTimeParseException e) {
            throw new BenBenException("The date and time is of the wrong format! Please use yyyy-MM-dd HH:mm");
        }
    }
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + getStartTime() + " to: " + getEndTime() + ")";
    }

    /**
     * Gets start time.
     *
     * @return the start time
     */
    public String getStartTime() {
        return startTime.getMonth().toString()
                + " " + startTime.getDayOfMonth()
                + " " + startTime.getYear()
                + " " + startTime.getHour() + ":" + startTime.getMinute();
    }

    /**
     * Gets end time.
     *
     * @return the end time
     */
    public String getEndTime() {
        return endTime.getMonth().toString()
                + " " + endTime.getDayOfMonth()
                + " " + endTime.getYear()
                + " " + endTime.getHour() + ":" + endTime.getMinute();
    }

    /**
     * Gets formatted start time
     *
     * @return the formatted start time
     */
    public String getFormattedStart() {
        return startTime.format(formatter);
    }

    /**
     * Gets formatted end time
     *
     * @return the formatted end time
     */
    public String getFormattedEnd() {
        return endTime.format(formatter);
    }

    @Override
    public String getLog() {
        return "E | " + (isDone? "1" : "0")
                + " | " + this.description
                + " | " + getFormattedStart()
                + " | " + getFormattedEnd() + System.lineSeparator();
    }

    @Override
    public boolean equals(Object task) {
        if (task == this) {
            return true;
        }

        if (!(task instanceof Event)) {
            return false;
        }

        Event t = (Event) task;

        return t.getLog().equals(this.getLog());
    }
}