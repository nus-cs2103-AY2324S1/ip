package rock.tasks;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Representation of a deadline task
 * recorded by the chatbot.
 * @author Alvis Ng (supermii2)
 */
public class TaskEvent extends Task {
    /** Start and end times of events */
    private LocalDate startTime;
    private LocalDate endTime;
    /**
     * Creates a deadline task.
     * @param taskName Name of task
     * @param startTime Start time of task
     * @param endTime End time of task
     */
    public TaskEvent(String taskName, String startTime, String endTime) throws IllegalArgumentException {
        super(taskName);
        super.oneLetterAbbrev = "E";
        try {
            this.startTime = LocalDate.parse(startTime);
            this.endTime = LocalDate.parse(endTime);
            if (startTime.compareTo(endTime) > 0) {
                throw new IllegalArgumentException("Start Date after End Date");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Illegal Date/Time");
        }
    }
    /**
     * Used to get the date of the task object.
     * @return Start date of Event
     */
    @Override
    public LocalDate getDate() {
        assert this.startTime != null : "Start Time cannot be null";
        return this.startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof TaskEvent) {
            TaskEvent t = (TaskEvent) o;
            return this.getName().equals(t.getName())
                    && this.startTime.equals(t.startTime)
                    && this.endTime.equals(t.endTime);
        } else {
            return false;
        }
    }

    /**
     * String representation of Event
     * @return String representation of Event
     */
    @Override
    public String toString() {
        assert this.startTime != null && this.endTime != null : "Start and End Time cannot be null";
        return super.toString()
                + String.format(" (from: %s to: %s)", this.startTime.toString(), this.endTime.toString());
    }
}
