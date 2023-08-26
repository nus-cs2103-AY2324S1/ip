import java.time.LocalDate;

public class AddCommand extends Command {

    private final Task task;

    public AddCommand(String description) {
        this.task = new ToDo(description);
    }

    public AddCommand(String description, LocalDate fromDate, LocalDate toDate) {
        this.task = new Event(description, fromDate, toDate);
    }

    public AddCommand(String description, LocalDate byDate) {
        this.task = new Deadline(description, byDate);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RobertException {
        tasks.addTask(this.task);
        ui.showMessage("Got it. I have added this task:\n  " + this.task
                + "\nNow you have " + tasks.getTaskCount() + " "
                + (tasks.getTaskCount() > 1 ? "tasks" : "task")
                + " in the list.");
    }

}
