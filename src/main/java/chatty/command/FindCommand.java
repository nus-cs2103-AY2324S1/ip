package chatty.command;

import chatty.exception.ChattyException;
import chatty.task.TaskList;
import chatty.utils.Storage;
import chatty.utils.Ui;

/**
 * A class that handles the find command entered by the user to search for tasks containing a specific keyword.
 * When the user provides a find command with a keyword, an instance of this class is created
 * to search for tasks in the task list that match the given keyword.
 */
public class FindCommand extends Command {

    private final String keyword;


    /**
     * Constructor for the FindCommand instance.
     * Initializes the instance with the provided keyword to be used in the task search.
     *
     * @param keyword The keyword used to search for tasks.
     */
    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    /**
     * Executes the find command to search for tasks matching the specified keyword.
     * This method returns a string containing a list of tasks that match the keyword.
     *
     * @param taskList The task list with the current available tasks.
     * @param ui The current user interface for displaying messages.
     * @param storage The storage class (unused in this command).
     * @return A String containing a list of tasks that match the specified keyword.
     * @throws ChattyException when storage could not update the files (unused in this command).
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws ChattyException {

        try {
            storage.saveTask(taskList);
        } catch (Exception e) {
            throw new ChattyException("Cannot save chatty.task!");
        }
        return ui.showMatched(taskList.containsKeyword(this.keyword));
    }
}
