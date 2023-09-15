package duke.Command;

import duke.Exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

/**
 * Class for the help command.
 */
public class HelpCommand extends Command {

    @Override
    public String execute(Ui ui, Storage storage, TaskList tasks) throws DukeException, IOException {
        storage.writeTasksToFile(tasks);
        return ui.printHelpMessage();
    }
}
