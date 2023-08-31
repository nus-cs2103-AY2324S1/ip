package brotherman.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    /**
     * Time of the deadline
     */
    private LocalDateTime startInDateTime;

    /**
     * Time of the deadline
     */
    private LocalDateTime endInDateTime;


    /**
     * Constructor for Deadline
     * @param description Description of the deadline
     * @param time Time of the deadline
     */
    public Event (String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startInDateTime = startTime;
        this.endInDateTime = endTime;

    }


    /**
     * Returns the type of the task
     * @return Type of the task
     */
    public String type() {
        return "E";
    }


    /**
     * Returns the time of the deadline
     * @return Time of the deadline
     */
    public String getStartTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String text = startInDateTime.format(formatter);
        return text;
    }


    /**
     * Returns the time of the deadline
     * @return Time of the deadline
     */
    public String getEndDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String text = endInDateTime.format(formatter);
        return text;
    }


    /**
     * Returns the time of the deadline
     * @return Time of the deadline
     */
    @Override
    public String storeText() {
        return String.format("%s|%s|%s/from%s/to%s", this.type(), this.isDone, this.description, getStartTime(), getEndDateTime());
    }

    /**
     * Returns the string representation of the task
     * @return String representation of the task
     */
    @Override
    public String toString() {
        String type = this.type();
        return String.format("[%s]%s(from:%s to:%s)", type, super.toString(), getStartTime(), getEndDateTime() );
    }
}
