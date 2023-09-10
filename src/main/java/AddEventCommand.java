import java.time.LocalDate;

public class AddEventCommand extends AddCommand {
    public static final String COMMAND_WORD = "event";
    public AddEventCommand(String description, LocalDate start, LocalDate end) {
        super(new Event(description, start, end));
    }
}
