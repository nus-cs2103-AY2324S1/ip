package brotherman.commands;

import brotherman.storage.Storage;
import brotherman.tasks.TaskList;
import brotherman.ui.Ui;

/**
 * Represents a command to find a task from the task list
 */
public class FindCommand extends Command {

    private String keyword;
    /**
     * Constructor for FindCommand
     * @param keyword Task to be found
     */
    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList task, Ui ui, Storage storage) {
        return ui.showFind(task, keyword);
    }

}
