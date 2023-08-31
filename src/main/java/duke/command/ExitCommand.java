package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command that exits the program. A <code>ExitCommand</code> object
 * corresponds to an executable exit command.
 */
public class ExitCommand extends Command {

    /**
     * Constructs a <code>ExitCommand</code> object.
     */
    public ExitCommand() {
        super(true);
    }

    /**
     * Executes the command to exit the program.
     *
     * @param taskList the task list
     * @param ui       the user interface to print messages to the user
     * @param storage  the storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showExit();
    }
}
