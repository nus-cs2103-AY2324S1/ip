package dude.command;

import dude.Storage;
import dude.Ui;
import dude.exception.DudeException;
import dude.task.TaskList;

/**
 * Command to find task with description containing given substring.
 */
public class FindTaskCommand extends DudeCommand {
    /**
     * Substring to search.
     */
    private final String searchString;

    /**
     * Constructor for find task command.
     *
     * @param searchString Substring to search.
     */
    public FindTaskCommand(String searchString) {
        this.searchString = searchString;
    }

    /**
     * Finds tasks with description matching substring.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DudeException {
        ui.printMessage(taskList.displaySearch(searchString));
    }

    /**
     * {@inheritDoc}
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
