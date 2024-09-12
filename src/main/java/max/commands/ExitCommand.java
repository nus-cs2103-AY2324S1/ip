package max.commands;

import max.exception.MaxException;
import max.storage.Storage;
import max.tasks.TaskList;
import max.ui.Ui;

/**
 * Represents exit command.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public ExitCommand() {

    }
    /**
     * Executes exit command. Saves current task list to storage. Prints exit message.
     *
     * @param tasks Task list
     * @param ui UI
     * @param storage Storage
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MaxException {
        storage.writeToFile(tasks);
        return ui.exit();
    }
    /**
     * Checks if command is an exit command.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
