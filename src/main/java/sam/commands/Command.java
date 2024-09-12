package sam.commands;

import sam.services.Storage;
import sam.services.TaskList;
import sam.services.Ui;

/**
 * Represents an executable command.
 * Based on AddressBook2
 */
public abstract class Command {

    /**
     * Executes the command and returns the result.
     *
     * @return the result of the command.
     */
    public abstract CommandResult execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns that it is the end of the conversation.
     *
     * @return boolean of whether it is an Exit Command.
     */
    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
