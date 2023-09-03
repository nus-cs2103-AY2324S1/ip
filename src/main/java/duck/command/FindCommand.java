package duck.command;

import duck.DuckException;
import duck.Storage;
import duck.Ui;

import duck.task.TaskList;

/**
 * Represents an executable command which finds matching tasks.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates a new find command.
     * 
     * @param keyword Keyword to be searched for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DuckException {
        ui.showMatchingTasksMessage(tasks.find(keyword));
    }
}
