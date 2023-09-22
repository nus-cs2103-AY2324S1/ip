package duke.command;

import duke.list.TaskList;
import duke.storage.Storage;

/**
 * Subclass of Command class. Creates invalid command with message.
 */
public class InvalidCommand extends Command {
    private String commandMessage = "";

    /**
     * Class constructor of InvalidCommand.
     */
    public InvalidCommand() {
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public String getCommandMessage() {
        return commandMessage;
    }

    /**
     * {@inheritDoc}
     *
     * Generates message to tell the user to use "help" command.
     * @param taskList List with tasks.
     * @param storage Storage where tasks are stored.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        commandMessage = "Mi an,, I do not understand. Maybe can type 'help' to find out the commands?";
    }
}
