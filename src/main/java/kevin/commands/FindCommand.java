package kevin.commands;

import java.util.ArrayList;

import kevin.storage.Storage;
import kevin.task.Task;
import kevin.task.TaskList;
import kevin.ui.Ui;

/**
 * Represents a find command in the Duke application.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Creates a new FindCommand with the given keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        assert keyword != null : "Keyword should not be null";
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> foundTasks = tasks.findTasks(keyword);
        ui.showFind(foundTasks);
    }

    @Override
    public String executeGui(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> foundTasks = tasks.findTasks(keyword);
        return ui.showFindGui(foundTasks);
    }

    public boolean isExit() {
        return false;
    }

}
