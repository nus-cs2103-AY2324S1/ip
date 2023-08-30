package tasks;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Representation of a deadline task
 * recorded by the chatbot.
 * 
 * @author Alvis Ng (supermii2)
 */
public class TaskDeadline extends Task {
    /** Deadline time of the task */
    private LocalDate deadlineTime;
    /**
     * Creates a deadline task.
     * @param taskName Name of task
     * @param deadlineTime Deadline
     */
    public TaskDeadline(String taskName, String deadlineTime) throws IllegalArgumentException {
        super(taskName);
        super.oneLetterAbbrev = "D";
        try {
            this.deadlineTime = LocalDate.parse(deadlineTime);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Illegal Date/Time");
        }
    }
    @Override
    public LocalDate getDate() {
        return this.deadlineTime;
    }
    @Override
    /**
     * String representation of Deadline
     * @return String representation of deadline
     */
    public String toString() {
        return super.toString() + 
        " (by: " + this.deadlineTime + ")";
    }
}
