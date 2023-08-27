import java.time.LocalDateTime;

public class EventCommand extends Command {

    private String name;
    private LocalDateTime from;
    private LocalDateTime to;

    public EventCommand(String name, LocalDateTime from, LocalDateTime to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList items, Ui ui, Storage storage) throws DukeException {
        Task item = items.addEvent(name, from, to);
        ui.addItem(item.toString(), items.getCount());
        storage.writeData(items.getItems());
    }
}
