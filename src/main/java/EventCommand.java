import java.time.LocalDateTime;

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

    public void execute(TaskList tasks, Ui ui) {
        Task task = new Event(description, from, to);
        tasks.addTask(task);
        ui.eventMessage(task);
        ui.taskListSizeMessage(tasks.getSize(), true);
    }
}