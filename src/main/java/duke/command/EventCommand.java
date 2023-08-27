import java.time.format.DateTimeParseException;

public class EventCommand extends Command {
    private String description;
    private String from;
    private String to;

    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = new Event(this.description, this.from, this.to);
            tasks.addTask(task);
            ui.printAddTask(task, tasks.getCountTasks());
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! Invalid date format. Please type dates in the format yyyy-mm-dd");
        }
    }
}
