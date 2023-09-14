package commands;
import components.Storage;
import components.Ui;
import tasks.TaskList;

public class ExitCommand extends Command {
    public ExitCommand() {
        super();
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        return ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
