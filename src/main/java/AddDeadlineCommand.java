import java.time.LocalDate;

public class AddDeadlineCommand extends AddCommand {
    public static final String COMMAND_WORD = "deadline";
    public AddDeadlineCommand(String description, LocalDate deadline) {
        super(new Deadline(description, deadline));
    }
}
