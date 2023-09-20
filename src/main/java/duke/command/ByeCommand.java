package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to exit the bot.
 */
public class ByeCommand extends Command {
    /**
     * {@inheritDoc}
     *
     * @param taskList List of Task objects.
     * @param ui UI that the user interact with.
     * @param storage Storage to handle data to and from an external file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.byeGreeting();
    }

    /**
     * {@inheritDoc}
     *
     * @param taskList List of Task objects.
     * @param ui UI that the user interact with.
     * @param storage Storage to handle data to and from an external file.
     * @return A string message.
     */
    @Override
    public String executeGui(TaskList taskList, Ui ui, Storage storage) {
        return "Bye! Not enough sleep, gotta rest (not a program btw)";
    }

    @Override
    public boolean isExit() {
        return !super.isExit();
    }
}
