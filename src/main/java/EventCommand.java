import java.time.LocalDateTime;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    private String taskDescription;
    private LocalDateTime from;
    private LocalDateTime to;

    public EventCommand(String taskDescription, LocalDateTime from, LocalDateTime to) {
        this.taskDescription = taskDescription;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatBuddyException {
        Event event = new Event(taskDescription, from, to);
        tasks.addTask(event);
        ui.showTaskAddition(event, tasks.getSize());
    }
}
