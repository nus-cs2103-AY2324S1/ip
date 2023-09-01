package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to find tasks by keyword.
 */
public class FindCommand extends Command {

    /**
     * The keyword to search for.
     */
    private final String keyword;

    /**
     * Constructs a new FindCommand object.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the search for tasks by keyword.
     * Updates the UI.
     *
     * @param tasks The list of tasks.
     * @param ui The UI for user interaction.
     * @param storage The storage for saving tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList foundTasks = tasks.findTasksByKeyword(keyword);
        ui.showFoundTasks(foundTasks);
    }
}
