package duke.Command;

import java.io.IOException;

import duke.Exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;


public class WelcomeCommand extends Command {
    @Override
    public String execute(Ui ui, Storage storage, TaskList tasks) throws DukeException, IOException {
        return ui.printWelcomeMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
