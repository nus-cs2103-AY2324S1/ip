package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the Command Exit that will shut the program when being executed.
 *
 * @author Angky Akdi Frandy Putrakelana
 */
public class ExitCommand extends Command {

    /**
     * Executes the ExitCommand.
     *
     * @param tasks The list of tasks.
     * @param ui The Ui that used as user interface.
     * @param storage The Storage that used to store, read and write data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printExit();
    }

    /**
     * Check if it is an ExitCommand
     *
     * @return a boolean that represent whether this is an ExitCommand or not.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
