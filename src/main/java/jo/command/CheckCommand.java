package jo.command;

import jo.JoException;
import jo.Storage;
import jo.TaskList;
import jo.Ui;

import java.time.LocalDate;

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
