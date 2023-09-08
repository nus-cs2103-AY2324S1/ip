package haste.commands;

import haste.data.TaskList;
import haste.ui.Ui;

/**
 * Represents a command which finds tasks containing keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates a FindCommand.
     *
     * @param keyword Keyword to search for in Task Descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command.
     *
     * @param tasks TaskList containing the tasks.
     * @param ui Ui that handles interactions.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        TaskList filteredTasks = tasks.filterTasks(this.keyword);
        return ui.printList(filteredTasks);
    }
}
