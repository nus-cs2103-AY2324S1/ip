package duke.assets.commands;

import duke.data.TaskList;

public class MarkCommand extends OperationOnListCommandAbstract {
    public MarkCommand(String input) {
        super(input);
    }

    @Override
    protected void completeOperation(TaskList tasklist) {
        tasklist.markTaskAt(Integer.parseInt(input.split(" ")[1]) - 1);
    }
}
