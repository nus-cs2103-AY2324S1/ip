package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents a command to display the help list.
 */
public class HelpCommand extends Command {

    /**
     * Constructs a new HelpCommand.
     */
    public HelpCommand() {
        super(false);
    }

    /**
     * Executes the HelpCommand, displaying the list of inputs available.
     *
     * @param taskList The list of tasks to search within.
     * @param ui       The user interface to display messages.
     * @param storage  The storage to save the task list.
     * @return A String of the inputs available.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showHelp();
    }
}
