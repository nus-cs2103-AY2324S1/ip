import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    private final LocalDate by;


    /**
     * Constructor for DeadlineTask.
     *
     * @param description of the task.
     * @param by          deadline of the task.
     */
    public DeadlineTask(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Get the deadline of the task.
     *
     * @return deadline of the task.
     */
    public String getBy() {
        return this.by.format(DateTimeFormatter.ofPattern(Task.DATE_FORMAT));
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.getBy() + ")";
    }
}
