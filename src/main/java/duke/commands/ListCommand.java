package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents the ListCommand Class.
 * Responsible for handling display list operation.
 *
 * @author Shishir
 */
public class ListCommand extends Command {

    /**
     * Executes the required command.
     * @param tasks List of all the tasks.
     * @param ui Ui for interacting with the user.
     * @param storage Storage of the tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
//        ui.showLine();
//        ;
//        tasks.print();
//        ui.showLine();
        return ui.showList(tasks.size()) + "\n" + tasks.toString();
    }

    /**
     * Returns the exit status of the command.
     * @return Exit status of the command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
