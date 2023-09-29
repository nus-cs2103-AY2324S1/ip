package rock.tasks;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Representation of a deadline task
 * recorded by the chatbot.
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

    /**
     * Used to get the date of the task object.
     * @return Date of Deadline
     */
    @Override
    public LocalDate getDate() {
        assert this.deadlineTime != null : "Deadline time cannot be null";
        return this.deadlineTime;
    }

    /**
     * String representation of Deadline
     * @return String representation of deadline
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof TaskDeadline) {
            TaskDeadline t = (TaskDeadline) o;
            return this.getName().equals(t.getName())
                    && this.getDate().equals(t.getDate());
        } else {
            return false;
        }
    }
    @Override
    public String toString() {
        assert deadlineTime != null : "Deadline Time cannot be null";
        return super.toString()
            + String.format(" (by: %s)", this.deadlineTime.toString());
    }
}
