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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Event event = new Event(description, start, end);
        tasks.add(event);
        ui.printAddSuccessMessage(event, tasks.getAllTasks());
        storage.save(tasks.getAllTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
