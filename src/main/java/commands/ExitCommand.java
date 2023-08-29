package commands;
import exceptions.DukeException;
import io.Storage;
import tasks.TaskList;
import ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
        super("bye");
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showGoodbye();
        this.setExit();
    }
}
