package cyrus.commands;

import cyrus.parser.ParseInfo;
import cyrus.tasks.TaskList;

/**
 * Command to represent when an invalid command is provided.
 */
public class UnknownCommand extends Command {
    public UnknownCommand(TaskList taskList, ParseInfo parseInfo) {
        super(taskList, parseInfo);
    }

    /**
     * Relies on the thrown {@code CommandError}.
     *
     * @throws CommandError by default
     */
    @Override
    public String[] execute() throws CommandError {
        throw new CommandError("I'm sorry, I don't know what that means :(");
    }
}
