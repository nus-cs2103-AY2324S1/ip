package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to find a task
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructor
     *
     * @param keyword the keyword to search for
     */
    public FindCommand(String keyword) {
        assert !keyword.isBlank() : "Keyword cannot be empty";
        this.keyword = keyword;
    }

    /**
     * The keyword to search for
     *
     * @return the keyword to search for
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filteredTasks = tasks.filter(keyword);
        return ui.showFilteredList(filteredTasks.getLength()) + "\n"
                + filteredTasks.showList();
    }

    /**
     * Returns true if the command is an exit command
     *
     * @return true if the command is an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
