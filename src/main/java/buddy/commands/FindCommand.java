package buddy.commands;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        String response = ui.printList(tasks.findTask(this.keyword).getAllTasks());
        return response;
    }
}
