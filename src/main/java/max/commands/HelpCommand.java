package max.commands;
import max.commands.*;
import max.exception.MaxException;
import max.parser.Parser;
import max.storage.Storage;
import max.tasks.TaskList;
import max.tasks.*;
import max.ui.Ui;

/**
 * Represents delete command.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    /**
     * Constructor for help command.
     *
     */
    public HelpCommand() {
    }
    /**
     * Executes help command. Shows message with key instructions.
     *
     * @param tasks Task list
     * @param ui UI
     * @param storage Storage
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MaxException {
        return ui.showHelp();
    }
    /**
     * Checks if command is an exit command.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
