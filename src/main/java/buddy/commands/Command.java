package buddy.commands;

import buddy.TaskList;
import buddy.exceptions.BuddyCommandException;
import buddy.exceptions.BuddyException;
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
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws BuddyCommandException;
}
