package duke.command;

import duke.list.TaskList;
import duke.storage.Storage;

/**
 * Subclass of Command class. Exits the program with bye message.
 */
public class ByeCommand extends Command {
    private String commandMessage = "";

    /**
     * Class constructor of ByeCommand.
     */
    public ByeCommand() {
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
     * Exits the program.
     * @param taskList List with tasks.
     * @param storage Storage where tasks are stored.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        commandMessage = "JalGa! See you again!";
    }

    /**
     * Returns true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
