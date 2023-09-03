package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents the FindCommand Class
 * Responsible for handling the find operation.
 *
 * @author Shishir
 */
public class FindCommand extends Command {

    /** String entered by user. */
    private String find;

    /**
     * Constructs the FindCommand object.
     * @param find Keyword to find.
     */
    public FindCommand(String find) {
        this.find = find;
    }

    /**
     * Executes the respective command.
     * @param tasks List of all the tasks.
     * @param ui Ui for interacting with the user.
     * @param storage Storage of the tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showFind(this.find) + "\n" + tasks.filterByName(this.find);
    }

    /**
     * Returns the exit status of the command.
     * @return Exit status of the command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
