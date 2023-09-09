package dre.command;

import dre.exception.DreException;
import dre.storage.Storage;
import dre.task.TaskList;
import dre.ui.Ui;

/**
 * Represents a command to find and list all tasks that contain a given keyword.
 * This command is intended to help users search for specific tasks within their list.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Creates a new FindCommand with the specified keyword.
     *
     * @param keyword The keyword used to search for tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find task command.
     * This method searches for tasks containing the keyword and displays them to the user.
     *
     * @param tasks The list of tasks to search within.
     * @param ui The UI object used for user interactions.
     * @param storage The storage object, though not directly used in this command.
     * @throws DreException If there are issues during the command execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DreException {
        TaskList foundTasks = tasks.findTasksByKeyword(keyword);
        ui.showFoundTasks(foundTasks);
    }
}