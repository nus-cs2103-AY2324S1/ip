package dude.command;

import dude.Storage;
import dude.TaskList;
import dude.Ui;

/**
 * Represents a command that is not defined in Dude.
 */
public class UnknownCommand extends Command {

    /**
     * Executes the command to tell users the command is unknown.
     *
     * @param taskList List of tasks.
     * @param storage Storage containing saved tasks, and saves and loads tasks.
     * @param ui User interface of Dude.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String output = ui.showUnknownCommand();
        return output;
    }
}
