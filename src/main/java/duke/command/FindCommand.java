package duke.command;

import duke.tasks.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Represents a command to search for tasks that match a given keyword.
 * <p>
 * The {@code FindCommand} allows users to find tasks in their list based
 * on a keyword they provide.
 * </p>
 */
public class FindCommand extends Command {
    /**
     * Keyword to search against.
     */
    private final String keyword;

    /**
     * Constructs a new {@code FindCommand} with the provided keyword.
     *
     * @param keyword Keyword to search against
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command by searching for the keyword in the provided task list,
     * and printing the results.
     *
     * @param tasks List of duke.tasks.
     * @param ui User interface.
     * @param storage Storage system.
     * @return A confirmation message for finding tasks from keyword.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.findTasks(keyword);
    }

    /**
     * Indicates that this command doesn't terminate the application.
     *
     * @return {@code false} since this command doesn't cause the application to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
