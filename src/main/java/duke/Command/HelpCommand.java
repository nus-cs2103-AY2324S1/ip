package duke.Command;

import java.io.IOException;

import duke.Exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;




/**
 * Class for the help command.
 */
public class HelpCommand extends Command {
    /**
     * Executes the command.
     * @param ui the ui class used.
     * @param storage the storage that is used.
     * @param tasks the tasklist that is used.
     * @return String representation of the execution.
     * @throws IOException IOException
     * @throws DukeException DukeException
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList tasks) throws DukeException, IOException {
        storage.writeTasksToFile(tasks);
        return ui.printHelpMessage();
    }
}
