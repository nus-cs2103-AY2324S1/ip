import java.time.LocalDate;
import java.util.List;

public class CreateDeadlineCommand extends Command {

    private Deadline task;

    public CreateDeadlineCommand(String task, LocalDate by) {
        this.task = new Deadline(task,by);
    }
    @Override
    public void execute(TaskList list) {
        list.add(task);
        Ui.ui.createTaskPrompt(task);
        Duke.run();
    }

}
