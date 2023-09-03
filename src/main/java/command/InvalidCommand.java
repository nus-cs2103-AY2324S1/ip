package command;

import duke.Ui;
import duke.TaskList;

import exception.InvalidCommandException;

/**
 * The Command to indicate that the user has type in an invalid command.
 */
public class InvalidCommand extends Command {
    private TaskList taskList;
    private Ui ui;

    /**
     * The constructor of InvalidCommand.
     *
     * @param taskList The task list which the command would modify when tasked.
     * @param ui The ui of the chatbot to get the input of the user.
     */
    public InvalidCommand(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidCommandException {
        throw new InvalidCommandException("");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
