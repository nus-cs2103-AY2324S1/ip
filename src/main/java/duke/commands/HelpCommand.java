package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.UI;

/**
 * Represents a command to show the program usage to help the user.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.showHelpMessage();
    }
}
