package chatty.command;

import chatty.exception.ChattyException;
import chatty.task.TaskList;
import chatty.utils.Storage;
import chatty.utils.Ui;

/**
 * A class that handle the find command that the user entered
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Contructor for find command that will create a task object
     * @param keyword the keyword used to find the task
     */
    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    /**
     * Find the task matching the keywords entered
     * @param taskList the tasklist with the current task
     * @param ui the current user interface
     * @param storage helps to update the data when necessary
     * @return the string that list out the tasks that matched the keywords
     * @throws ChattyException when storage could not update the files
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
