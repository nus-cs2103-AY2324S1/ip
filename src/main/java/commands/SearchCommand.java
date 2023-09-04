package commands;

import functions.*;
import java.util.ArrayList;
import tasks.*;

/**
 * Represents a command to search for tasks based on a keyword.
 */
public class SearchCommand extends Command {

    /**
     * The keyword to search for in task descriptions.
     */
    String searchWord;

    /**
     * Constructs a SearchCommand with the keyword to search for.
     *
     * @param searchWord The keyword to search for in task descriptions.
     */
    public SearchCommand(String searchWord) {
        this.searchWord = searchWord;
    }

    /**
     * Executes the SearchCommand to search for tasks based on the keyword,
     * display matching tasks, and show the search results.
     *
     * @param tasks   The TaskList to search for tasks in.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage interface (not used in this command).
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matches = tasks.searchTask(this.searchWord);
        ui.showMatchesMsg(matches);
    }
}
