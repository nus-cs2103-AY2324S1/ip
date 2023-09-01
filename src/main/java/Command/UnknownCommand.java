package Command;
import Ui.*;
import Duke.*;
import TaskList.TaskList;
import Storage.Storage;
public class UnknownCommand extends Command{
    private String unknownCommand;

    public UnknownCommand(String unknownCommand) {
        this.unknownCommand = unknownCommand;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showError(new DukeException("Unknown command: " + unknownCommand));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
