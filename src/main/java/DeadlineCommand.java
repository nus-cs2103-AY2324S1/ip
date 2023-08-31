import java.time.LocalDateTime;

public class DeadlineCommand extends AddCommand{
    private String description;
    private LocalDateTime by;

    public DeadlineCommand (String description, boolean isDone, LocalDateTime by) {
        super(new Deadline(description, isDone, by));
    }
}
