package duke.command;

import duke.exception.ChatException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Prompts the program to find all tasks with keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for the class FindCommand.
     * @param keyword Keyword of task.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Returns a list of all the tasks that matches the keyword.
     * @param tasks List of task stored by the program.
     * @param ui Responses to be shown to user.
     * @param storage Saves the list of task to be accessed in the future.
     * @return
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList foundList = tasks.findTask(this.keyword);
        try {
            return ui.listMatchingTaskResponse(foundList);
        } catch (ChatException e) {
            return ui.showLoadingError(e);
        }
    }
}
