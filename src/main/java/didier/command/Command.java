package didier.command;

import didier.Storage;
import didier.TaskList;
import didier.exception.DidierException;

/**
 * Represents a command that was given by the user, resulting in some interaction with the storage and some
 * output being produced. The Command class is abstract since the manner in which it is executed
 * depends on what type of command it is.
 */
public abstract class Command {
    /**
     * Executes the command, resulting in reading and writing to the storage
     * depending on what the type of command is.
     *
     * @param taskList The list of the users tasks.
     * @param storage  The storage object where the tasks are written to and stored.
     * @throws DidierException If the command was inappropriate resulting in some error.
     */
    public abstract void execute(TaskList taskList, Storage storage) throws DidierException;

    /**
     * Returns the response of the bot to the reception of the command.
     *
     * @param taskList The list of the users tasks.
     * @param storage  The storage object where the tasks are written to and stored.
     * @return the bot response.
     */
    public abstract String getBotOutput(TaskList taskList, Storage storage) throws DidierException;
    /**
     * Returns whether this Command is an ExitCommand.
     *
     * @return whether this Command is an ExitCommand.
     */
    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
