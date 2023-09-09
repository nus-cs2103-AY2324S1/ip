package duke.Command;

import duke.Exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public class WelcomeCommand extends Command {
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws DukeException, IOException {
        ui.printWelcomeMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
