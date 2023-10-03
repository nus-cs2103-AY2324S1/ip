package duke.command;

import duke.Duplicate.Duplicate;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A Command Class to list out the Task
 *
 * @author marioalvaro
 */
public class ListCommand extends Command {
    /**
     * Method to execute the list out.
     *
     * @param taskList The used TaskList
     * @param ui The ui object
     * @param storage The storage used
     * @return Message response from running the command.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage, Duplicate duplicate) {

        return ui.printTaskList(taskList);
    }
}
