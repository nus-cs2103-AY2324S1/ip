import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    private String description;
    private String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = new Deadline(this.description, this.by);
            tasks.addTask(task);
            ui.printAddTask(task, tasks.getCountTasks());
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! Invalid date format. Please type dates in the format yyyy-mm-dd");
        }
    }
}
