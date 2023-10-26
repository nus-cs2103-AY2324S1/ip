package jarvis.commands;

import jarvis.storage.Storage;
import jarvis.tasks.TaskList;
import jarvis.ui.Ui;

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
        assert !find.isBlank() : "Search parameter is blank!";
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
        return tasks.filterByName(this.find);
    }
}
