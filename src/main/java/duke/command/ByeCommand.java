package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A Command Class to execute gooodbye statement
 *
 * @author marioalvaro
 */
public class ByeCommand extends Command {
    /**
     * Method to execute the goodbye statement.
     *
     * @param taskList The used TaskList
     * @param ui The ui object
     * @param storage The storage used
     * @throws Exception throw Exception from writeFile
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws Exception {
        ui.printBye();
        storage.writeFile(taskList);
    }
}
