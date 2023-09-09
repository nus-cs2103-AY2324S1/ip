package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.InvalidCommandException;

/**
 * The Command to indicate that the user has type in an invalid command.
 */
public class InvalidCommand extends Command {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * The constructor of InvalidCommand.
     *
     * @param taskList The task list which the command would modify when tasked.
     * @param ui The ui of the chatbot to get the input of the user.
     */
    public InvalidCommand(TaskList taskList, Ui ui, Storage storage) {
        super(taskList, ui, storage);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidCommandException {
        throw new InvalidCommandException("");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
