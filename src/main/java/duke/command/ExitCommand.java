package duke.command;
import duke.ui.Ui;
import duke.task.TaskList;
import duke.storage.Storage;
import duke.DukeException;
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        storage.saveDataToFile(list.getList());
        ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }


}
