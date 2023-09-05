import java.io.IOException;
import java.time.LocalDate;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(String taskDescription) {
        super(false);
        this.task = new Todo(taskDescription);
    }

    public AddCommand(String taskDescription, LocalDate deadline) {
        super(false);
        this.task = new Deadline(taskDescription, deadline);
    }

    public AddCommand(String taskDescription, String startTime, String endTime) {
        super(false);
        this.task = new Event(taskDescription, startTime, endTime);
    }


    public void execute(TaskList tasks, Ui ui, Storage storage) throws QiException {
        tasks.addTask(this.task);
        ui.showTaskAdded(this.task ,tasks);
        try {
            storage.update(tasks);
        } catch (IOException e) {
            throw new QiException("Cannot write to file!");
        }
    }
}
