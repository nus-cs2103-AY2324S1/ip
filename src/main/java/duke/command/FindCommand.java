package duke.command;
import java.io.IOException;

import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;




/**
 * Represents a command to search and display tasks that contain a specific keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a new FindCommand instance with a specified keyword to search for.
     *
     * @param keyword The keyword to match tasks against.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command, searching the tasks for any that contain the keyword.
     * Matching tasks are then displayed to the user via the UI.
     *
     * @param tasks   The list of tasks to search through.
     * @param ui      The UI instance to display matching tasks to the user.
     * @param storage The storage instance, not used in this command but needed for command interface.
     * @throws IOException If there's an issue with input or output operations.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        return tasks.findTasks(keyword, ui);
    }
}
