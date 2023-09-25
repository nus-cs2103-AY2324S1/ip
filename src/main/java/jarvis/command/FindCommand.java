package jarvis.command;

import java.util.ArrayList;

import jarvis.storage.Storage;
import jarvis.task.Task;
import jarvis.tasklist.TaskList;
import jarvis.ui.Ui;

/**
 * Represents a command to find tasks that contain a keyword.
 * Contains the keyword to search for.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructor for FindCommand.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the FindCommand.
     * Finds all tasks that contain the keyword, and displays them to the user.
     *
     * @param tasks The list of tasks.
     * @param ui The Ui object, for displaying the matching tasks to the user.
     * @param storage The Storage object, not used here.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = tasks.find(keyword);
        return ui.displayMatchingTasks(matchingTasks);
    }
}
