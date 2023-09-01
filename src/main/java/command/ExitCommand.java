package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UI;

import java.util.ArrayList;

public class ExitCommand extends Command {
    public static final String  COMMAND_EXIT = "bye";
    public ExitCommand(ArrayList<String> params) {
        super(params);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        ui.printGoodbyeMessage();
        ui.closeUi();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
