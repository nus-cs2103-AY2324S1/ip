package buddy.commands;

import buddy.TaskList;
import buddy.utils.BuddyException;
import buddy.utils.Storage;
import buddy.utils.Ui;

/**
 * An abstract class representing user commands to the chatbot.
 */
public abstract class Command {

    /**
     * Executes the command.
     * @param tasks The TaskList to be worked on.
     * @param ui The Ui to be worked on.
     * @param storage The Storage to be worked on.
     * @throws BuddyException On input or file error.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        throw new BuddyException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    };
}
