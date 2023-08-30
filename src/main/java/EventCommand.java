import java.time.LocalDateTime;
import java.util.ArrayList;

public class EventCommand extends Command {
    private String description;
    private LocalDateTime from;
    private LocalDateTime to;

    public EventCommand(String description, LocalDateTime from, LocalDateTime to) {
        super(CommandType.EVENT);
        this.description = description;
        this.from = from;
        this.to = to;
    }

    public void execute(ArrayList<Task> tasks, Ui ui) {
        Task task = new Event(description, from, to);
        tasks.add(task);
        ui.eventMessage(task);
        ui.taskListSizeMessage(tasks.size(), true);
    }
}