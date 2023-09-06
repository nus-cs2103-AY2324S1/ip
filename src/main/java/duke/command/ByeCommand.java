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
     * @return Message response from running the command.
     * @throws Exception throw Exception from writeFile
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws Exception {
        storage.writeFile(taskList);
        return ui.printBye();
    }
}
