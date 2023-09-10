package buddy.commands;

import buddy.Task;
import buddy.TaskList;
import buddy.utils.BuddyException;
import buddy.utils.Storage;
import buddy.utils.Ui;

/**
 * The FindCommand is for "find" command.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * The constructor for a FindCommand.
     *
     * @param keyword The string representation of the keyword.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        ui.printFilteredList(tasks.findTask(this.keyword).getAllTasks());
    }
}
