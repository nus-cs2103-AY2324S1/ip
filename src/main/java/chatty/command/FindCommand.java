package chatty.command;

import chatty.exception.ChattyException;
import chatty.task.TaskList;
import chatty.utils.Storage;
import chatty.utils.Ui;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

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
