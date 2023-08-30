package duke.command;
import duke.ui.Ui;
import duke.task.*;
import duke.storage.Storage;
import duke.DukeException;
public class ListCommand extends Command {
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        ui.showList(list.getList());
    }
}
