import java.time.LocalDate;

public class AddCommand extends Command {

    private String description;
    private LocalDate by, from, to;

    public AddCommand(String description, LocalDate by, LocalDate from, LocalDate to) {
        this.description = description;
        this.by = by;
        this.from = from;
        this.to = to;
    }
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DidierException {
        Task task;
        if (this.from != null && this.to != null) {
            task = new Event(this.description, this.from, this.to);
        } else if (this.by != null) {
            task = new Deadline(this.description, this.by);
        } else {
            task = new ToDo(this.description);
        }
        taskList.addTask(task);
        storage.saveTasks(taskList);
        ui.botPrintTaskAdded(task, taskList.getSize());
    }
}
