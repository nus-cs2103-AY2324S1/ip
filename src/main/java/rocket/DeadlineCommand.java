package rocket;

import java.time.LocalDateTime;

public class DeadlineCommand extends AddCommand{
    private String description;
    private LocalDateTime by;

    /**
     * Create deadline command.
     * @param description task description.
     * @param isDone whether the task has been done.
     * @param by the deadline.
     */
    public DeadlineCommand (String description, boolean isDone, LocalDateTime by) {
        super(new Deadline(description, isDone, by));
    }
}
