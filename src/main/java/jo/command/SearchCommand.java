package jo.command;

import jo.JoException;
import jo.Storage;
import jo.TaskList;
import jo.Ui;

/**
 * The `SearchCommand` class represents a command for searching tasks containing a specified keyword.
 */

public class SearchCommand extends Command {

    private String keyword;

    /**
     * Constructs a `SearchCommand` object with the specified keyword to be used for task search.
     *
     * @param keyword The keyword to search for within task descriptions.
     */
    public SearchCommand(String keyword) {
        this.keyword = keyword;
    }
    /**
     * * Executes the command, searching for tasks containing the specified keyword and displaying the result.
     *
     * @param tasks   The `TaskList` containing tasks to operate on.
     * @param ui      The user interface for displaying search results.
     * @param storage The storage object for loading and saving tasks to a file (unused in this command).
     * @throws JoException If an error occurs during the execution of the command (unused in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.findResult(tasks.find(this.keyword));
    }

    /**
     * Checks whether the command results in exiting the application.
     *
     * @return `false` since searching for tasks does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
