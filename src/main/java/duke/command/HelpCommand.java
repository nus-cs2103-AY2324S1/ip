package duke.command;

import duke.exception.DukeException;
import duke.util.Keyword;
import duke.util.Response;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a <code>help</code> command. A <code>HelpCommand</code> object corresponds
 * to an executable command to provide help to the user.
 */
public class HelpCommand extends Command {
    private final String commandBody;

    /**
     * Constructs a <code>help</code> command.
     * For the purpose of a general help.
     */
    public HelpCommand() {
        this("");
    }

    /**
     * Constructs a <code>help</code> command.
     * For the purpose of a specific help.
     *
     * @param commandBody The command body.
     */
    public HelpCommand(String commandBody) {
        super(false);
        this.commandBody = commandBody;
    }

    /**
     * Executes the help command to provide help to the user.
     *
     * @param taskList The task list.
     * @param ui       The user interface.
     * @param storage  The storage.
     * @return the response to the user.
     * @throws DukeException If an error occurs during execution.
     */
    @Override
    public Response execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        assert ui != null : "ui should not be null";

        if (commandBody.isEmpty()) {
            return ui.showGeneralHelp();
        }

        try {
            Keyword key = Keyword.valueOf(commandBody.toUpperCase());
            return ui.showHelp(key);
        } catch (IllegalArgumentException e) {
            String errMessage = String.format("OOPS!!! I might not be able to provide help for the command '%s'.",
                    commandBody);
            throw new DukeException(errMessage);
        }
    }
}
