import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class that inherits from Task.
 * 
 * @var deadline Representing deadline.
 * 
 * @author Owen Yeo
 */
public class Deadline extends Task{

    private LocalDateTime deadline;

    /**
     * Constructor for a deadline object.
     * 
     * @param label Descriptor for the task with deadline
     * @param deadline String representing deadline
     */
    Deadline(String label, String deadline) {
        super(label);
        this.deadline = LocalDateTime.parse(deadline, DateTimeFormatter
            .ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * 
     */
    @Override
    public String toSaveString() {
        return "D " + super.toSaveString() + " | " + deadline
            .format(DateTimeFormatter.ofPattern("MMM dd YYYY ha"));
    }

    /**
     * 
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline
            .format(DateTimeFormatter.ofPattern("MMM dd YYYY ha")) + ")";
    }
    
}
