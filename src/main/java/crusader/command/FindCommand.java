package crusader.command;

import crusader.Storage;
import crusader.TaskList;
import crusader.Ui;
import crusader.exception.CrusaderException;

/**
 * Command used to search for matching tasks.
 */
public class FindCommand extends Command {

    private final String query;

    /**
     * Constructs a new find/search command.
     *
     * @param query The query to search for.
     */
    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage9) throws CrusaderException {
        return String.format(
                "Here are the matching tasks in the list:\n%s",
                taskList.filter(query).toString());
    }
}
