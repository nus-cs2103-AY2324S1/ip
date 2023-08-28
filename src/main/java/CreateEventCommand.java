import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class CreateEventCommand extends Command {

    private Events task;

    public CreateEventCommand(String task, LocalDate from, LocalDate to) {
        this.task = new Events(task, from, to);
    }

    @Override
    public void execute(TaskList list) {
        list.add(task);
        Ui.ui.createTaskPrompt(task);
        Duke.run();
    }

}