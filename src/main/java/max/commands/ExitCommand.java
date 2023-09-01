package max.commands;
import max.commands.*;
import max.exception.MaxException;
import max.parser.Parser;
import max.storage.Storage;
import max.tasks.TaskList;
import max.tasks.*;
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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MaxException {
        storage.writeToFile(tasks);
        ui.exit();
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
