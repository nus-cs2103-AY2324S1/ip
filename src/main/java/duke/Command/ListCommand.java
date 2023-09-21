package duke.Command;

import java.io.IOException;

import duke.Exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;


/**
 * Class that handles the list command.
 */
public class ListCommand extends Command {
    @Override
    public String execute(Ui ui, Storage storage, TaskList tasks) throws DukeException, IOException {
        storage.writeTasksToFile(tasks);
        return ui.printListMessage(tasks);
    }
}
