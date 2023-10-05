package buddy.commands;

import buddy.TaskList;
import buddy.utils.Storage;
import buddy.utils.Ui;

/**
 * The FindCommand is for "find" command.
 */
public class FindCommand extends Command {
    public static final String MESSAGE_FORMAT =
            "find <keyword/phrase>\n" + "Example: find book\n" + "find 2023-09";

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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String response = ui.printFilteredList(tasks.findTask(this.keyword).getAllTasks());
        return response;
    }
}
