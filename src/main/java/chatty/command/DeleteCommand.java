package chatty.command;

import chatty.exception.ChattyException;
import chatty.task.TaskList;
import chatty.utils.Storage;
import chatty.utils.Ui;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int i) {
        super(false);
        this.index = i;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws ChattyException {

        try {
            storage.saveTask(taskList);
        } catch (Exception e) {
            throw new ChattyException("Cannot save chatty.task!");
        }
        return ui.showDelete(index, taskList);
    }
}
