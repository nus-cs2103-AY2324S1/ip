package nobita.command;

import nobita.storage.Storage;
import nobita.task.TaskList;
import nobita.ui.Ui;

public class FindCommand extends Command {

    /** The query string to search for */
    private final String query;

    /**
     * Constructs FindCommand with a query String.
     * @param query The item to search  for.
     */
    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Command that executes finding of a task.
     *
     * @param tasks Contains all current tasks.
     * @param ui Ui for interacting with user.
     * @param storage Storage that the data file is stored in.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks.findTask(this.query));
    };

    /**
     * Retrieves a boolean on whether the Command is an Exit Command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    };
}
