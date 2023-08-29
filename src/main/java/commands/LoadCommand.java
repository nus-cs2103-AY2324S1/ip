package commands;
import exceptions.DukeException;
import io.Storage;
import tasks.TaskList;
import ui.Ui;

public class LoadCommand extends Command {
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.load(taskList);
        ui.showLoad();
    }
}
