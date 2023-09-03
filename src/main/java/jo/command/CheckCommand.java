package jo.command;

import java.time.LocalDate;

import jo.JoException;
import jo.Storage;
import jo.TaskList;
import jo.Ui;


public class CheckCommand extends Command {

    private LocalDate deadline;

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
