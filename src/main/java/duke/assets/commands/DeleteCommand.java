package duke.assets.commands;

import duke.assets.storage.TaskList;

public class DeleteCommand extends OperationOnListCommandAbstract {
    public DeleteCommand(String input) {
        super(input);
    }

    @Override
    protected void completeOperation(TaskList tasklist) {
        tasklist.deleteTaskAt(Integer.parseInt(input.split(" ")[1]) - 1);;
    }
}
