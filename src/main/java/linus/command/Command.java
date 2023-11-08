package linus.command;

import linus.exception.LinusException;

/**
 * Represents a command that can be executed by Linus.
 */
public abstract class Command {
    /**
     * Executes the command.
     * @throws LinusException
     */
    public abstract void execute() throws LinusException;
}
