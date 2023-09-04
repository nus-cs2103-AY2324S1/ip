package duke.assets.commands;

import duke.data.TaskList;

public class UnmarkCommand extends OperationOnListCommandAbstract {
    public UnmarkCommand(String input) {
        super(input);
    }

    @Override
    protected void completeOperation(TaskList tasklist) {
        tasklist.unmarkTaskAt(Integer.parseInt(input.split(" ")[1]) - 1);
    }
}
