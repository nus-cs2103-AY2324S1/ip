import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CheckCommand extends Command {

    LocalDate deadline;

    public CheckCommand(LocalDate deadline) {
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JoException {
        ui.searchResult(this.deadline, tasks.search(this.deadline));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
