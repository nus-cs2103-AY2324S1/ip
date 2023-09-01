import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime date;
    private static final long serialVersionUID = 6548478093468504893L;
    //included serialVersionUID indicated as the Task implements Serializable Interface
    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    @Override
    public String taskString() {
        return "[D]" + super.taskString() + "(by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
}