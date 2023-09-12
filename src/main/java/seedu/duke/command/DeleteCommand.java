package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.TaskList;
import seedu.duke.Ui;

public class DeleteCommand extends EditCommand {

    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, String[] response) throws Exception {
        int deleteIndex = ui.delete(input);
        assert deleteIndex >= 0 : "Delete index should be non-negative.";
        if (taskList.isOutOfRange(deleteIndex)) {
            throw new DukeException("☹ OOPS!!! Please specify a valid task number to be deleted.");
        }
        response[0] = taskList.remove(deleteIndex);
    }
}
