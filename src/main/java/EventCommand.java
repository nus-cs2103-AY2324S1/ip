import java.time.LocalDateTime;

public class EventCommand extends Command {
    private String description;
    private LocalDateTime from;
    private LocalDateTime to;

    public EventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        Task event = new Event(description, from, to);
        tasks.add(event);
        storage.update(tasks);
        ui.displayMsg(new String[] {
            "Okie! I've added a new " + Ui.cTxt("EVENT", Ui.COLOR.YELLOW) + ":",
            "  " + event.toString(),
            "Total no. of tasks stored: " + tasks.getSize()
        });
    }
}
