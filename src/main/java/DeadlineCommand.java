import java.time.LocalDate;

public class DeadlineCommand extends Command {

    private String description;
    private LocalDate by;

    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException{
        Task temp = new Deadline(description, by);
        taskList.addTask(temp);
        ui.printAddTaskMessage(temp, taskList);
        storage.save(taskList);
    }
}
