import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Representation of a deadline task
 * recorded by the chatbot.
 * 
 * @author Alvis Ng (supermii2)
 */
public class TaskEvent extends Task{
    /**Start and end times of events */
    LocalDate startTime, endTime;
    /**
     * Creates a deadline task.
     * @param taskName Name of task
     * @param startTime Start time of task
     * @param endTime End time of task
     */
    TaskEvent(String taskName, String startTime, String endTime) throws IllegalArgumentException {
        super(taskName);
        super.oneLetterAbbrev = "E";
        try {
            this.startTime = LocalDate.parse(startTime);
            this.endTime = LocalDate.parse(endTime);
            if (startTime.compareTo(endTime) > 0) throw new IllegalArgumentException("Start Date after End Date");
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Illegal Date/Time");
        }
    }
    @Override
    /**
     * String representation of Event
     * @return String representation of Event
     */
    public String toString() {
        return super.toString() + 
        " (from: " + this.startTime + " to: " + this.endTime + ")";
    }    
}
