import java.time.LocalDate;

public class AddEventCommand extends Command {
    private String description;
    private LocalDate start;
    private LocalDate end;

    public AddEventCommand(String description, LocalDate start, LocalDate end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpotException {
        Event e = tasks.addEvent(description, start, end);
        ui.sayAdd(tasks, e);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
