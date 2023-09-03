package skye.commands;

import skye.data.TaskList;
import skye.storage.Storage;
import skye.ui.UI;

/**
 * Represents the command to exit from the program.
 */
public class ByeCommand extends Command{

    public static final String COMMAND_WORD = "bye";

    /**
     * Executes the ByeCommand
     *
     * @param taskList TaskList
     * @param ui UI
     * @param storage Storage
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.showGoodBye();
    }

    /**
     * When called, the program will exit.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}