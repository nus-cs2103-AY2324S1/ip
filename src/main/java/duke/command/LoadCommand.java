package duke.command;

import duke.exception.DukeException;
import duke.util.Response;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command to load data from a file.
 * A <code>LoadCommand</code> object corresponds to an executable command
 * to load data from either a user-specified file or the default file.
 */
public class LoadCommand extends Command {
    private final String commandBody;
    private final boolean isLoadDefault;

    /**
     * Constructs a <code>load</code> command.
     * The default file name is used.
     */
    public LoadCommand() {
        super(false);
        this.commandBody = "";
        this.isLoadDefault = true;
    }

    /**
     * Constructs a <code>load</code> command.
     *
     * @param commandBody The command body.
     */
    public LoadCommand(String commandBody) {
        super(false);
        this.commandBody = commandBody;
        this.isLoadDefault = false;
    }

    /**
     * Executes the load command to load date from a user-specified file.
     *
     * @param taskList The task list.
     * @param ui       The user interface.
     * @param storage  The storage.
     * @return the response to the user.
     * @throws DukeException If an error occurs during execution.
     */
    @Override
    public Response execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        assert taskList != null : "taskList should not be null";
        assert ui != null : "ui should not be null";
        assert storage != null : "storage should not be null";

        taskList.changeTaskList(storage.loadTasks(isLoadDefault, commandBody));
        return ui.showLoad(isLoadDefault, commandBody);
    }
}
