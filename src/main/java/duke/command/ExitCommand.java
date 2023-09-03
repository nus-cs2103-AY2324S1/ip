package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The ExitCommand class is for the command "bye".
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command.
     *
     * @param tasks The TaskList to be worked on.
     * @param ui The Ui to be worked on.
     * @param storage The Storage to be worked on.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printExitMessage();
    }

    /**
     * Checks whether it is an exit command.
     *
     * @return Returns true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
