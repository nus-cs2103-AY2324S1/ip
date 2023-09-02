import java.io.IOException;

public class ByeCommand extends Command {

    public ByeCommand() {}
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException, IOException {
        ui.byeMessage();
        storage.saveList();
    }

    @Override
    public boolean end() {
        return false;
    }
}
